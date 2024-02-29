package com.adnanozgurcelik.jetnote.util

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(time: Long): String {
    val date = Date(time)

    val format = SimpleDateFormat(
        "EE, d MMM hh:mm aaa",
        Locale.getDefault()
    )

    return format.format(date)
}