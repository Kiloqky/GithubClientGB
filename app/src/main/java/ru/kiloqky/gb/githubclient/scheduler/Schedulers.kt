package ru.kiloqky.gb.githubclient.scheduler

import io.reactivex.rxjava3.core.Scheduler

interface Schedulers {

    fun background(): Scheduler
    fun main(): Scheduler

}