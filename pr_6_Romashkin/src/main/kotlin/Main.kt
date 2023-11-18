import kotlin.random.Random

fun main() {
    // Список городов
    val cities = listOf("Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Казань", "Челябинск",
        "Омск", "Самара", "Ростов-на-Дону", "Уфа", "Красноярск", "Пермь", "Волгоград", "Воронеж", "Калининград")

    var direction = "" // Переменная для хранения направления поезда
    var passengers = 0 // Переменная для хранения количества пассажиров
    var train = mutableListOf<Int>() // Список для хранения вместимости вагонов поезда

    var input: String? = "" // Переменная для хранения ввода пользователя
    while (input != "EXIT") {
        // Выводим меню действий
        println("1. Создать направление")
        println("2. Продать билеты")
        println("3. Сформировать поезд")
        println("4. Отправить поезд")
        println("Введите команду или введите EXIT для выхода: ")
        input = readLine()

        when (input) {
            "1" -> {
                // Генерируем случайные начальную и конечную точки маршрута
                val start = cities.random()
                var end = cities.random()
                while (end == start) {
                    end = cities.random()
                }
                direction = "$start - $end"
                println("Создано направление: $direction")
            }
            "2" -> {
                // Генерируем случайное количество пассажиров
                passengers = Random.nextInt(5, 201)
                println("Продано билетов: $passengers")
            }
            "3" -> {
                if (direction.isBlank()) {
                    println("Создайте направление с помощью команды 1")
                } else {
                    // Создаем поезд и добавляем вагоны, пока не усадим всех пассажиров
                    train = mutableListOf()
                    var remainingPassengers = passengers
                    while (remainingPassengers > 0) {
                        // Генерируем вместимость каждого вагона
                        val capacity = Random.nextInt(5, 25)

                        // Определяем количество пассажиров в данном вагоне
                        val boardedPassengers = if (remainingPassengers <= capacity) remainingPassengers else capacity

                        // Добавляем вагон в список поезда
                        train.add(boardedPassengers)

                        remainingPassengers -= boardedPassengers
                    }
                    println("Сформирован поезд с ${train.size} вагонами")
                }
            }
            "4" -> {
                if (train.isEmpty()) {
                    println("Сформируйте поезд с помощью команды 3")
                } else {
                    // Выводим информацию о поезде и вагонах
                    println("Поезд $direction отправлен.")
                    println("Информация о вагонах:")
                    for (i in train.indices) {
                        println("Вагон ${i + 1}: вместимость ${train[i]} пассажир(ов)")
                    }
                }
                // Сбрасываем переменные
                direction = ""
                passengers = 0
                train.clear()
            }
        }
    }
}
