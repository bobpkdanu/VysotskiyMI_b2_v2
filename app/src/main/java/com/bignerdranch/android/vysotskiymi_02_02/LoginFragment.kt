package com.bignerdranch.android.vysotskiymi_02_02

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etLogin = view.findViewById<EditText>(R.id.etLogin)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnStart = view.findViewById<Button>(R.id.btnStart)

        val sharedPrefs = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        btnStart.setOnClickListener {
            val inputLogin = etLogin.text.toString().trim()
            val inputPassword = etPassword.text.toString().trim()

            if (inputLogin.isEmpty() || inputPassword.isEmpty()) {
                showAlert("Введите логин и пароль")
                return@setOnClickListener
            }

            val savedLogin = sharedPrefs.getString("LOGIN", null)
            val savedPassword = sharedPrefs.getString("PASSWORD", null)

            if (savedLogin == null && savedPassword == null) {
                if (inputLogin == "ects" && inputPassword == "ects2026") {
                    sharedPrefs.edit()
                        .putString("LOGIN", inputLogin)
                        .putString("PASSWORD", inputPassword)
                        .apply()
                    (activity as? MainActivity)?.navigateToFragment(ConverterFragment())
                } else {
                    showAlert("Неверный логин или пароль")
                }
            } else {
                if (inputLogin == savedLogin && inputPassword == savedPassword) {
                    (activity as? MainActivity)?.navigateToFragment(ConverterFragment())
                } else {
                    showAlert("Неверный логин или пароль")
                }
            }
        }
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}