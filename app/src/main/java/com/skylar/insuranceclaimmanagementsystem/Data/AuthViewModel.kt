package com.skylar.insuranceclaimmanagementapp.data

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

// Sealed class to represent different authentication states
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Authenticated(val userId: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

// Sealed class for one-time UI events like navigation or error messages
sealed class AuthEvent {
    data class ShowMessage(val message: String) : AuthEvent()
    object NavigateToHome : AuthEvent()
}

@Suppress("UNCHECKED_CAST")
open class AuthViewModel : ViewModel() {

    // StateFlow to hold the current authentication state
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    open val authState: State<AuthState> = _authState as State<AuthState>

    // SharedFlow to emit one-time UI events
    private val _eventFlow = MutableSharedFlow<AuthEvent>()
    val eventFlow: SharedFlow<AuthEvent> = _eventFlow
    private val _authstate:isAuthenticated
        get() {
            TODO()
        }

    annotation class isAuthenticated

    // Login function with email and password validation
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            delay(1000) // Simulate network delay

            // Validate email and password
            if (isEmailValid(email) && isPasswordValid(password)) {
                // Simulate successful login
                _authState.value = AuthState.Authenticated(userId = "123")
                _eventFlow.emit(AuthEvent.NavigateToHome)
            } else {
                // Handle error cases
                _authState.value = AuthState.Error("Invalid email or password")
                _eventFlow.emit(AuthEvent.ShowMessage("Login failed"))
            }
        }
    }

    // Sign-up function with validation and password confirmation
    fun signUp(name: String, email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            delay(1000) // Simulate network delay

            // Check for password mismatch
            if (password != confirmPassword) {
                _authState.value = AuthState.Error("Passwords do not match")
                _eventFlow.emit(AuthEvent.ShowMessage("Password mismatch"))
                return@launch
            }

            // Validate credentials
            if (isEmailValid(email) && isPasswordValid(password)) {
                _authState.value = AuthState.Authenticated(userId = "456")
                _eventFlow.emit(AuthEvent.NavigateToHome)
            } else {
                _authState.value = AuthState.Error("Invalid credentials")
                _eventFlow.emit(AuthEvent.ShowMessage("Invalid credentials"))
            }
        }
    }

    // Helper function to validate email format
    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Helper function to validate password length
    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }
}
