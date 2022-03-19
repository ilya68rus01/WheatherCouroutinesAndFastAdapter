package khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen

data class DayliWheatherModel(
    val formattedDate: String,
    val temp: String,
    val wheatherIconUrl: String,
    val pressure: String,
    val wind: String,
    val description: String,
    val visibility: String
)

val EMPTY_MODEL = DayliWheatherModel(
    "",
    "",
    "",
    "",
    "",
    "",
    ""
)
