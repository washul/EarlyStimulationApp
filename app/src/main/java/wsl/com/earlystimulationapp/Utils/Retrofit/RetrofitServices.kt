package wsl.com.earlystimulationapp.Utils.Retrofit

import io.reactivex.Observable
import retrofit2.http.*
import wsl.com.earlystimulationapp.Data.Room.Entity.EActivity


interface ServicesData{

    @GET("activities")
    fun getActivities(): Observable<List<EActivity>>
//
//    @POST("update-item")
//    fun update(@Header("auth") token: String, @Header("uuid_partner_") uuid_partner_: String, @Body item: EItem): Observable<CloudMessage>
//
//    @Multipart
//    @POST("upload-image/")
//    fun uploadImage(@Header("auth") token: String, @Header("uuid_partner_") uuid_partner_: String, @Header("uuid_item") uuid_item: String, @Part file: MultipartBody.Part ): Observable<CloudMessage>
//
//    @POST("insert-item/")
//    fun insert(@Header("auth") token: String, @Header("uuid_partner_") uuid_partner_: String, @Body item: EItem): Observable<CloudMessage>

}

