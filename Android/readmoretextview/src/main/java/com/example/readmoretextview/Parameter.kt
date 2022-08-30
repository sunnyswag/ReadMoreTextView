package com.example.readmoretextview

import android.graphics.Color

class Parameter(builder: Builder) {

    /** the text to display. */
    var text: CharSequence = builder.text
    val limitMode: Int = builder.limitMode
    val limitLines: Int = builder.limitLines
    val limitWords: Int = builder.limitWords
    val showFoldedChoice: Boolean = builder.showFoldedChoice
    val clickableTextColor: Int = builder.clickableTextColor
    val expandWords: String = builder.expandWords
    val foldWords: String = builder.foldWords

    class Builder {
        var text: CharSequence = "hello world!"
        var limitMode: Int = Constants.LIMIT_MODE_LINES
        var limitLines: Int = Constants.DEFAULT_LIMIT_LINES
        var limitWords: Int = Constants.DEFAULT_LIMIT_WORDS
        var showFoldedChoice: Boolean = Constants.SHOW_FOLDED_CHOICE
        var clickableTextColor: Int = Color.BLUE
        var expandWords: String = Constants.EXPAND_WORDS
        var foldWords: String = Constants.FOLD_WORDS

        // TODO: write some annotation
        fun text(text: CharSequence): Builder = apply {
            this.text = text
        }

        fun limitMode(mode: Int): Builder = apply {
            this.limitMode = mode
        }

        fun limitLines(lines: Int): Builder = apply {
            this.limitLines = lines
        }

        fun limitWords(words: Int): Builder = apply {
            this.limitWords = words
        }

        fun showFoldedChoice(toggle: Boolean): Builder = apply {
            this.showFoldedChoice = toggle
        }

        fun expandWords(words: String): Builder = apply {
            this.expandWords = words
        }

        fun foldWords(words: String): Builder = apply {
            this.foldWords = words
        }

        fun build(): Parameter {
            return Parameter(this)
        }
    }
}