package com.senac.turismo
import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HdrPlus
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.senac.turismo.model.TipoViagem
import com.senac.turismo.model.Viagem
import java.text.DecimalFormat
import java.time.LocalDate
import androidx.navigation.compose.NavHost
import com.senac.turismo.viewModel.PessoaViewModel
import com.senac.turismo.viewModel.PessoaViewModelFactory
import com.senac.turismo.viewModel.ViagemViewModel
import com.senac.turismo.viewModel.ViagemViewModelFactory

@Composable
fun TravelCompose(navController: NavController, id: Int? ) {
    val context = LocalContext.current;
    val app = context.applicationContext as Application;
    val viagem: ViagemViewModel = viewModel(
        factory = ViagemViewModelFactory(app)
    )


    var initValue : List<Viagem> = listOf();
    var allViagens by remember {
        mutableStateOf(initValue)
    }

    viagem.findAll(id?: 0,
        onSuccess = {
            allViagens = it;
        }
    )

    LazyColumn(){

        items(items = allViagens) {
                p -> ViagemView(p, navController)
        }
    }

    ExtendedFloatingActionButton(
        icon = { Icon(Icons.Filled.Add,"") },
        text = { Text("") },
        onClick = {
            navController.navigate("${ScreenManager.FormTravel.route}/$id")
        },
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        contentColor = Color.Black,
        backgroundColor = Color.Green,
        shape = CircleShape,
    )
}

@Composable
fun ViagemView(viagem: Viagem, navController: NavController) {
    val df = DecimalFormat("0.00")
    val context = LocalContext.current
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable {
                Toast.makeText(context,
                    "Viagem: ${viagem.destino}",
                    Toast.LENGTH_SHORT).show()
            }
    ) {
        Row() {
            Column(modifier = Modifier
                .padding(16.dp)
                .weight(1f)
            ) {
                Text(text = viagem.destino.toString())
                Text(
                    text = "Partida: ${viagem.dataPartida}  -  Chagada: ${viagem.dataChegada}"
                )
                Text(text = "Orçamento: R$ ${df.format(viagem.orcamento)}")
            }
        }
    }
}