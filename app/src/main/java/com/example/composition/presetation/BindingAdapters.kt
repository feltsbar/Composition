package com.example.composition.presetation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

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






