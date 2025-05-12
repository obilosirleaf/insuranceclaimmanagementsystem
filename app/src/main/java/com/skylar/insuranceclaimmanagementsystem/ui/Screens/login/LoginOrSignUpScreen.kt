package com.skylar.insuranceclaimmanagementsystem.ui.Screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.skylar.insuranceclaimmanagementapp.data.AuthState
import com.skylar.insuranceclaimmanagementsystem.navigation.ROUTE_DASHBOARD
import com.skylar.insuranceclaimmanagementsystem.navigation.ROUTE_HOME

@Composable
fun LoginOrSignUpScreen(navController: NavHostController) {
    var isLogin by remember { mutableStateOf(true) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }


    val authState = null
    LaunchedEffect(authState) {
        val authState = null
        if (authState is AuthState.Authenticated) {
            email = ""
            password = ""
            name = ""
            confirmPassword = ""
            navController.navigate(ROUTE_DASHBOARD) {
                popUpTo(ROUTE_HOME) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = if (isLogin) "Login" else "Sign Up", style = MaterialTheme.typography.headlineMedium)

        if (!isLogin) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        }

        OutlinedTextField(value = email, onValueChange = { email = it.trim() }, label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next), modifier = Modifier.fillMaxWidth())

        PasswordTextField("Password", password, { password = it }, passwordVisibility, { passwordVisibility = it }, Modifier.fillMaxWidth())

        if (!isLogin) {
            PasswordTextField("Confirm Password", confirmPassword, { confirmPassword = it }, confirmPasswordVisibility, { confirmPasswordVisibility = it }, Modifier.fillMaxWidth())
        }

        Button(onClick = {
        }) {
            Text(if (isLogin) "Login" else "Sign Up")
        }
    }
}

// Improved PasswordTextField
@Composable
fun PasswordTextField(
    label: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisibility: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val visualTransformation = remember(passwordVisibility) {
        if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
    }

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(label) },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        trailingIcon = {
            IconButton(onClick = { onPasswordVisibilityChange(!passwordVisibility) }) {
            }
        },
        modifier = modifier
    )
}
@Preview(showBackground = true)
@Composable
fun LoginorSignUpScreenPreview() {
    LoginOrSignUpScreen(navController = rememberNavController())
}
