package com.senac.turismo
import android.app.Application
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.senac.turismo.componentes.PasswordField
//declara state by delagation
import com.senac.turismo.ui.theme.TurismoTheme
import com.senac.turismo.viewModel.PessoaViewModel
import com.senac.turismo.viewModel.PessoaViewModelFactory
import java.net.PasswordAuthentication

@Composable
fun LoginCompose(navController: NavController) {
    val context = LocalContext.current;
    val app = context.applicationContext as Application;
    val pessoa: PessoaViewModel = viewModel(
        factory = PessoaViewModelFactory(app)
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment= Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.aviao),
            contentDescription = "LoginImage",
        )
        OutlinedTextField(value = pessoa.usuario, onValueChange = { pessoa.usuario =it; })
        PasswordField(value = pessoa.senha, onChange = { pessoa.senha = it});

        OutlinedButton(onClick =  {
            if (pessoa.usuario.equals("admin") && pessoa.senha.equals("admin")) {
                Toast.makeText(context, "Login ok", Toast.LENGTH_SHORT).show()
                navController.navigate(ScreenManager.Home.route)
            }
            else {
                Toast.makeText(context, "Login inválido", Toast.LENGTH_LONG).show();
            }
        }) {
            Text(text = "Login")
        }

        Row(Modifier.padding(all= 60.dp)) {
            OutlinedButton(onClick =  {
                navController.navigate(ScreenManager.Register.route) {

                }

            }) {
                Text(text = "Registrar")
            }

            OutlinedButton(onClick =  {
                navController.navigate(ScreenManager.ForgotPassword.route) {

                }
            }) {
                Text(text = "Esqueci minha senha")
            }
        }
    }
}
