package com.example.composition.presetation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

interface OnOptionClickListener {
    fun onOptionClickListener(option : Int)
}

@BindingAdapter("isWinnerEmoji")
fun bindEmojiResult(imageView: ImageView, isWinner : Boolean){
    imageView.setImageResource(getSmileResourceId(isWinner))
}

@BindingAdapter("requiredAnswers")
fun bindRequireAnswers(textView : TextView, count : Int){
    textView.text = String.format(
        textView.context.getString(R.string.minimal_right_answers),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView : TextView, count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.your_score),
        count
    )
}

@BindingAdapter("requiredPercent")
fun bindRequirePercent(textView: TextView, count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.minimal_percent_of_answers),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.right_answers_percent),
        getPercentOfRightAnswers(gameResult)
    )
}


private fun getSmileResourceId(isWinner : Boolean) : Int {
    return if (isWinner) {
        R.drawable.ic_smile_emoji
    } else {
        R.drawable.ic_sad_emoji
    }
}

private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("dynamicProgress")
fun bindProgressBar(progressBar: ProgressBar, count: Int){
    progressBar.setProgress(count, true)
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough : Boolean){
    textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough : Boolean){
    val color = getColorByState(progressBar.context, enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context : Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_dark
    } else {
        android.R.color.holo_red_dark
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number : Int){
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener : OnOptionClickListener){
    textView.setOnClickListener {
        clickListener.onOptionClickListener(textView.text.toString().toInt())
    }
}