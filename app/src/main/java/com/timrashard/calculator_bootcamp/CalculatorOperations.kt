package com.timrashard.calculator_bootcamp

import android.util.Log
import androidx.lifecycle.MutableLiveData

object CalculatorOperations {

    val buttons = listOf(
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "C", "0", ".", "="
    )

    // Renkler için
    val operatorIndicesSet = setOf(3, 7, 11, 15, 19)
    val others = setOf(0, 1, 2)

    // Sonuç ve İşlemler
    val equation = MutableLiveData("")
    val result = MutableLiveData("")

    fun onClick(text: String) {
        equation.value?.let { equationText ->
            when (text) {
                "AC" -> {
                    equation.value = ""
                    result.value = "0"
                }
                "C" -> {
                    equation.value = equationText.dropLast(1)
                    result.value = result.value?.dropLast(1)
                }
                "=" -> {
                    val calculatedResult = calculateResult(equationText)
                    result.value = calculatedResult
                    equation.value = calculatedResult  // Sonucu göster
                }
                "+", "-", "x", "÷" -> {
                    // operatör değiştirilebilir
                    if (equationText.isNotEmpty() && equationText.last().isDigit().not()) {
                        equation.value = equationText.dropLast(1) + text
                    } else {
                        equation.value = equationText + text
                    }
                }
                else -> {
                    // yeni sayı
                    if (equationText.isNotEmpty() && equationText.last().isDigit().not()) {
                        result.value = text  // Yeni sayıyı başlat
                    } else {
                        if (result.value == "0") {
                            result.value = text // 0' sil
                        } else {
                            result.value = (result.value ?: "") + text  // yanına ekle
                        }
                    }
                    equation.value = equationText + text
                }
            }
        }
    }


    private fun calculateResult(equation: String): String {
        try {
            val tokens = equation.split("(?<=[-+x÷])|(?=[-+x÷])".toRegex())

            val numbers = mutableListOf<Double>()
            val operations = mutableListOf<String>()

            for (token in tokens) {
                when {
                    token.matches("[+\\-x÷]".toRegex()) -> operations.add(token)
                    token.matches("\\d+(\\.\\d+)?".toRegex()) -> numbers.add(token.toDouble())
                }
            }

            // Çarpma ve bölme işlemleri
            while (operations.contains("x") || operations.contains("÷")) {
                val index = operations.indexOfFirst { it == "x" || it == "÷" }
                val result = when (operations[index]) {
                    "x" -> numbers[index] * numbers[index + 1]
                    "÷" -> numbers[index] / numbers[index + 1]
                    else -> 0.0
                }
                numbers[index] = result
                numbers.removeAt(index + 1)
                operations.removeAt(index)
            }

            // Toplama ve çıkarma işlemleri
            var finalResult = numbers[0]
            for (i in 1 until numbers.size) {
                finalResult = when (operations[i - 1]) {
                    "+" -> finalResult + numbers[i]
                    "-" -> finalResult - numbers[i]
                    else -> finalResult
                }
            }

            return if (finalResult % 1 == 0.0) {
                finalResult.toInt().toString()
            } else {
                finalResult.toString()
            }
        } catch (ex: Exception) {
            Log.e("ERROR!", ex.localizedMessage ?: "Error!")
            return "Error"
        }
    }
}
