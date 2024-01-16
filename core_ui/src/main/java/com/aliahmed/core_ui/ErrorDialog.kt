package com.aliahmed.core_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorDialog(
    message: String?,
    onDismiss: (() -> Unit)? = null,
    onRetry: (() -> Unit)? = null,
) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
                onDismiss?.invoke()
            },
            title = {
                Text(
                    text ="An unexpected error occurred",
                    color = Color.Black
                )
            },
            text = {
                message?.let { Text(it,  color = Color.Black) }
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 12.dp, 12.dp, 24.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(modifier = Modifier,
                        onClick = {
                            openDialog.value = false
                            onDismiss?.invoke()
                        }) {
                        Text(
                            text = stringResource(id = R.string.dismiss),
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    onRetry?.let {action ->
                        Button(modifier = Modifier,
                            onClick = {
                                openDialog.value = false
                                onDismiss?.invoke()
                            }) {
                            Text(
                                text = stringResource(id = R.string.retry),
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun ErrorDialogPreview(
) {
    ErrorDialog(
        message = "test",
        onRetry = {}
    )
}