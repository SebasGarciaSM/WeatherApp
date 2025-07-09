package com.example.weatherapp.ui.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun DetailText(
    title: String,
    content: String
) {
    val styledText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Light)) {
            append("$title ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(content)
        }
    }
    Text(styledText, modifier = Modifier.padding(vertical = 6.dp))
}