package br.com.rrodovalho.data.di

import androidx.room.Room
import br.com.rrodovalho.data.BuildConfig
import br.com.rrodovalho.data.repository.MarvelRepositoryImpl
import br.com.rrodovalho.data.repository.local.LocalRepository
import br.com.rrodovalho.data.repository.local.db.LocalDatabase
import br.com.rrodovalho.data.repository.remote.RemoteRepository
import br.com.rrodovalho.data.repository.remote.api.ApiContract
import br.com.rrodovalho.domain.repository.MarvelRepository
import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val KOIN_NAMED_LOCAL_REPOSITORY = named("local")
val KOIN_NAMED_REMOTE_REPOSITORY = named("remote")

val dataModule = module {
    single(KOIN_NAMED_LOCAL_REPOSITORY) { LocalRepository(get()) }
    single(KOIN_NAMED_REMOTE_REPOSITORY) { RemoteRepository(get()) }
    single { MarvelRepositoryImpl(get(KOIN_NAMED_LOCAL_REPOSITORY), get(KOIN_NAMED_REMOTE_REPOSITORY)) as MarvelRepository }
    single { Gson() }
    single {
        Room.databaseBuilder(androidApplication(),
            LocalDatabase::class.java,
            "marvelapp.db")
            .build()
    }
    single { get<LocalDatabase>().comicsDetailDao() }
    single {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG ) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val original: Request = chain.request()
                val originalHttpUrl: HttpUrl = original.url()

                val url: HttpUrl = originalHttpUrl.newBuilder()
                    .addQueryParameter(BuildConfig.apiKeyParam, BuildConfig.apiKeyValue)
                    .addQueryParameter(BuildConfig.tsParam, BuildConfig.tsValue)
                    .addQueryParameter(BuildConfig.hashParam, BuildConfig.hashValue)
                    .build()

                // Request customization: add request headers
                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)

                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }
    single {
        val okHttpClient: OkHttpClient = get()
        Retrofit.Builder()
            .baseUrl(ApiContract.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        val retrofit: Retrofit = get()
        retrofit.create(ApiContract::class.java)
    }
}