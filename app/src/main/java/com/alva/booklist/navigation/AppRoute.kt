package com.alva.booklist.navigation

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
sealed class AppRoute private constructor() {
    @Serializable
    data object Home : AppRoute()

    @Serializable
    data class Detail(val bookIndex: Int) : AppRoute()
}

//Use this class to serialize and deserialize custom objects when passing data through navigation
class CustomNavType<T>(private val serializer: KSerializer<T>) : NavType<T>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getString(key)?.let { Json.decodeFromString(serializer, it) }
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, Json.encodeToString(serializer, value))
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(serializer, value)
    }
}