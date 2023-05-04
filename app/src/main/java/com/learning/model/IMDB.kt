package com.learning
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class IMDB(
   @SerializedName("status") var searchType: String? = null,
   @SerializedName("result") var results: ArrayList<Results> = arrayListOf(),
   @SerializedName("message") var errorMessage: String? = null,
)

   data class Results(
      @Expose
      @SerializedName("id")
      val Id: String,
      @Expose
      @SerializedName("first_name")
      val First_name: String,
      @Expose
      @SerializedName("last_name")
      val Last_name: String,
      @Expose
      @SerializedName("email")
      val Email: String,
      @Expose
      @SerializedName("dob")
      val Dob: String,
      @Expose
      @SerializedName("age")
      val Age: String,
      @Expose
      @SerializedName("image")
      val Image: String,
      @Expose
      @SerializedName("mobile")
      val Mobile: String,
      @Expose
      @SerializedName("about")
      val About: String,
      @Expose
      @SerializedName("password")
      val Password: String,
      @Expose
      @SerializedName("gender")
      val Gender: String,
      @Expose
      @SerializedName("zipcode")
      val Zipcode: String,
      @Expose
      @SerializedName("bio")
      val Bio: String,
      @Expose
      @SerializedName("post_filter")
      val Post_filter: String,
      @Expose
      @SerializedName("should_not")
      val Should_not: String,
      @Expose
      @SerializedName("want_to")
      val Want_to: String,
      @Expose
      @SerializedName("age_range_from")
      val Age_range_from: String,
      @Expose
      @SerializedName("age_range_to")
      val Age_range_to: String,
      @Expose
      @SerializedName("should")
      val Should: String,
      @Expose
      @SerializedName("p_language")
      val P_language: String,
      @Expose
      @SerializedName("f_location")
      val F_location: String,
      @Expose
      @SerializedName("f_lat")
      val F_lat: String,
      @Expose
      @SerializedName("f_lon")
      val F_lon: String,
      @Expose
      @SerializedName("filter_gender")
      val Filter_gender: String,
      @Expose
      @SerializedName("distance")
      val Distance: String,
      @Expose
      @SerializedName("register_id")
      val Register_id: String,
      @Expose
      @SerializedName("ios_register_id")
      val Ios_register_id: String,
      @Expose
      @SerializedName("social_id")
      val Social_id: String,
      @Expose
      @SerializedName("otp")
      val Otp: String,
      @Expose
      @SerializedName("address")
      val Address: String,
      @Expose
      @SerializedName("lat")
      val Lat: String,
      @Expose
      @SerializedName("lon")
      val Lon: String,
      @Expose
      @SerializedName("time_zone")
      val Time_zone: String,
      @Expose
      @SerializedName("notification")
      val Notification: String,
      @Expose
      @SerializedName("push_notification")
      val Push_notification: String,
      @Expose
      @SerializedName("email_notification")
      val Email_notification: String,
      @Expose
      @SerializedName("type")
      val Type: String,
      @Expose
      @SerializedName("identify_image")
      val Identify_image: String,
      @Expose
      @SerializedName("admin_approval")
      val Admin_approval: String,
      @Expose
      @SerializedName("social_type")
      val Social_type: String,
      @Expose
      @SerializedName("status")
      val Status: String,
      @Expose
      @SerializedName("language")
      val Language: String,
      @Expose
      @SerializedName("first_login")
      val First_login: String,
      @Expose
      @SerializedName("online_status")
      val Online_status: String,
      @Expose
      @SerializedName("date_time")
      val Date_time: String
   )