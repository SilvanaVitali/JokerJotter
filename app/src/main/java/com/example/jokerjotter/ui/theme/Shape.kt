package com.example.jokerjotter.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp),
    medium = CutCornerShape(25.dp),
    extraLarge = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
    extraSmall = RoundedCornerShape(topStart = 25.dp, bottomEnd = 25.dp)
)