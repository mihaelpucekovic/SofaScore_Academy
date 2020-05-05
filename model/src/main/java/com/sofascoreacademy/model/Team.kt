package com.sofascoreacademy.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Team(@field:SerializedName("id") val id: Int, @field:SerializedName("name") val name: String) : Serializable