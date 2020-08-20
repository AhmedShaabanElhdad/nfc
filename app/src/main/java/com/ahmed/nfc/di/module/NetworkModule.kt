package com.jai.blueprint.di.module

import android.content.Context
import com.jai.blueprint.data.network.NetworkConnectionInterceptor
import com.jai.blueprint.data.source.NetworkCall
import com.jai.blueprint.utils.AppConstant.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.*
import javax.security.cert.CertificateException


object RestAdapter {

    // Create a trust manager that does not validate certificate chains
    val unsafeOkHttpClient:

    // Install the all-trusting trust manager

    // Create an ssl socket factory with our all-trusting manager
            OkHttpClient.Builder
        get() = try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts =
                arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )

            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(object : HostnameVerifier {
                override fun verify(hostname: String?, session: SSLSession?): Boolean {
                    return true
                }
            })
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
}

@Module
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideNetworkConnectionInterceptor(mContext: Context): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(mContext)
    }


    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(RestAdapter.unsafeOkHttpClient.build())
            .build()
    }


    @Provides
    @Singleton
    internal fun provideMovieApi(retrofit: Retrofit): NetworkCall {
        return retrofit.create(NetworkCall::class.java)
    }

//    @Provides
//    @Singleton
//    internal fun provideTrailerApi(retrofit: Retrofit): TrailerApi {
//        return retrofit.create(TrailerApi::class.java)
//    }


}