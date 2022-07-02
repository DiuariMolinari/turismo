package com.senac.turismo.componentes

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE
import java.util.*

@Composable
fun DatePickerField(label: String, mDateModel: String): LocalDate {
    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    var mDate = remember { mutableStateOf("") }
    var newDate = remember { mutableStateOf("") }
    if (!mDateModel.equals("")) {
        mDate.value = mDateModel
    }
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            var dayChar = if (mDayOfMonth < 10) 0 else "";
            var monthChar = if (mMonth < 10) 0 else "";
            mDate.value = "$mYear-$monthChar${mMonth + 1}-$dayChar$mDayOfMonth"
            newDate.value = "$mYear-$monthChar${mMonth + 1}-$dayChar$mDayOfMonth"
        },
        mYear, mMonth, mDay,
    )
    Box(
        Modifier.clickable(
            onClick = {
                mDatePickerDialog.show()
            }
        )) {
        TextField(
            value = mDate.value,
            onValueChange = {
                mDate.value = it
            },
            singleLine = true,
            enabled = false,
            label = { Text(text = label, color = Color.DarkGray) },
            modifier = Modifier
                .clickable {
                    mDatePickerDialog.show()
                },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.CalendarToday,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        )
    }
    if (!mDateModel.equals(newDate.value) && !newDate.value.equals("")) {
        mDate = newDate
    }

    var date = LocalDate.parse(mDate.value, DateTimeFormatter.ISO_DATE);
    return date;
}