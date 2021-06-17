package ru.kiloqky.gb.githubclient.model.imageloader

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}