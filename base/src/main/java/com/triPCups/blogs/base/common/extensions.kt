package com.triPCups.blogs.base.common

import android.app.DatePickerDialog
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.URLUtil
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import coil.load
import coil.transform.CircleCropTransformation
import com.triPCups.blogs.base.R
import com.triPCups.blogs.base.models.HyperLink
import com.triPCups.blogs.base.models.PostTag
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun ImageView.putImage(url: String?, isCircle: Boolean = false, placeholder: Int = R.drawable.ic_article, error: Int = R.drawable.ic_broken_image) {
    //todo implement cache ?
    this.load(url) {
        crossfade(true)
        placeholder(placeholder)
        error(error)
        if(isCircle) {
            transformations(CircleCropTransformation())
        }
    }
}

fun EditText.applyToPost(newTextCallback: (String) -> Unit) {
    doAfterTextChanged {
        newTextCallback.invoke(it.toString())
    }
}


fun TextView.setTextFromHTML(newText: String) {
    setText(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(newText, HtmlCompat.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(newText)
    })
}

fun View.setOnClickDatePickerDialog(context: Context, callback: (Date)-> Unit) {
    setOnClickListener {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(context, { view, year, monthOfYear, dayOfMonth ->
            val newDate = Date(year, monthOfYear, dayOfMonth)
            callback.invoke(newDate)
        }, year, month, day)
        dpd.datePicker.updateDate(year, month, day)
        dpd.show()
    }
}


fun TextView.putTags(tags: ArrayList<PostTag>?, isEditPost: Boolean = false, shouldShowTags: Boolean = true) {
    this.text = if (!tags.isNullOrEmpty() && shouldShowTags) {
        var tagsStr = ""//"<u>תגיות</u><b>:</b> "
        for (tag in tags) {
            tagsStr += "#<b>${tag.tag}</b> "
        }
        this.isVisible = true
        HtmlCompat.fromHtml(tagsStr, 0)
    } else {
        this.isVisible = isEditPost
        if(isEditPost) {
            "לחץ כאן להוספת תגיות"
        } else {
            ""
        }
    }
}

fun Context.openMail(email: String, subject: String, extraText: String, promptTitle: String) {
    val emailIntent = Intent(Intent.ACTION_SENDTO,
        Uri.fromParts("mailto", email, null)
    )
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
    emailIntent.putExtra(Intent.EXTRA_TEXT, extraText)
    startActivity(Intent.createChooser(emailIntent, promptTitle))
}

fun EditText.openKeyboard() {
    val inputMethodManager = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.showSoftInput(this, 0)
}

fun TextView.setDate(date: Date?, isEditPost: Boolean = false, shouldHideDate: Boolean = true) {
    this.text = if (date != null && !shouldHideDate) {
        this.isVisible = true
//        textDirection = TEXT_DIRECTION_LTR
//        layoutDirection = LAYOUT_DIRECTION_LTR
        getDate(date.toString())
    } else {
        this.isVisible = isEditPost
        if(isEditPost) {
            "לחץ כאן להוספת תאריך"
        } else {
            ""
        }
    }
}

fun getDate(dateStr: String): String {
    return try {
        val parser = SimpleDateFormat( "EEE MMM d HH:mm:ss Z yyyy", Locale.ENGLISH)
        val mDate = parser.parse(dateStr)

        val formatter = SimpleDateFormat("dd/MM/yy")
        formatter.format(mDate)
    } catch (e: Exception){
        Log.e("mDate",e.toString())
        ""
    }
}

val clickedUrls = arrayListOf<String>()

fun String.openUrl(context: Context) {
    if(isNullOrEmpty()) return
    try {
        val defaultColors = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(ContextCompat.getColor(context, R.color.purple_500))
            .build()

        CustomTabsIntent.Builder().setDefaultColorSchemeParams(defaultColors)
            .setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left)
            .setExitAnimations(context, R.anim.slide_in_left, R.anim.slide_out_right)
            .build().launchUrl(context, Uri.parse(this))
    } catch (e: Throwable) {
        context.startActivity(Intent.parseUri(URLUtil.guessUrl(this), 0))
    }
}

fun View.setOnClickOpensUrl(url: String) {
    url.openUrl(context)

    this.isActivated = false

    if (!clickedUrls.contains(url)) {
        clickedUrls.add(url)
    }
}

fun TextView.initLinks(url: String) {
    this.isActivated = !clickedUrls.contains(url)

    setOnClickListener {
        setOnClickOpensUrl(url)
    }
}


fun TextView.setTextWithLink(hyperLink: HyperLink, defaultValue: String = "") {
    setUnderline(
        hyperLink.text.ifEmpty{
            defaultValue
        }
    )
    initLinks(hyperLink.url)
}

fun TextView.setUnderline(textToUnderline: String) {
    val content = SpannableString(textToUnderline)
    content.setSpan(UnderlineSpan(), 0, content.length, 0)
    text = content
}
