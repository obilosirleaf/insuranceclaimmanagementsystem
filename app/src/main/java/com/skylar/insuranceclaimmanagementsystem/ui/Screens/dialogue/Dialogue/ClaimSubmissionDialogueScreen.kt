package com.skylar.insuranceclaimmanagementsystem.ui.Screens.dialogue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.skylar.insuranceclaimmanagementsystem.navigation.ROUTE_DASHBOARD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimSubmissionDialogScreen(navController: NavController) {
    var incidentDescription by remember { mutableStateOf("") }
    var claimAmount by remember { mutableStateOf("") }
    var incidentDate by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var validationError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Show Alert Dialog after submission
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Claim Submitted") },
            text = { Text("Your claim has been successfully submitted.") },
            confirmButton = {
                Button(
                    onClick = {
                        // Clear form fields after submission
                        incidentDescription = ""
                        claimAmount = ""
                        incidentDate = ""
                        showDialog = false
                        navController.popBackStack()
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Claim Submission") })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text("Submit a Claim", style = MaterialTheme.typography.headlineSmall)

                Spacer(modifier = Modifier.height(16.dp))

                // Incident Description Input
                OutlinedTextField(
                    value = incidentDescription,
                    onValueChange = { incidentDescription = it },
                    label = { Text("Incident Description") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 4,
                    isError = validationError,
                    supportingText = {
                        if (validationError) {
                            Text(
                                text = errorMessage,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Claim Amount Input
                OutlinedTextField(
                    value = claimAmount,
                    onValueChange = { claimAmount = it },
                    label = { Text("Claim Amount") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    isError = validationError
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Incident Date Input
                OutlinedTextField(
                    value = incidentDate,
                    onValueChange = { incidentDate = it },
                    label = { Text("Date of Incident (DD/MM/YYYY)") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = validationError
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Submit Claim Button
                Button(
                    onClick = {
                        // Validate fields
                        validationError = false
                        errorMessage = ""

                        // Check if fields are filled and validate date format
                        when {
                            incidentDescription.isBlank() -> {
                                validationError = true
                                errorMessage = "Incident Description cannot be empty."
                            }
                            claimAmount.isBlank() -> {
                                validationError = true
                                errorMessage = "Claim Amount cannot be empty."
                            }
                            incidentDate.isBlank() -> {
                                validationError = true
                                errorMessage = "Incident Date cannot be empty."
                            }
                            !incidentDate.matches(Regex("\\d{2}/\\d{2}/\\d{4}")) -> {
                                validationError = true
                                errorMessage = "Please enter a valid date in DD/MM/YYYY format."
                            }
                        }

                        // If validation passed, show dialog
                        if (!validationError) {
                            showDialog = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Submit")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Navigate to Dashboard button
                Button(
                    onClick = {
                        navController.navigate(ROUTE_DASHBOARD)  // Navigate to Dashboard
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Go to Dashboard")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ClaimSubmissionDialogScreenPreview() {
    val navController = rememberNavController()
    ClaimSubmissionDialogScreen(navController = navController)
}
