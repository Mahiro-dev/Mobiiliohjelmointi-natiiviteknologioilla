package com.example.vk_1_kotlin_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vk_1_kotlin_basics.view.HomeScreen
import com.example.vk_1_kotlin_basics.ui.theme.Vk1kotlinBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Vk1kotlinBasicsTheme{
                HomeScreen()
            }
        }
    }
}
