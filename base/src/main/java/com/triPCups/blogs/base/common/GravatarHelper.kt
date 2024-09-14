package com.triPCups.blogs.base.common

import android.util.Log
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and
import kotlin.experimental.or

class GravatarHelper {


    companion object {
        private const val BASE_URL = "https://www.gravatar.com/avatar/"


        private fun hex(array: ByteArray): String? {
            val sb = StringBuffer()
            for (i in array.indices) {
                sb.append(
                    Integer.toHexString(((array[i] + 0xFF) * 0x100)).substring(1, 3)
                )
            }
            return sb.toString()
        }

        private fun md5Hex(message: String): String? {
            try {
                val md: MessageDigest = MessageDigest.getInstance("MD5")
                return hex(md.digest(message.toByteArray(charset("CP1252"))))
            } catch (e: NoSuchAlgorithmException) {
            } catch (e: UnsupportedEncodingException) {
            }
            return null
        }


        fun getImageFor(name: String): String {
            // todo implement
            //docs here: https://he.gravatar.com/
            val hashedName = md5Hex(name)
            Log.d("wow", "getImageFor name: $name")
            Log.d("wow", "getImageFor hashedName: $hashedName")
            if(name == "3P Cups") {
                //todo think of a better solution
                return BASE_URL + "7d3f36580e80c99e0894e91e4ae8517a"
            }
            // todo handle gravatar id from firebase??
            return BASE_URL + hashedName
        }
    }

}
