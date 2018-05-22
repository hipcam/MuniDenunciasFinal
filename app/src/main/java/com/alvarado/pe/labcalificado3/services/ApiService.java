package com.alvarado.pe.labcalificado3.services;

import com.alvarado.pe.labcalificado3.clases.ResponseMessage;
import com.alvarado.pe.labcalificado3.models.Ciudadano;
import com.alvarado.pe.labcalificado3.models.Reporte;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Alumno on 11/05/2018.
 */

public interface ApiService {
    String API_BASE_URL = " https://labcal3-alessandrafernanda-alvarado.c9users.io/";
    @GET("api/v1/reportes")
    Call<List<Reporte>> getReportes();

    @FormUrlEncoded
    @POST("/api/v1/login")
    Call<Ciudadano> Login(@Field("idciudadano") String user,
                          @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/v1/reportes")
    Call<ResponseMessage> createReporte(@Field("idciudadanos") String idciudadano,
                                         @Field("titulo") String titulo,
                                         @Field("desc") String desc,
                                         @Field("ubicacion") String ubicacion);

    @Multipart
    @POST("/api/v1/reportes")
    Call<ResponseMessage> createReporteWithImage(
            @Part("idciudadano") RequestBody idciudadano,
            @Part("titulo") RequestBody titulo,
            @Part("desc") RequestBody desc,
            @Part("ubicacion") RequestBody ubicacion,
            @Part MultipartBody.Part foto
    );
    @DELETE("/api/v1/reportes/{id}")
    Call<ResponseMessage> destroyReporte(@Path("id") Integer id);

    @GET("api/v1/reportes/{id}")
    Call<Reporte> showReporte(@Path("id") Integer id);
}
