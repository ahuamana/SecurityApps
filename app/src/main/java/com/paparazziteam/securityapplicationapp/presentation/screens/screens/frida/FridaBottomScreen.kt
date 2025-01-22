package com.paparazziteam.securityapplicationapp.presentation.screens.screens.frida

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FridaBottomScreen(
    modifier: Modifier = Modifier,
    onCheckAdb: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
    ) {


        Spacer(modifier = Modifier.height(16.dp))

        // Frida Check ADB is enabled
        Button(
            onClick = { onCheckAdb() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Check ADB is enabled")
        }
    }
}