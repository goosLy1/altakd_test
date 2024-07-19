package com.example.altakidtest.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.altakidtest.R

@Composable
fun AuthForm(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        value = email,
        onValueChange = onEmailChange,
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
        label = { Text(text = stringResource(id = R.string.email)) },
        isError = isError,
        maxLines = 1,
        shape = RoundedCornerShape(32)
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        value = password,
        onValueChange = onPasswordChange,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
        label = { Text(text = stringResource(id = R.string.password)) },
        visualTransformation = PasswordVisualTransformation(),
        isError = isError,
        maxLines = 1,
        shape = RoundedCornerShape(32)
    )
}