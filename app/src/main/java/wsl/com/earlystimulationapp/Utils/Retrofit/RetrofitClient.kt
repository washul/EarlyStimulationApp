package wsl.com.earlystimulationapp.Utils.Retrofit

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import wsl.com.earlystimulationapp.Utils.*
import kotlin.coroutines.coroutineContext

object RetrofitClient {

    private var retrofit: Retrofit? = null

    val instance: Retrofit
        get() {
            
            if ( retrofit == null ) {

                val client = OkHttpClient
                    .Builder()
                    .addInterceptor( RequestInterceptorAddHeaders() )
                    .addInterceptor( RequestInterceptorSetJsonModel() )
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl( URL_BASE )
                    .client( client )
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            }

            return retrofit!!

        }

}

private class RequestInterceptorAddHeaders : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("baby_id", BABY_ID)
            .build()

        val requestBuilder = original.newBuilder()
            .addHeader("Authorization", TOKEN)
            .url(url)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }

}

private class RequestInterceptorSetJsonModel : Interceptor {

    val JSON = MediaType.parse("application/json; charset=utf-8")
    val GSON = Gson()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        val body = response.body()
        val bodyString = body!!.string()

        Log.e(TAG, bodyString )

        val data = GSON.fromJson( bodyString, Data::class.java )

        val apiResponse: ApiResponseModel = data.data
        body.close()

        val newResponse = response.newBuilder()
            .body( ResponseBody.create(JSON, ( apiResponse.activities ?: apiResponse.articles ?: apiResponse.article ).toString()))

        return newResponse.build()

    }

}

data class Data(
    val data: ApiResponseModel
)

data class ApiResponseModel(

    val id: Int?,
    val name: String,
    val type: String?,
    val activities: JsonArray?,
    val articles: JsonArray?,
    val article: JsonObject?,
    val meta: JsonObject

)