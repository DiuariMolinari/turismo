package com.senac.turismo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
//declara state by delagation
import com.senac.turismo.ui.theme.TurismoTheme
import com.senac.turismo.componentes.PasswordField
import com.senac.turismo.viewModel.PessoaViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TurismoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp();
                }
            }
        }
    }
}

@Composable
fun MyApp(){
    val navController = rememberNavController()
    LoginNavigation(navController)
}

@Composable
fun Navigation(navController: NavHostController, ){
    val screenList = listOf(
        ScreenManager.Home,
        ScreenManager.Profile,
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
    ) { innerPadding ->
        NavHost(navController, startDestination = ScreenManager.Home.route, Modifier.padding(innerPadding)) {
            composable(ScreenManager.Home.route) { HomeCompose(navController) }
            composable(ScreenManager.About.route) { AboutCompose(navController) }
            profileGraph(navController)
        }
    }
}

fun NavGraphBuilder.profileGraph(navController: NavHostController) {
    navigation(startDestination = "principal", route = "profile") {
        composable("principal") { ProfileCompose(navController) }
        composable("form/{profileId}",
            arguments = listOf(
                navArgument("profileId") {
                    type = NavType.IntType
                } // ,
/*                    navArgument("other") {
                        type = NavType.FloatType
                    }, */
            )
        ) {
            val id = it.arguments?.getInt ("profileId")
            // val other = it.arguments?.getFloat("other")
            ProfileFormCompose(navController, id)
        }
    }
}

@Composable
fun LoginNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = ScreenManager.Login.route) {
        composable(ScreenManager.Login.route) { LoginCompose(navController) }
        composable(ScreenManager.ForgotPassword.route) { ForgotPasswordCompose(navController) }
        composable(ScreenManager.Register.route) { RegisterCompose(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TurismoTheme {
        MyApp();
    }
}