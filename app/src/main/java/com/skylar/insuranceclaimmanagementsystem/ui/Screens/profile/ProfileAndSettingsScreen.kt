package com.skylar.insuranceclaimmanagementsystem.ui.Screens.profile


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.skylar.insuranceclaimmanagementsystem.navigation.ROUTE_DASHBOARD
import com.skylar.insuranceclaimmanagementsystem.navigation.ROUTE_PROFILE

data class User(
    val id: String,
    val name: String,
    val email: String,
    val claimHistory: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, user: User, userId: String) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Profile") })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text("User ID: ${user.id}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))

                Text("Name: ${user.name}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))

                Text("Email: ${user.email}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))

                Text("Claim History:", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(user.claimHistory, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(24.dp))

                // Dashboard button
                Button(
                    onClick = {
                        navController.navigate(ROUTE_DASHBOARD)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Go to Dashboard")
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Settings button
                Button(
                    onClick = {
                        navController.navigate(ROUTE_PROFILE)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Go to Settings")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val dummyUser = User(
        id = "123",
        name = "Sirleaf Nandwa",
        email = "ObiloSirleaf@gmail.com",
        claimHistory = "No claims yet."
    )

    val navController = rememberNavController()
    ProfileScreen(navController = navController, user = dummyUser, userId = "123")
}
