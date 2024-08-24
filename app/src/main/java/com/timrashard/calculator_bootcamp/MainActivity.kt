package com.timrashard.calculator_bootcamp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.calculator_bootcamp.presentation.CalculatorGrid
import com.timrashard.calculator_bootcamp.ui.theme.Calculator_BootcampTheme
import com.timrashard.calculator_bootcamp.ui.theme.Color1
import com.timrashard.calculator_bootcamp.ui.theme.Color2
import com.timrashard.calculator_bootcamp.ui.theme.Color6

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculator_BootcampTheme {
                CalculatorApp()
            }
        }
    }
}

@Composable
fun CalculatorApp(){
    Scaffold(
        containerColor = Color2
    ) { paddingValues ->

        val configuration = LocalConfiguration.current

        val screenHeightDp = configuration.screenHeightDp
        val screenWidthDp = configuration.screenWidthDp
        Log.e("EKRAN", "Width: $screenWidthDp --- Height: $screenHeightDp")

        val gridHeightDp = screenHeightDp * 0.6f
        Log.e("EKRAN", "Grid Height %60: $gridHeightDp --- Width: $screenWidthDp")

        // Calculator
        val result = CalculatorOperations.result.observeAsState()
        val equation = CalculatorOperations.equation.observeAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Sayı kısımları, read-only
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 38.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = equation.value ?: "",
                        fontSize = 36.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = result.value ?: "0",
                        fontSize = 72.sp,
                        color = Color6,
                        textAlign = TextAlign.End,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Rakam ve işlem butonları
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(gridHeightDp.dp)
            ) {
                CalculatorGrid(
                    onButtonClick = { buttonText ->
                        if (buttonText.isNotEmpty()) {
                            CalculatorOperations.onClick(buttonText)
                        }
                    },
                    width = screenWidthDp.dp,
                    height = gridHeightDp.dp
                )
            }
        }
    }
}