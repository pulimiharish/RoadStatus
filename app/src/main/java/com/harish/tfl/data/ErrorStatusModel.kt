package com.harish.tfl.data

import com.google.gson.annotations.SerializedName

data class ErrorStatusModel(
    @SerializedName("$" + "type") val type: String,
    @SerializedName("timestampUtc") val timestampUtc: String,
    @SerializedName("exceptionType") val exceptionType: String,
    @SerializedName("httpStatusCode") val httpStatusCode: Int,
    @SerializedName("httpStatus") val httpStatus: String,
    @SerializedName("relativeUri") val relativeUri: String,
    @SerializedName("message") val message: String
)