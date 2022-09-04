package com.example.readmoretextview

import android.graphics.Color

class Parameter(builder: Builder) {

    /** the text to display. */
    var text: CharSequence = builder.text
    var limitMode: Int = builder.limitMode
    var limitLines: Int = builder.limitLines
    var limitWords: Int = builder.limitWords
    var showFoldedChoice: Boolean = builder.showFoldedChoice
    var clickableTextColor: Int = builder.clickableTextColor
    var expandWords: Int = builder.expandWords
    var foldWords: Int = builder.foldWords

    class Builder {
        var text: CharSequence = "hello world!"
        var limitMode: Int = Constants.LIMIT_MODE_LINES
        var limitLines: Int = Constants.DEFAULT_LIMIT_LINES
        var limitWords: Int = Constants.DEFAULT_LIMIT_WORDS
        var showFoldedChoice: Boolean = Constants.SHOW_FOLDED_CHOICE
        var clickableTextColor: Int = Color.BLUE
        var expandWords: Int = R.string.expand_words
        var foldWords: Int = R.string.fold_words

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

        fun expandWords(words: Int): Builder = apply {
            this.expandWords = words
        }

        fun foldWords(words: Int): Builder = apply {
            this.foldWords = words
        }

        fun build(): Parameter {
            return Parameter(this)
        }
    }
}