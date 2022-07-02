package com.senac.turismo
import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.senac.turismo.componentes.DatePickerField
import com.senac.turismo.componentes.PasswordField
import com.senac.turismo.model.TipoViagem
//declara state by delagation
import com.senac.turismo.ui.theme.TurismoTheme
import com.senac.turismo.viewModel.PessoaViewModel
import com.senac.turismo.viewModel.PessoaViewModelFactory
import com.senac.turismo.viewModel.ViagemViewModel
import com.senac.turismo.viewModel.ViagemViewModelFactory
import java.net.PasswordAuthentication
import java.time.LocalDate

@Composable
fun FormTravelCompose(navController: NavController, id: Int) {
    val context = LocalContext.current;
    val app = context.applicationContext as Application;
    val viagem: ViagemViewModel = viewModel(
        factory = ViagemViewModelFactory(app)
    )

    fun isValidFields(): Boolean {
        var errorMessage = "";

        if (viagem.destino.isNullOrEmpty())
            errorMessage += "Campo Destino obrigatório! \r\n";

        if (viagem.dataChegada == null)
            errorMessage += "Campo Data Chegada obrigatório! \r\n";

        if (viagem.dataPartida == null)
            errorMessage += "Campo Data Partida obrigatório! \r\n";

        if (viagem.orcamento.equals(0f))
            errorMessage += "Campo Orçaamento obrigatório! \r\n";


        if (!errorMessage.isNullOrEmpty())
        {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            return false;
        }
        return true;
    }

    fun clearFields() {
        viagem.destino = "";
        viagem.dataChegada = LocalDate.now();
        viagem.dataPartida = LocalDate.now();
        viagem.orcamento = 0f;
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment= Alignment.CenterHorizontally, ) {
        Text(text = "Destino")
        OutlinedTextField( value = viagem.destino, onValueChange = { viagem.destino = it; })

        Text(text = "Orçamento")
        OutlinedTextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), value = viagem.orcamento.toString(), onValueChange = { viagem.orcamento = it.toFloat(); })

        viagem.dataPartida = DatePickerField("Data de Partida", viagem.dataPartida.toString())

        viagem.dataChegada = DatePickerField("Data de Chegada", viagem.dataChegada.toString())

        Row() {
            RadioButton(
                selected = viagem.tipo == TipoViagem.NEGOCIO,
                onClick = { viagem.tipo = TipoViagem.NEGOCIO }
            )
            Text(text = "Negócio")
            RadioButton(selected = viagem.tipo == TipoViagem.LAZER,
                onClick = { viagem.tipo = TipoViagem.LAZER})
            Text(text = "Lazer")
        }

        Row(Modifier.padding(all= 60.dp)) {
            Button( onClick = { navController.navigateUp() }) {
                Text(text = "Voltar")
            }

            Button(modifier = Modifier.padding(start = 20.dp) ,onClick = {
                if (isValidFields())
                {
                    viagem.save()
                    Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                    clearFields();
                }
            }) {
                Text(text = "Cadastrar")
            }
        }
    }

}

