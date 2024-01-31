package com.example.a20240130_samuelbrandenburg_nycschools.nav

sealed class Routes(
    val destination: String,
) {
    object HomeScreenRoute: Routes(
        "HomeScreen"
    )

    object DetailScreenRoute: Routes(
        "DetailScreen/{schoolId}"
    )
}