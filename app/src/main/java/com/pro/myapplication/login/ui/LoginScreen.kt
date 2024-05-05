package com.pro.myapplication.login.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pro.myapplication.R
import com.pro.myapplication.login.domain.model.LoginUiState
import com.pro.myapplication.ui.theme.colorBlue60
import com.pro.myapplication.ui.theme.colorBluer40

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, onNavigateTo: () -> Unit) {
    val context = LocalContext.current
    val uiState by loginViewModel.uiState.collectAsState()

    when (uiState) {
        is LoginUiState.Success -> {
            val state = uiState as LoginUiState.Success
            if (state.navigateTo) {
                onNavigateTo()
                loginViewModel.updateStateScreen()
            }
            LoginDataScreen(loginUiState = state, onChangeInputEmail = {
                loginViewModel.onLoginChange(email = it, password = state.passwordValue)
            }, onChangeInputPassword = {
                loginViewModel.onLoginChange(email = state.emailValue, password = it)
            }, onValidateUser = {
                loginViewModel.onLoginSelected(
                    user = state.emailValue,
                    password = state.passwordValue
                )
            })
        }

        is LoginUiState.Error -> {
            Toast.makeText(context, "Ocurrio un error", Toast.LENGTH_SHORT).show()
        }
    }
}