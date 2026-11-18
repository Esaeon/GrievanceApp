package com.example.grievanceapp.auth

enum class UserRole { Student, Admin }

// PLACEHOLDER for testing only. Replace with a call to the university's
// login service, which should return the role.
fun authenticate(username: String, password: String): UserRole? = when {
    username == "student" && password == "student" -> UserRole.Student
    username == "admin" && password == "admin" -> UserRole.Admin
    else -> null
}