package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class emp_complaints(
        val subject: String,
        val description: String,
        val status: String,
        val cre_date: String,
        val res_date: String
)

@Serializable
data class tickarray(
    val complaint_id: Int = 0,
    val emp_id: Int = 0,
    val subject: String,
    val description: String,
    val status: String,
    val cre_date: String,
    val res_date: String
)

@Serializable
data class employee_data
    (
        val emp_id: Int = 0,
        val employee_name: String,
        val email: String,
        val department: String
)

@Serializable
data class employees
    (
        val employee_name: String,
        val email: String,
        val department: String
)

@Serializable
data class comms_data
    (
    val comment_id: Int = 0,
    val complaint_id: Int = 0,
    val comms_text: String,
    val comms_date: String
)

@Serializable
data class comms
    (
        val comms_text: String,
        val comms_date: String
)

@Serializable
data class resModel
    (
        var code: Int,
        var status: String
)
