package ru.kiloqky.gb.githubclient.scheduler

object SchedulersFactory {
    fun create(): Schedulers = DefaultSchedulers()
}