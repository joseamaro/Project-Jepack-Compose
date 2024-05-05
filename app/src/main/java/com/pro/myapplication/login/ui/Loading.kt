package com.pro.myapplication.login.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun LoadingButton(modifier: Modifier = Modifier, isLoading: Boolean = false) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = modifier.size(24.dp),
            color = Color.White,
            strokeWidth = 2.dp
        )
    }
}