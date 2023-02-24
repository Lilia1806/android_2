package com.example.android_2.ui.fragments.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.android_2.databinding.FragmentRegistrationBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private var auth: FirebaseAuth? = null
    private var storeVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() = with(binding) {
        btReceive.setOnClickListener {
            if (edNumber.text.isEmpty()) {
                if (edNumber.text.isEmpty()) {
                    edNumber.error = "Заполните страку"
                }
            } else {
                startPhoneNumberVerification(edNumber.text.toString())
            }
            btConfirm.isVisible = true
        }
        btConfirm.setOnClickListener {
            if (edNumber.text.isEmpty() || edKod.text.isEmpty()) {
                if (edNumber.text.isEmpty()) {
                    edNumber.error = "Заполните страку"
                    if (edKod.text.isEmpty()) {
                        edKod.error = "Заполните страку"
                    }
                }
            } else {
                verifyPhoneNumberWithCode(storeVerificationId, edKod.text.toString())
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(com.example.android_2.R.id.noteAppFragment)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(
                            requireContext(),
                            "Registration is not",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth!!)
            .setPhoneNumber(phoneNumber)       // Номер телефона для подтверждения
            .setTimeout(60L, TimeUnit.SECONDS) // Тайм-аут и единица измерения
            .setActivity(requireActivity())                 // Активность (для привязки обратного вызова)
            .setCallbacks(callback)          // При изменении состояния проверки Обратные вызовы
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private var callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            storeVerificationId = verificationId
            resendToken = token
        }
    }
}