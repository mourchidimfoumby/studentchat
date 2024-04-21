package com.example.data.model

sealed class DataEvent<T> {
    data class Add<T>(val data: T): DataEvent<T>()
    data class Modify<T>(val data: T): DataEvent<T>()
    data class Remove<T>(val data: T): DataEvent<T>()
}