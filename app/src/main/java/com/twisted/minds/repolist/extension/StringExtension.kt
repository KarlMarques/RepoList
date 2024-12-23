package com.twisted.minds.repolist.extension

import java.text.SimpleDateFormat
import java.util.Locale

internal fun String?.orValue(value: String): String {
    var result = this

    if (result.isNullOrBlank()) {
        result = value
    }

    return result
}

internal fun dateFormatter(date: String?): String {
    val dateInputPattern = "yyyy-MM-dd HH:mm:ss "

    val dateOutputPattern = "dd MMM yyyy', at' HH:mm:ss"

    val formatterInput = SimpleDateFormat(dateInputPattern, Locale.ENGLISH)

    val formatterOutput = SimpleDateFormat(dateOutputPattern, Locale.ENGLISH)

    val filteredDate = date?.replace("T", " ")?.replace("Z", " ")

    val dateTime = filteredDate?.let { formatterInput.parse(it) }

    return dateTime?.let { formatterOutput.format(it) }.orEmpty()
}
