package com.example.readmoretextview

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView

// TODO: why need change TextView to AppCompatTextView
class ReadMoreTextView: AppCompatTextView {

    /** current states is readMore or not */
    private var mReadMore: Boolean = false
    /** the text to display when collapsed. */
    private lateinit var mCollapsedText: CharSequence
    /** the text to display when expanded. */
    private lateinit var mExpandedText: CharSequence

    private var mViewMoreSpan: ClickableSpan = ReadMoreClickableSpan(context)

    private var mPara: Parameter = Parameter.Builder().build()

    constructor(context: Context, para: Parameter) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        text = mPara.text
    }

    init {
        movementMethod = LinkMovementMethod.getInstance() // enable the click action
        highlightColor = Color.TRANSPARENT // set background when clicked to transparent
    }

    private class ReadMoreClickableSpan(val context: Context): ClickableSpan() {
        override fun onClick(widget: View) {
            Toast.makeText(context, "clicked!", Toast.LENGTH_SHORT).show()
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.color = Color.CYAN
            ds.isUnderlineText = false // remove the underline
        }
    }

    override fun setText(text: CharSequence?, type: BufferType?) = mPara?.let {
        super.setText(getDisplayText(text), type)
    }

    private fun getDisplayText(text: CharSequence?) :CharSequence {
        return SpannableStringBuilder(text)
            .append(Constants.ELLIPSIS)
            .append(mPara.expandWords).apply {
            setSpan(mViewMoreSpan,
                this.length - 9, this.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }



}