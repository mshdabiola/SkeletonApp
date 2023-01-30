package com.mshdabiola.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.serialization.kotlinx.json.json
import java.io.File

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun clientProvider() = HttpClient(Android) {
        install(Resources)
        install(Logging) {
        }
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            this.url("")
        }
        install(UserAgent) {
            agent = "my app"
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(5)
            exponentialDelay()
        }
        install(HttpCache) {
            val file = File.createTempFile("abiola", "tem")
            publicStorage(FileStorage(file))
        }
    }
}
