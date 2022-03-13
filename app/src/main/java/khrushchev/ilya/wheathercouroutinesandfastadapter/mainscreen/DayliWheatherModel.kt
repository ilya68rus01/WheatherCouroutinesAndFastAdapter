package khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen

data class DayliWheatherModel(
    val formattedDate: String,
    val temp: String,
    val wheatherIconUrl: String,
    val pressure: Int,
    val wind: Int,
    val description: String
)

val EMPTY_MODEL = DayliWheatherModel(
    "",
    "",
    "",
    Int.MIN_VALUE,
    Int.MIN_VALUE,
    ""
)
