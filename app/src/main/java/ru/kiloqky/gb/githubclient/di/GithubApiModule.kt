package ru.kiloqky.gb.githubclient.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.kiloqky.gb.githubclient.model.api.GithubApi
import javax.inject.Named
import javax.inject.Singleton

@Module
class GithubApiModule {

    @Named("baseUrl")
    @Provides
    fun baseurl(): String = "https://api.github.com"

    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): GithubApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GithubApi::class.java)

    @Singleton
    @Provides
    fun gson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

}