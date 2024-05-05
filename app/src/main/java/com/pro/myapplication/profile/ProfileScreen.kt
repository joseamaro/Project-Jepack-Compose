package com.pro.myapplication.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.Blue)) {
        Text(modifier = Modifier.align(Alignment.Center), text = "Perfil")
    }
}