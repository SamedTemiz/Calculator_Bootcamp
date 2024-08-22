package com.timrashard.calculator_bootcamp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.calculator_bootcamp.ui.theme.Color2
import com.timrashard.calculator_bootcamp.ui.theme.Color6

@Composable
fun ButtonComponent(
    onButtonClick: () -> Unit,
    buttonSize: Dp,
    buttonText: String,
    textColor: Color? = Color6
) {
    val fontSize = (buttonSize.value * 0.3).sp

    ElevatedCard(
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color2
        ),
        modifier = Modifier
            .size(buttonSize)
            .clickable {
                onButtonClick()
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = buttonText,
                fontSize = fontSize,
                color = textColor!!,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
fun ButtonComponentPreview(){
    ButtonComponent(buttonSize = 100.dp, buttonText = "9", onButtonClick = {})
}

