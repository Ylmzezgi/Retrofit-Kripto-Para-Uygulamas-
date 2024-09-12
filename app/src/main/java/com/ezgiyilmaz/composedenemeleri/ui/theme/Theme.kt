package com.ezgiyilmaz.composedenemeleri.ui.theme

import android.graphics.LightingColorFilter
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import retrofit2.Retrofit

class Theme {
    private val DarkColorPalette = darkColors(
        primary = Purple500,
        primaryVariant = Purple700,
        secondary = Teal200
    )

    private val LightingColorPalette = lightColors(
        primary = Purple500,
        primaryVariant = Purple700,
        secondary = Teal200,
        background = Color.White

    )

    @Composable
    fun RetrofitComposeTheme(
        darkTheme:Boolean= isSystemInDarkTheme(),
        content :@Composable() ()->Unit
    ){
        val colors=if(darkTheme){
            DarkColorPalette
        }else{
            LightingColorPalette
        }
        MaterialTheme(
            colors=colors,
            typography =Typography,
            //shapes = Shapes,
            content=content
        )
    }
}