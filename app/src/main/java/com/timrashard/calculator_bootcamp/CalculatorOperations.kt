package com.timrashard.calculator_bootcamp

object CalculatorOperations {

    enum class Operator {
        SUM, EXTRACT, DIVIDE, MULTIPLY
    }

    val buttons = listOf(
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        ".", "0", ",", "="
    )

    val operatorIndices = mapOf(
        Operator.DIVIDE to 3,
        Operator.MULTIPLY to 7,
        Operator.SUM to 15,
        Operator.EXTRACT to 11
    )

    val operatorIndicesSet = operatorIndices.values.toSet()
    val others = setOf(0, 1, 2)
}
