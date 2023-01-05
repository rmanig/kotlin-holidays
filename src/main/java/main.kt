import org.holidays.Holidays
import java.time.LocalDate

fun main() {
    val holidays = Holidays()
    var result = holidays.between(LocalDate.of(2022, 1, 1),
            LocalDate.of(2022, 12, 31), )
}