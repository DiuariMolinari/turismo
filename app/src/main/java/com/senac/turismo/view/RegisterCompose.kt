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
import com.senac.turismo.componentes.PasswordField
//declara state by delagation
import com.senac.turismo.ui.theme.TurismoTheme
import com.senac.turismo.viewModel.PessoaViewModel
import com.senac.turismo.viewModel.PessoaViewModelFactory
import java.net.PasswordAuthentication

@Composable
fun RegisterCompose(navController: NavController) {
    val context = LocalContext.current;
    val app = context.applicationContext as Application;
    val pessoa: PessoaViewModel = viewModel(
        factory = PessoaViewModelFactory(app)
    )

    var confirmaSenha = remember {
        mutableStateOf("")
    }

    fun isValidFields(): Boolean {
        var errorMessage = "";

        if (pessoa.nome.isNullOrEmpty())
            errorMessage += "Campo Nome obrigatório! \r\n";

        if (pessoa.email.isNullOrEmpty())
            errorMessage += "Campo Email obrigatório! \r\n";

        if (pessoa.usuario.isNullOrEmpty())
            errorMessage += "Campo Usuário obrigatório! \r\n";

        if (pessoa.senha.isNullOrEmpty())
            errorMessage += "Campo Senha obrigatório! \r\n";

        if (pessoa.senha != confirmaSenha.value)
            errorMessage += "Senhas não conferem! \r\n";

        if (!errorMessage.isNullOrEmpty())
        {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            return false;
        }
        return true;
    }

    fun clearFields() {
        pessoa.usuario = "";
        pessoa.email = "";
        pessoa.nome = "";
        pessoa.senha = "";
        confirmaSenha.value  = "";
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment= Alignment.CenterHorizontally, ) {
        Text(text = "Nome")
        OutlinedTextField(value = pessoa.nome, onValueChange = { pessoa.nome = it; })

        Text(text = "Email")
        OutlinedTextField(value = pessoa.email, onValueChange = { pessoa.email = it; })

        Text(text = "Usuário")
        OutlinedTextField(value = pessoa.usuario, onValueChange = { pessoa.usuario = it; })

        Text(text = "Senha")
        PasswordField(value = pessoa.senha, onChange = { pessoa.senha = it});

        Text(text = "Confirmar senha")
        PasswordField(value = confirmaSenha.value, onChange = { confirmaSenha.value = it});

        Row(Modifier.padding(all= 60.dp)) {
            Button( onClick = { navController.navigateUp() }) {
                Text(text = "Voltar")
            }

            Button(modifier = Modifier.padding(start = 20.dp) ,onClick = {
                if (isValidFields())
                {
                    pessoa.save()
                    Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                    clearFields();
                }
            }) {
                Text(text = "Cadastrar")
            }
        }
    }
}

