package com.triPCups.blogs.base.custom_views

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.triPCups.blogs.base.R
import com.triPCups.blogs.base.common.putImage
import com.triPCups.blogs.base.models.Author
import kotlin.math.roundToInt


class AuthorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {


    private var authorNameTextView: TextView? = null
    private var authorNameImageView: ImageView? = null
    private var authorNameImageViewLayout: CardView? = null

    companion object {
        const val BIG = 0
        const val MEDIUM = 1
        const val SMALL = 2
    }

    init {

        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.AuthorView, 0, 0
        )
        val authorViewSize = a.getInt(R.styleable.AuthorView_authorView_size, 0)
        a.recycle()

        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.author_view, this, true)

        view.apply {
            authorNameTextView = findViewById<TextView>(R.id.author_view_name)
            authorNameImageView = findViewById<ImageView>(R.id.author_view_image)
            authorNameImageViewLayout = findViewById<CardView>(R.id.author_view_image_layout)
        }

        initView(authorViewSize)

    }

    private fun initView(authorViewSize: Int) {
        when (authorViewSize) {
            BIG -> {
                //already initialized
            }
            MEDIUM -> {
                authorNameTextView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17.0f)
                authorNameTextView?.gravity = Gravity.START
                authorNameImageViewLayout?.apply {
                    (authorNameTextView?.layoutParams as ConstraintLayout.LayoutParams).apply {
                        startToStart = ConstraintLayout.LayoutParams.UNSET
                        endToEnd = R.id.root//ConstraintLayout.LayoutParams.UNSET
                        topToBottom = ConstraintLayout.LayoutParams.UNSET


                        startToEnd =
                            authorNameImageViewLayout?.id ?: R.id.author_view_image_layout
                        topToTop = authorNameImageViewLayout?.id
                            ?: R.id.author_view_image_layout//ConstraintLayout.LayoutParams.PARENT_ID
                        bottomToBottom =
                            authorNameImageViewLayout?.id ?: R.id.author_view_image_layout
//                            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                        authorNameTextView!!.requestLayout()
                    }
                    (authorNameImageViewLayout?.layoutParams as ConstraintLayout.LayoutParams).apply {
                        endToEnd = ConstraintLayout.LayoutParams.UNSET

                        width = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            36f,
                            resources.displayMetrics
                        ).roundToInt()
                        height = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            36f,
                            resources.displayMetrics
                        ).roundToInt()

                        authorNameImageViewLayout!!.requestLayout()

                    }
                }
            }
            SMALL -> {
                authorNameTextView?.gravity = Gravity.START
                authorNameTextView?.isVisible = false
                authorNameImageViewLayout?.apply {
                    (authorNameImageViewLayout?.layoutParams as ConstraintLayout.LayoutParams).apply {
                        endToEnd = ConstraintLayout.LayoutParams.UNSET

                        width = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            26f,
                            resources.displayMetrics
                        ).roundToInt()
                        height = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP,
                            26f,
                            resources.displayMetrics
                        ).roundToInt()

                        authorNameImageViewLayout!!.requestLayout()
                    }
                }
            }
        }
    }



    //todo refactor to binding
    fun setAuthor(author: Author, onClickCallback: ((Author) -> Unit)? = null) {
        isVisible = false //author.showAuthor

        authorNameTextView?.text = author.name
        authorNameImageView?.scaleType = if(author.circleImageAuthor) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
        authorNameImageView?.putImage(author.image, author.circleImageAuthor, placeholder = R.drawable.ic_android, error = R.drawable.ic_android)
        authorNameImageViewLayout?.radius = if(author.circleImageAuthor) 42f else 0f
        authorNameImageViewLayout?.cardElevation = if(author.circleImageAuthor) 4f else 0f
        authorNameImageViewLayout?.maxCardElevation = if(author.circleImageAuthor) 8f else 0f

        onClickCallback?.apply {
            setOnClickListener {
                invoke(author)
            }
        }
    }
}