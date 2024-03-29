package uz.gita.dima.apicontactcompose.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import uz.gita.dima.apicontactcompose.navigation.NavigationHandler
import uz.gita.dima.apicontactcompose.presenter.splash.SplashScreen
import uz.gita.dima.apicontactcompose.theme.ApiContactComposeTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationHandler: NavigationHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiContactComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigator(screen = SplashScreen()) { navigator->
                        LaunchedEffect(key1 = navigator){
                            navigationHandler.navigationBuffer
                                .onEach { it(navigator) }
                                .collect()
                        }
                        CurrentScreen()
                    }
                }
            }
        }
    }
}