fun main() {

    agoToText(60*60*24)
}

fun agoToText(
    howManySeconds: Int,
    rightNow: Int = 0,
    xMinutesAgo: Int = 60,
    xHoursAgo: Int = 60 * 60,
    today: Int = 24 * 60 * 60,
    yesterday: Int = 24 * 2 * 60 * 60,
    longTimeAgo: Int = 24 * 3 * 60 * 60
) {
    val secInHour = when {
        (howManySeconds - xMinutesAgo > 0 && howManySeconds < xHoursAgo) -> howManySeconds / xMinutesAgo
        (howManySeconds - xHoursAgo >= 0) -> howManySeconds / xHoursAgo
        else -> howManySeconds
    }
    when {
        howManySeconds in rightNow..xMinutesAgo -> println("Был(а) только что")
        howManySeconds in today until yesterday -> println("Был(а) сегодня")
        howManySeconds in yesterday until longTimeAgo -> println("Был(а) вчера")
        howManySeconds >= longTimeAgo -> println("Был(а) давно")
        else -> println(
            "Был(а) $secInHour ${
                minutesOrHours(
                    howManySeconds
                )
            } назад"
        )
    }

}

fun minutesOrHours(
    howManySeconds: Int,
    minutes: Int = howManySeconds / 60,
    hours: Int = howManySeconds / (60 * 60),
    days: Int = howManySeconds / (24 * 60 * 60)
): String {
    var data = ""
    when {
        howManySeconds >= 0 && howManySeconds < (60 * 60) -> data = when {
            (minutes == 1) -> "минуту"
            (minutes == 11) -> "минут"
            (minutes - 1) == 0 -> "минуты"
            (minutes - 2) == 0 -> "минуты"
            (minutes - 3) == 0 -> "минуты"
            (minutes - 4) == 0 -> "минуты"
            (minutes in 12..20) -> "минут"
            (minutes - 1) % 10 == 0 -> "минуту"
            (minutes - 2) % 10 == 0 -> "минуты"
            (minutes - 3) % 10 == 0 -> "минуты"
            (minutes - 4) % 10 == 0 -> "минуты"
            else -> "минут"
        }
        hours in 1..23 -> data = when {
            (hours -1) == 0 -> "час"
            (hours -2) == 0 -> "часа"
            (hours -3) == 0 -> "часа"
            (hours -4) == 0 -> "часа"
            (hours - 1) % 20 == 0 -> "час"
            (hours - 2) % 20 == 0 -> "часа"
            (hours - 3) % 20 == 0 -> "часа"
            (hours - 4) % 20 == 0 -> "часа"
            else -> "часов"
        }
        days >= 24 -> data = when {
            (days - 1) % 10 == 0 -> "сутки"
            else -> "суток"
        }
    }
    return data
}
