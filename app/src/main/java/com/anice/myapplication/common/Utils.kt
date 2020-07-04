package com.anice.myapplication.common

import android.content.Context
import com.anice.myapplication.application.MyApplication
import java.io.IOException

class Utils {
    companion object {
        fun loadJSONFromAsset(fileName: String): String? {
            var json: String? = null
            json = try {
                val `is` = MyApplication.applicationContext().assets.open(fileName)
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer, charset("UTF-8"))

            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
            return json
        }

    }
}