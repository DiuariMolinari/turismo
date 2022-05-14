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
//declara state by delagation
import com.senac.turismo.ui.theme.TurismoTheme
import java.net.PasswordAuthentication

@Composable
fun ProfileCompose(navController: NavController) {
    Column() {
        Text(text = "Profile")
        Button(onClick = { navController.navigate("form/1") }) {
            Text(text = "Form 1")
        }
        Button(onClick = { navController.navigate("form/2") }) {
            Text(text = "Form 2")
        }
        Button(onClick = { navController.navigate("form/3") }) {
            Text(text = "Form 3")
        }

        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Voltar")
        }
    }
    NavigationBar(navController)
}