package com.timrashard.calculator_bootcamp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
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
                Scaffold(
                    containerColor = Color1
                ) { paddingValues ->

                    val configuration = LocalConfiguration.current

                    val screenHeightDp = configuration.screenHeightDp
                    val screenWidthDp = configuration.screenWidthDp
                    Log.e("EKRAN", "Width: $screenWidthDp --- Height: $screenHeightDp")

                    val gridHeightDp = screenHeightDp * 0.6f
                    Log.e("EKRAN", "Grid Height %60: $gridHeightDp --- Width: $screenWidthDp")

                    val numbers = remember { mutableStateOf("") }

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
                            Text(
                                text = numbers.value,
                                fontSize = 72.sp,
                                color = Color6,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 38.dp, vertical = 16.dp)
                            )
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
                                    if (buttonText.toIntOrNull() != null) {
                                        numbers.value += buttonText
                                    } else if (buttonText == "AC") {
                                        numbers.value = ""
                                    } else {
                                        // Diğer işlemler (örneğin, "+", "-", "=" vb.)
                                        // Burada işlemler yapılabilir
                                    }
                                },
                                width = screenWidthDp.dp,
                                height = gridHeightDp.dp
                            )
                        }
                    }
                }
            }
        }
    }
}