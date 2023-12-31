package net.harutiro.geofencetestapp.ui

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import net.harutiro.geofencetestapp.Model.LatLng
import net.harutiro.geofencetestapp.R
import net.harutiro.geofencetestapp.ViewModel.MainViewModel
import net.harutiro.geofencetestapp.ViewModel.MapSetupController
import net.harutiro.geofencetestapp.ViewModel.MapTapController
import net.harutiro.geofencetestapp.ui.theme.GeoFenceTestAppTheme
import org.osmdroid.api.IMapController
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint

@Composable
fun MainScreen(mainViewModel:MainViewModel = viewModel()){
    val context = LocalContext.current
    val activity: Activity = LocalContext.current as Activity

    val latlng: LatLng by remember { mainViewModel.latlng }.collectAsState()

    Box {
        MapView { map ->
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 100)
            } else {
                // 位置情報を取得する
                MapSetupController(context, map).setupMapWithLocation()

                // マップタップイベントを設定
                MapTapController(map) { tappedLatLng ->
                    // UIを更新せずにViewModelのデータを更新する
                    mainViewModel.setLatLngWithoutTriggeringUI(tappedLatLng)
                }
            }
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                )//右下に配置
                .align(Alignment.CenterEnd),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
//            Text("緯度:${latlng.latitude}")
//            Text("経度:${latlng.longitude}")
            Button(
                onClick = {
                    mainViewModel.startGeofence()
                }
            ) {
                Text("Start Geofence")
            }

            Button(
                onClick = {
                    mainViewModel.stopGeofence()
                }
            ) {
                Text("止める")
            }
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