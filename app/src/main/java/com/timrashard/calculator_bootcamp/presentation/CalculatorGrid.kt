package com.timrashard.calculator_bootcamp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.timrashard.calculator_bootcamp.CalculatorOperations
import com.timrashard.calculator_bootcamp.presentation.components.ButtonComponent
import com.timrashard.calculator_bootcamp.ui.theme.Color3
import com.timrashard.calculator_bootcamp.ui.theme.Color5
import com.timrashard.calculator_bootcamp.ui.theme.Color6

@Composable
fun CalculatorGrid(onButtonClick: (String) -> Unit, width: Dp, height: Dp){
    val buttons = CalculatorOperations.buttons
    val operatorIndices = CalculatorOperations.operatorIndicesSet
    val others = CalculatorOperations.others

    val itemSpace = 14.dp
    val totalHorizontalSpace = itemSpace * 5

    val availableWidth = width - totalHorizontalSpace
    val buttonSize = minOf(availableWidth / 4, height / 5)

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        userScrollEnabled = false,
        contentPadding = PaddingValues(itemSpace),
        horizontalArrangement = Arrangement.spacedBy(itemSpace),
        verticalArrangement = Arrangement.spacedBy(itemSpace)
    ) {
        itemsIndexed(buttons) { index, buttonText ->
            val textColor = when {
                index in operatorIndices -> Color5
                index in others -> Color6
                else -> Color3
            }

            ButtonComponent(
                onButtonClick = { onButtonClick(buttonText) },
                buttonSize = buttonSize,
                buttonText = buttonText,
                textColor = textColor
            )
        }
    }
}