package com.pro.myapplication.login.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Close
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
fun LoginDataScreen(
    loginUiState: LoginUiState.Success,
    onChangeInputEmail: (String) -> Unit,
    onChangeInputPassword: (String) -> Unit,
    onValidateUser: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(Color.White)
        )
        Body(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            emailValue = loginUiState.emailValue,
            passwordValue = loginUiState.passwordValue,
            enableButton = loginUiState.enableButton,
            isLoading = loginUiState.isLoading,
            onChangeInputEmail = onChangeInputEmail,
            onChangeInputPassword = onChangeInputPassword,
            onValidateInfo = onValidateUser
        )
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    val activity = LocalContext.current as Activity
    Row(modifier = modifier) {
        IconButton(onClick = {
            activity.finish()
        }) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "icon close")
        }
    }
}

@Composable
fun Body(
    modifier: Modifier = Modifier,
    emailValue: String,
    passwordValue: String,
    enableButton: Boolean,
    isLoading: Boolean = false,
    onChangeInputEmail: (String) -> Unit,
    onChangeInputPassword: (String) -> Unit,
    onValidateInfo: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.logo_fluvial_oval),
            contentDescription = "image fluvial"
        )
        Spacing(5)
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Agua Fluvial",
            style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold, color = colorBlue60)
        )
        Spacing(20)
        Email(emailValue) {
            onChangeInputEmail(it)
        }
        Spacing(10)
        Password(passwordValue) {
            onChangeInputPassword(it)
        }
        Spacing(5)
        Text(
            modifier = Modifier.align(Alignment.End),
            text = "Forgot password?",
            style = TextStyle(fontSize = 12.sp, color = colorBlue60)
        )
        Spacing(20)
        ButtonLogin(enableButton = enableButton, isLoading = isLoading) {
            onValidateInfo()
        }
    }
}

@Composable
fun Email(email: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp)
            ),
        value = email,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text(text = "Email", color = Color(0xFFB2B2B2)) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFFAFAFA),
            focusedContainerColor = Color(0xFFFAFAFA),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun Password(password: String, onValueChange: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp)
            ),
        value = password,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text(text = "Password", color = Color(0xFFB2B2B2)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFFAFAFA),
            focusedContainerColor = Color(0xFFFAFAFA),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val image = if (passwordVisibility)
                Icons.Filled.VisibilityOff
            else
                Icons.Filled.Visibility
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "passwordVisibility")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun ButtonLogin(enableButton: Boolean, isLoading: Boolean, onNavigateTo: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
            disabledContainerColor = colorBluer40,
            containerColor = colorBlue60
        ), shape = RoundedCornerShape(10), onClick = {
            onNavigateTo()
        }, enabled = enableButton
    ) {

        LoadingButton(modifier = Modifier.align(Alignment.CenterVertically), isLoading = isLoading)
        if (!isLoading) {
            Text(text = "Ingresar", color = Color.White)
        }
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        DividerComponent()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(end = 5.dp),
                text = "Don´t have an account?", style = TextStyle(
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            )
            Text(
                text = "Sign Up.", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4EA8E9),
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
fun Spacing(dimen: Int) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimen.dp)
    )
}

@Composable
fun DividerComponent(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .height(0.5.dp),
        color = Color.Gray
    )
}