package com.fery.lyfeledger.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.fery.lyfeledger.app.presentation.ui.MainScreen
import com.fery.lyfeledger.app.presentation.ui.theme.LyfeledgerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LyfeledgerTheme {
                MainScreen()
            }
        }
    }
}