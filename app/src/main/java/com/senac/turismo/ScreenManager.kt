package com.senac.turismo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Password
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenManager(val route: String,
                          val name: String,
                           val icon: ImageVector
) {
    object Home : ScreenManager("home", "Home", Icons.Filled.Home)
    object Profile : ScreenManager("profile", "Profile", Icons.Filled.Build)
    object Pessoa : ScreenManager("pessoa", "Pessoa", Icons.Filled.Face)

    object SystemNavigation : ScreenManager("systemNavigation", "System Navigation", Icons.Filled.Password)

    object Login : ScreenManager("login", "Login", Icons.Filled.Face)
    object Register : ScreenManager("register", "Register", Icons.Filled.Face)
    object ForgotPassword : ScreenManager("forgotPassword", "Forgot Password", Icons.Filled.Password)


}