package com.example.readmoretextview

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView

// TODO: why need change TextView to AppCompatTextView
class ReadMoreTextView: AppCompatTextView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        movementMethod = LinkMovementMethod.getInstance() // enable the click action
        highlightColor = Color.TRANSPARENT // set background when clicked to transparent
    }

    private class ReadMoreClickableSpan(val context: Context): ClickableSpan() {
        override fun onClick(widget: View) {
            Toast.makeText(context, "clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(getDisplayText(text), type)
    }

    private fun getDisplayText(text: CharSequence?) :CharSequence {
        return SpannableStringBuilder(text).append("...").append("read more").apply {
            setSpan(ReadMoreClickableSpan(context), this.length - 9, this.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        }
    }



}