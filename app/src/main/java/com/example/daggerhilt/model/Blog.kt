package com.example.daggerhilt.model


//main model that is common withn the app
data class Blog(
    var id: Int,
    var title: String,
    var body: String,
    var image: String,
    var category: String
)
