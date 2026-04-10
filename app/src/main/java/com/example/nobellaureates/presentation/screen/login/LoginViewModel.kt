package com.example.nobellaureates.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nobellaureates.domain.usecase.LoginUseCase
import com.example.nobellaureates.presentation.common.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onUsernameChange(username: String) {
        _state.value = _state.value.copy(username = username)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onLogin() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            val result = loginUseCase(
                _state.value.username,
                _state.value.password
            )

            result.fold(
                onSuccess = { token ->
                    tokenManager.saveToken(token)
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isSuccess = true
                    )
                },
                onFailure = { exception ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Ошибка авторизации"
                    )
                }
            )
        }
    }

    fun resetSuccess() {
        _state.value = _state.value.copy(isSuccess = false)
    }
}