package com.triPCups.blogs.showsOnline.common

import android.content.Context
import android.content.Intent
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.showsOnline.R


fun BlogPost.shareShow(context: Context) {
    val i = Intent(Intent.ACTION_SEND)
    i.type = "text/plain"
    i.putExtra(Intent.EXTRA_SUBJECT, "שיתוף הצגה: $title")
    i.putExtra(Intent.EXTRA_TEXT,
        "${links?.first()?.url ?: ""}\n${getRandomStartPhrase()} \"$title\"\n\n${getRandomC2APhrase()}\n\n${getDownloadLinkPhrase(context)}")
    context.startActivity(Intent.createChooser(i, "שיתוף הצגה"))
}

fun Context.shareApp() {
    val i = Intent(Intent.ACTION_SEND)
    i.type = "text/plain"
    i.putExtra(Intent.EXTRA_SUBJECT, "שמעת על האפליקציה הצגות אונליין?")
    i.putExtra(Intent.EXTRA_TEXT, getShareAppPhrase(this))
    startActivity(Intent.createChooser(i, "שיתוף אפליקציה"))
}

fun getRandomStartPhrase(): String {
    return arrayListOf(
        "מכיר את ההצגה הזאת?",
        "מצאתי הצגה מעניינת",
        "מצאתי הצגה שווה!",
        "זאת הצגה שמדברת אליי!",
        "אני רוצה לראות את זה",
        "שמעת על ההצגה הזאת?"
    ).random()
}

fun getRandomC2APhrase(): String {
    return arrayListOf(
        "חייבים לראות אותה ביחד!",
        "רוצה לראות את זה היום?",
        "רוצה לראות את זה בהזדמנות?",
        "רוצה לראות את ההצגה הזאת איתי?",
        "מה דעתך לצפות בזה?"
    ).random()
}

fun getDownloadLinkPhrase(context: Context): String {
    return "נשלח באמצעות אפליקציית ${
                context.getString(
                    R.string.app_name
                )
            }.\n" + getDownloadNowPhrase(context)
}

fun getShareAppPhrase(context: Context): String {
    return "תכירו את אפליקציית הצגות אונליין, חווית תרבות ישראלית מלאה מהכיס שלך!\n\n${getDownloadNowPhrase(context)}"
}

fun getDownloadNowPhrase(context: Context): String {
    return "לחצו כאן להורדה למכשירי אנדרואיד\n" + getDownloadLink(context)
}

fun getDownloadLink(context: Context): String {
    return "${Constants.APPLICATION_IN_STORE_LINK_PREFIX}${context.packageName}"
}