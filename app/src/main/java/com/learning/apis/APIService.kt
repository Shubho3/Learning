package com.learning.apis

import com.learning.IMDB
import com.learning.model.LoginDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface APIService {
/*    @FormUrlEncoded
@POST("search")
  suspend fun imdbFunction(
       @Field("search") APIKey : String,
       @Field("user_id") searchExpression : String,
   ) : Response<IMDB>*/
@GET("get_managerList")
  suspend fun get_managerList(
      @QueryMap params: Map<String, String>) : Response<IMDB>
    @FormUrlEncoded
    @POST("login")
    suspend  fun login(@FieldMap params: Map<String, String>): Response<LoginDto>
}