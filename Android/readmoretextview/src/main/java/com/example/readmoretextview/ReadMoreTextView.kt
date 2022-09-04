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
import java.lang.ref.WeakReference

// TODO: why need change TextView to AppCompatTextView
class ReadMoreTextView: AppCompatTextView {

    /** current states is readMore or not */
    private var mReadMore: Boolean = false
    /** the text to display when collapsed. */
    private lateinit var mCollapsedText: CharSequence
    /** the text to display when expanded. */
    private lateinit var mExpandedText: CharSequence

    private var mViewMoreSpan: ClickableSpan = ReadMoreClickableSpan(this)

    private var mPara: Parameter = Parameter.Builder().build()

    constructor(context: Context, para: Parameter) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        // TODO: TypedArray analyze
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ReadMoreTextView)
        mPara.apply {
            limitMode = typedArray.getInt(R.styleable.ReadMoreTextView_limitMode, Constants.LIMIT_MODE_LINES)
            limitLines = typedArray.getInt(R.styleable.ReadMoreTextView_limitLines, Constants.DEFAULT_LIMIT_LINES)
            limitWords = typedArray.getInt(R.styleable.ReadMoreTextView_limitWords, Constants.DEFAULT_LIMIT_WORDS)
            showFoldedChoice = typedArray.getBoolean(R.styleable.ReadMoreTextView_showFoldedChoice, Constants.SHOW_FOLDED_CHOICE)
            clickableTextColor = typedArray.getColor(R.styleable.ReadMoreTextView_clickableTextColor, Color.BLUE)
            expandWords = typedArray.getResourceId(R.styleable.ReadMoreTextView_expandWords, R.string.expand_words)
            foldWords = typedArray.getResourceId(R.styleable.ReadMoreTextView_expandWords, R.string.fold_words)
        }

        typedArray.recycle() // TODO: why need this operation
        text = mPara.text
    }

    init {
        movementMethod = LinkMovementMethod.getInstance() // enable the click action
        highlightColor = Color.TRANSPARENT // set background when clicked to transparent
    }

    private class ReadMoreClickableSpan(): ClickableSpan() {

        lateinit var view: WeakReference<View>
        constructor(context: View) : this() {
            view = WeakReference(context)
        }

        override fun onClick(widget: View) {
            val clickable = view.get()?.isClickable
            view.get()?.isClickable = !clickable!!
            Toast.makeText(view.get()?.context, "${view.get()?.isClickable} clicked!", Toast.LENGTH_SHORT).show()
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
            .append(R.string.ellipsis.getRes())
            .append(mPara.expandWords.getRes()).apply {
            setSpan(mViewMoreSpan,
                this.length - 9, this.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    private fun calculateFoldedText() {

    }

    private fun Int.getRes() = resources.getString(this)
}