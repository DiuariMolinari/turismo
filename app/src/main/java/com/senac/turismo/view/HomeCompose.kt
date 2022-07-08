package com.senac.turismo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
//declara state by delagation
import com.senac.turismo.ui.theme.TurismoTheme
import java.net.PasswordAuthentication

@Composable
fun HomeCompose(id: Int?) {
    NavigationBar(id)
}

@Composable
fun NavigationBar(id: Int?){
    val navController = rememberNavController()
    val screenList = listOf(
        ScreenManager.Home,
        ScreenManager.Travel,
        ScreenManager.About
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                screenList.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.name) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {

                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(navController, startDestination = "home") {
            composable("home") { Principal() }

            composable("formTravel/{Id}",
                arguments = listOf(
                    navArgument("Id") {
                        type = NavType.IntType
                    }
                )
            ) {
                val idf = it.arguments?.getInt ("Id") ?: id
                FormTravelCompose(navController, idf);
            }

            composable(ScreenManager.About.route) { AboutCompose(navController) }
            composable(ScreenManager.Travel.route) { TravelCompose(navController, id) }
        }
    }
}

@Composable
fun Principal(){
    Column() {
        Text(text="Home");
    }
}