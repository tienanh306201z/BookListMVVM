package com.alva.booklist.constants

object AppRegexes {
    val httpImageRegex = Regex("http(.*)(.png|.jpg|.svg|.gif|.jpeg)")
    val httpRegex = Regex("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    val upperHeadLetterRegex = Regex("(?=\\p{Upper})")
    val escapedUnicodeRegex = Regex("\\\\u([0-9A-Fa-f]{4})")
    val htmlRegex = Regex("<[^>]+>.*?</[^>]+>")
    val spaceRegex = Regex("\\s+")
    val romanNumeralRegex = Regex("^(I|II|III|IV|V|VI|VII|VIII|IX|X)$")
    val numberRegex = Regex("^[0-9]+$")
    val mailRegex = Regex("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
}