package com.paparazziteam.securityapplicationapp.presentation.screens.screens.frida

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FridaBottomScreen(
    modifier: Modifier = Modifier,
    onCheckAdb: () -> Unit,
    onCheckRootBearNormal: () -> Unit,
    onCheckRootBearBusyBox: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
    ) {


        Spacer(modifier = Modifier.height(8.dp))

        // Frida Check ADB is enabled
        Button(
            onClick = { onCheckAdb() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Check ADB is enabled")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Check Root with RootBear normal
        Button(
            onClick = { onCheckRootBearNormal() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Check Root with RootBear")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Check Root with RootBear busybox
        Button(
            onClick = { onCheckRootBearBusyBox() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Check Root with RootBear busybox")
        }

    }
}

@Preview
@Composable
private fun FridaBottomScreenPrev() {
    FridaBottomScreen(
        modifier = Modifier.fillMaxSize(),
        onCheckAdb = {},
        onCheckRootBearNormal = {},
        onCheckRootBearBusyBox = {}
    )
}