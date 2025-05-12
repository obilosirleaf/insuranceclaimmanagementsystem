package com.skylar.insuranceclaimmanagementsystem.ui.Screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.skylar.insuranceclaimmanagementapp.data.AuthState
import com.skylar.insuranceclaimmanagementapp.data.AuthViewModel
import com.skylar.insuranceclaimmanagementsystem.navigation.ROUTE_DASHBOARD
import com.skylar.insuranceclaimmanagementsystem.navigation.ROUTE_HOME
import com.skylar.insuranceclaimmanagementsystem.navigation.ROUTE_SPLASH
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController, authViewModel: AuthViewModel) {
    val scope = rememberCoroutineScope()
    var authState by remember { mutableStateOf<AuthState>(AuthState.Idle) }

    // Collect authentication state
    LaunchedEffect(Unit) {
        authState = authViewModel.authState.value
        if (authState is AuthState.Authenticated) {
            navController.navigate(ROUTE_DASHBOARD) {
                popUpTo(ROUTE_HOME) { inclusive = true }
            }
        }
    }

    // Animation states
    val alphaAnim by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )
    val scaleAnim = remember { Animatable(0.8f) }

    LaunchedEffect(Unit) {
        scaleAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
        delay(1200)

        scope.launch {
            val destination = if (authState is AuthState.Authenticated) ROUTE_DASHBOARD else ROUTE_HOME
            navController.navigate(destination) {
                popUpTo(ROUTE_SPLASH) { inclusive = true }
            }
        }
    }

    // Splash UI
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.height(16.dp))

            // Text with fade-in animation
            Text(
                text = "Insurance Claim Management App",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black,
                modifier = Modifier.graphicsLayer(alpha = alphaAnim)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()
    SplashScreen(navController, authViewModel)
}

