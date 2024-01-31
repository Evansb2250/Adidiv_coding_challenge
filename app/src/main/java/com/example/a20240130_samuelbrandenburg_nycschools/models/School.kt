package com.example.a20240130_samuelbrandenburg_nycschools.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class School(
    @SerialName("dbn") val id: String,
    @SerialName("school_name") val name: String,
)
