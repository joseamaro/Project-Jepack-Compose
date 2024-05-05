package com.pro.myapplication.login.domain.model


sealed class LoginUiState {
    data class Success(
        val emailValue: String = "",
        val passwordValue: String = "",
        val enableButton: Boolean = false,
        val isLoading: Boolean = false,
        val navigateTo: Boolean = false
    ) : LoginUiState()
    data class Error(val throwable: Throwable) : LoginUiState()
}