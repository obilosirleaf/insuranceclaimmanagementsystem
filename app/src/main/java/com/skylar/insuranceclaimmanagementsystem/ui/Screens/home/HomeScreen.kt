package com.skylar.insuranceclaimmanagementsystem.ui.Screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.skylar.insuranceclaimmanagementsystem.navigation.ROUTE_DIALOGUE
import com.skylar.insuranceclaimmanagementsystem.navigation.ROUTE_LOGIN
import com.skylar.insuranceclaimmanagementsystem.navigation.ROUTE_PROFILE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Insurance Claim Management") })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Welcome to Insurance Claim Management", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    // Pass dummy userId; in real use, this should be dynamic
                    navController.navigate("$ROUTE_PROFILE/123")
                }) {
                    Text("View Claims")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    navController.navigate(ROUTE_DIALOGUE)
                }) {
                    Text("Submit New Claim")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUTE_LOGIN) {
                        popUpTo(ROUTE_LOGIN) { inclusive = true }
                    }
                }) {
                    Text("Log Out")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
