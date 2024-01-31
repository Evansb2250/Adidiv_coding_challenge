package com.example.a20240130_samuelbrandenburg_nycschools

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a20240130_samuelbrandenburg_nycschools.nav.Routes
import com.example.a20240130_samuelbrandenburg_nycschools.screens.details_screen.SchoolDetailScreen
import com.example.a20240130_samuelbrandenburg_nycschools.screens.main_screen.SchoolScreen
import com.example.a20240130_samuelbrandenburg_nycschools.ui.theme._20240130SamuelBrandenburgNYCSchoolsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _20240130SamuelBrandenburgNYCSchoolsTheme {
                // A surface container using the 'background' color from the theme
                StartApp()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StartApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                        Text(text = "NYC School Info")
                },
            )
        }
    ) { padding ->

        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = Routes.HomeScreenRoute.destination
        ){

            composable(
                route = Routes.HomeScreenRoute.destination
            ){
                SchoolScreen(){
                    navController.navigate(Routes.DetailScreenRoute.destination.replace("{schoolId}", it))
                }
            }

            composable(
                route = Routes.DetailScreenRoute.destination
            ){
               val key = it.arguments?.getString("schoolId") ?: (return@composable)
                SchoolDetailScreen(screenId = key)
            }

        }
    }
}