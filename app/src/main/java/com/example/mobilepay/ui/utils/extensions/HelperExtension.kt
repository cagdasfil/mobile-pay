package com.example.mobilepay.ui.utils.extensions

inline fun <R> R?.orElse(block: () -> R): R {
    return this ?: block()
}