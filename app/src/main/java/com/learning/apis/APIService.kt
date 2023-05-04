package com.learning.apis

import com.learning.IMDB
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {
    @FormUrlEncoded
@POST("search")
  suspend fun imdbFunction(
       @Field("search") APIKey : String,
       @Field("user_id") searchExpression : String,
   ) : Response<IMDB>

}