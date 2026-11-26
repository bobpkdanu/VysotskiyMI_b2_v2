package com.bignerdranch.android.vysotskiymi_02_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlin.math.pow

class ConverterFragment : Fragment(R.layout.fragment_converter) {

    private val unitPowers = mapOf(
        "байт" to 0,
        "килобайт" to 1,
        "мегабайт" to 2,
        "гигабайт" to 3
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etInputValue = view.findViewById<EditText>(R.id.etInputValue)
        val etUnitFrom = view.findViewById<EditText>(R.id.etUnitFrom)
        val etUnitTo = view.findViewById<EditText>(R.id.etUnitTo)
        val etResult = view.findViewById<EditText>(R.id.etResult)
        val btnCalculate = view.findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            val inputText = etInputValue.text.toString().trim()
            val fromText = etUnitFrom.text.toString().trim().lowercase()
            val toText = etUnitTo.text.toString().trim().lowercase()

            if (inputText.isEmpty() || fromText.isEmpty() || toText.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val inputValue = inputText.toDoubleOrNull()
            if (inputValue == null) {
                Toast.makeText(requireContext(), "Введите корректное число", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val fromPower = unitPowers[fromText]
            val toPower = unitPowers[toText]

            if (fromPower == null || toPower == null) {
                Toast.makeText(
                    requireContext(),
                    "Доступные единицы: байт, килобайт, мегабайт, гигабайт",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            val bytes = inputValue * 1024.0.pow(fromPower.toDouble())
            val convertedValue = bytes / 1024.0.pow(toPower.toDouble())

            val formattedResult = String.format("%.4f", convertedValue)
            etResult.setText(formattedResult)

            val fullInfo = "$inputValue $fromText = $formattedResult $toText"

            val resultFragment = ResultFragment().apply {
                arguments = Bundle().apply {
                    putString("RESULT_TEXT", fullInfo)
                }
            }
            (activity as? MainActivity)?.navigateToFragment(resultFragment)
        }
    }
}