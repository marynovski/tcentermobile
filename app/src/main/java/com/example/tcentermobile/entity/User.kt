package com.example.tcentermobile.entity

class User(id: Int, username: String, token: String) {

    var id: Int = id
        get() = field
        set(id) { field = id }

    var username : String = username
        get() = field
        set(username) { field = username }

    var token : String = token
        get() = field
        set(username) { field = username }
}