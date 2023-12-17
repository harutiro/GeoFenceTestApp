package net.harutiro.geofencetestapp

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.location.Geofence
import net.harutiro.geofencetestapp.ViewModel.GeofenceRepository
import net.harutiro.geofencetestapp.ViewModel.MainViewModel
import net.harutiro.geofencetestapp.ui.MainScreen
import net.harutiro.geofencetestapp.ui.theme.GeoFenceTestAppTheme
import org.osmdroid.config.Configuration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // mapを表示するための設定
        Configuration.getInstance().load(
            this,
            PreferenceManager.getDefaultSharedPreferences(this)
        )

        // TODO:Staticで持ってくるのを止める。
        MainViewModel.activity = this

        setContent {
            GeoFenceTestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
