package com.learning.utills

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Geocoder
import android.util.Base64
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import com.learning.R
import java.io.*
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

class DataManager private constructor() {
    private var mDialog: Dialog? = null
    private var isProgressDialogRunning = false

    //        WP10ProgressBar progressBar;
    var progressBar: ProgressBar? = null
    fun showProgressMessage(dialogActivity: Activity?, msg: String?) {
        try {
            if (isProgressDialogRunning) {
                hideProgressMessage()
            }
            isProgressDialogRunning = true
            mDialog = Dialog(dialogActivity!!)
            mDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mDialog!!.setContentView(R.layout.dialog_loading)
            mDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //TextView textView = mDialog.findViewById(R.id.textView);
            progressBar = mDialog!!.findViewById(R.id.progressBar)
            // textView.setText(msg);
            val lp = mDialog!!.window!!.attributes
            lp.dimAmount = 0.8f
            mDialog!!.window!!.attributes = lp
            mDialog!!.window!!.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            mDialog!!.setCancelable(false)
            mDialog!!.setCanceledOnTouchOutside(false)
            mDialog!!.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun hideProgressMessage() {
        isProgressDialogRunning = true
        try {
            if (mDialog != null) {
                mDialog!!.dismiss()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun saveBitmapToFile(file: File): File? {
        return try {

            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            o.inSampleSize = 6
            var inputStream = FileInputStream(file)
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o)
            inputStream.close()
            val REQUIRED_SIZE = 75
            var scale = 1
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                o.outHeight / scale / 2 >= REQUIRED_SIZE
            ) {
                scale *= 2
            }
            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            inputStream = FileInputStream(file)
            val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2)
            inputStream.close()

            // here i override the original image file
            file.createNewFile()
            val outputStream = FileOutputStream(file)
            selectedBitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            file
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        val instance = DataManager()

        fun toBase64(message: String): String? {
            val data: ByteArray
            try {
                data = message.toByteArray(charset("UTF-8"))
                return Base64.encodeToString(data, Base64.DEFAULT)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            return null
        }

        fun fromBase64(message: String?): String? {
            val data = Base64.decode(message, Base64.DEFAULT)
            try {
                return String(data, Charset.defaultCharset())
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
            return null
        }

        @SuppressLint("SimpleDateFormat")
        fun convertDateToString(l: Long): String {
            var str = ""
            val date = Date(l)
            val dateFormat = SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")
            str = dateFormat.format(date)
            return str
        }

        fun CurrentCity(latitude: Double, longitude: Double, c: Context?): String {
            if (latitude >= 0.0) {
                val geocoder = Geocoder(c!!, Locale.getDefault())
                try {
                    val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                    val address = addresses!![0].subLocality
                    val cityName = addresses[0].locality
                    val stateName = addresses[0].adminArea
                    val country = addresses[0].countryName
                    return "$address,$cityName,$stateName,$country"
                } catch (e: IOException) {
                    e.printStackTrace()
                    e.localizedMessage?.let { Log.e("CurrentCity: %s", it) }
                    e.message?.let { Log.e("CurrentCity: %s", it) }
                    Log.e("CurrentCity: %s", e.cause.toString())
                }
            }
            return "  "
        }
    }
}