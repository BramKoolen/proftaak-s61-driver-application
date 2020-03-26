package nl.fhict.denmarkroadtax.generic.extension

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

fun DateTime.convertToDate(stringDate: String): DateTime {
    return DateTimeFormat.forPattern("dd/MM/yyyy").parseDateTime(stringDate)
}

fun DateTime.dateString(): String {
    return DateTime.now().toString("dd/MM/yyyy")
}

fun DateTime.dateString(dateTime: DateTime): String {
    return dateTime.toString("dd/MM/yyyy")
}
