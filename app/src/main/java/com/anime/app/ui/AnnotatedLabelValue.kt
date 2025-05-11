package com.anime.app.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

@Composable
fun Label(label: String, value: String) {
    Text(
        buildAnnotatedString {
            append(label)
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(value)
            }
        }
    )
}