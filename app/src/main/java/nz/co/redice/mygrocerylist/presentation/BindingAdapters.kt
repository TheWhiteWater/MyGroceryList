package nz.co.redice.mygrocerylist.presentation

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import nz.co.redice.mygrocerylist.R
import java.lang.Error

@BindingAdapter ("numberToString")
fun convertNumberToString(textView: TextView, count:Int) {
    textView.text = count.toString()
}


@BindingAdapter ("errorInputName")
fun bindErrorInputName(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.error_input_name)
    } else {
        null
    }
    textInputLayout.error = message
}
@BindingAdapter ("errorInputCount")
fun bindErrorInputCount(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.error_input_count)
    } else {
        null
    }
    textInputLayout.error = message
}