package com.bignerdranch.android.vysotskiymi_02_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultFragment : Fragment(R.layout.fragment_result) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvConversionResult = view.findViewById<TextView>(R.id.tvConversionResult)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        val resultText = arguments?.getString("RESULT_TEXT") ?: "Нет данных"
        tvConversionResult.text = resultText

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}