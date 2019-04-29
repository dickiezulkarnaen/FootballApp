package com.dickiez.soccerhub.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun gsonBuilder(): Gson {
    val gson = GsonBuilder().create()
    return gson
}

fun dateToLong(date: Date):Long{
    val cal = Calendar.getInstance()
    cal.time = date
    return cal.timeInMillis
}

fun addHour(date: Date?, hour : Int?):Long{
    val cal = Calendar.getInstance()
    cal.time = date
    cal.add(Calendar.HOUR, hour!!)
    return cal.timeInMillis
}

fun convertStringToLong(date: String, pattern :String): Long {
    val cal = Calendar.getInstance()
    val tz = cal.timeZone

    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    dateFormat.timeZone = tz
    var convertedDate = Date()
    try {
        convertedDate = dateFormat.parse(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return addHour(convertedDate, 0)

}

fun getFormated(format: String, time: Long): String {
    val dateFormat = SimpleDateFormat(format)
    val date = Date()
    date.time = time
    return dateFormat.format(date)
}

fun toGMTFormat(date: String, time: String): Date? {
    val formatter = SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"
    return formatter.parse(dateTime)
}