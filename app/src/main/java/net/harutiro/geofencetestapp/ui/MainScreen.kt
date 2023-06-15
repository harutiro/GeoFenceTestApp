package net.harutiro.geofencetestapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.harutiro.geofencetestapp.ViewModel.MainViewModel
import net.harutiro.geofencetestapp.ui.theme.GeoFenceTestAppTheme

@Composable
fun MainScreen(mainViewModel:MainViewModel = viewModel()){

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
                mainViewModel.startGeofence()
            }
        ) {
            Text("Hello World!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GeoFenceTestAppTheme {
        MainScreen()
    }
}