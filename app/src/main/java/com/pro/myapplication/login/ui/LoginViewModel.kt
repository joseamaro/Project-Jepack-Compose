package com.pro.myapplication.login.ui

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.pro.myapplication.BaseViewModel
import com.pro.myapplication.login.domain.usecase.LoginUseCase
import com.pro.myapplication.login.domain.model.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginUiState>(LoginUiState.Success()) {

    fun onLoginChange(email: String, password: String) {
        _uiState.update { state ->
            (state as LoginUiState.Success).copy(
                emailValue = email,
                passwordValue = password,
                enableButton = enableButton(email = email, password = password)
            )
        }
    }

    private fun enableButton(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 1

    fun onLoginSelected(user: String, password: String) {
        viewModelScope.launch {
            _uiState.update { state ->
                (state as LoginUiState.Success).copy(
                    isLoading = true
                )
            }
            val result = loginUseCase(user, password)
            if (result.isSuccessful) {
                _uiState.update { state ->
                    (state as LoginUiState.Success).copy(
                        isLoading = false,
                        navigateTo = true
                    )
                }
            } else {
                _uiState.value = LoginUiState.Error(Throwable(result.message()))
            }
        }
    }

    fun updateStateScreen() {
        _uiState.update { state ->
            (state as LoginUiState.Success).copy(
                navigateTo = false
            )
        }
    }
}