package com.senac.turismo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenManager(val route: String,
                          val name: String,
                           val icon: ImageVector
) {
    object Home : ScreenManager("home/{Id}", "Home", Icons.Filled.Home)
    object About : ScreenManager("about", "About", Icons.Filled.AccountBox)
    object Pessoa : ScreenManager("pessoa", "Pessoa", Icons.Filled.Face)
    object Travel : ScreenManager("travel", "Travel", Icons.Filled.TravelExplore)
    object FormTravel : ScreenManager("formTravel", "Form Travel", Icons.Filled.DynamicForm)

    object SystemNavigation : ScreenManager("systemNavigation", "System Navigation", Icons.Filled.Password)

    object Login : ScreenManager("login", "Login", Icons.Filled.Face)
    object Register : ScreenManager("register", "Register", Icons.Filled.Face)
    object ForgotPassword : ScreenManager("forgotPassword", "Forgot Password", Icons.Filled.Password)


}