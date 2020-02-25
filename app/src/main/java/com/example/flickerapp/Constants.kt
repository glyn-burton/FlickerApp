package com.example.flickercodetest

const val API_KEY = "6bf318919bbbc455f3573d18798a58e3"

var SEARCH_TERM = ""

val URL = "https://www.flickr.com/services/rest/?method=flickr.photos." +
        "search&api_key=6bf318919bbbc455f3573d18798a58e3&tags=$SEARCH_TERM&format=json&nojsoncallback=1"