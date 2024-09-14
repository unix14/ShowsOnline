package com.triPCups.blogs.showsOnline.common

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import androidx.annotation.IntRange
import java.math.BigInteger
import java.security.MessageDigest

class DeviceUtils {


    companion object {


        @SuppressLint("HardwareIds")
        fun getDeviceId(ctx: Context): String {
            return Settings.Secure.getString(ctx.contentResolver, Settings.Secure.ANDROID_ID)

        }

        fun randomAlphaNumericString(@IntRange(from= 1, to= 10) length: Int = 4): String {
            val alphaNumeric = ('a'..'z') + ('A'..'Z') + ('0'..'9')
            return alphaNumeric.shuffled().take(length).joinToString("")
        }

        fun md5(input:String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }



        fun getUniqueUserID(ctx: Context): String {
            val deviceId = getDeviceId(ctx)
            val md5 = md5(deviceId)
            return md5.take(4)
        }
    }

}