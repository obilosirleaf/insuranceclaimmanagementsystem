package com.skylar.insuranceclaimmanagementsystem.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.skylar.insuranceclaimmanagementapp.data.AuthViewModel
import com.skylar.insuranceclaimmanagementsystem.ui.Screens.dashboard.DashboardScreen
import com.skylar.insuranceclaimmanagementsystem.ui.Screens.dialogue.ClaimSubmissionDialogScreen
import com.skylar.insuranceclaimmanagementsystem.ui.Screens.home.HomeScreen
import com.skylar.insuranceclaimmanagementsystem.ui.Screens.profile.ProfileScreen
import com.skylar.insuranceclaimmanagementsystem.ui.Screens.login.LoginOrSignUpScreen
import com.skylar.insuranceclaimmanagementsystem.ui.Screens.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUTE_SPLASH) {
            SplashScreen(
                navController, authViewModel,

            )
        }
        composable(ROUTE_LOGIN) {
            LoginOrSignUpScreen(navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(ROUTE_PROFILE) {
            ProfileScreen(
                navController = navController, userId = "123",
                user = TODO()
            ) // Replace with actual user ID if needed
        }
        composable(ROUTE_DIALOGUE) {
            ClaimSubmissionDialogScreen(navController)
        }
        composable(ROUTE_DASHBOARD) {
            DashboardScreen(navController)
        }
    }
}
