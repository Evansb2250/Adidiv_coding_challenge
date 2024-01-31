package com.example.a20240130_samuelbrandenburg_nycschools.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SchoolDetails(
    @SerialName("dbn") val id: String = "",
    @SerialName("school_name")  val schoolName: String = "",
    @SerialName("sat_critical_reading_avg_score")   val readingScore: String =  "",
    @SerialName("sat_math_avg_score") val writingScore: String = "",
    @SerialName("sat_writing_avg_score") val mathScore: String = "",
)
