package HarrysFrisørSalon;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingKalender {

    public boolean erBookingTilladt(LocalDateTime startTid, LocalDateTime slutTid) {
        if (startTid.getDayOfWeek() != DayOfWeek.SATURDAY && startTid.getDayOfWeek()
                != DayOfWeek.SUNDAY && startTid.toLocalTime().isAfter(LocalTime.of(10, 0))
                && startTid.toLocalTime().isBefore(LocalTime.of(18, 0))) {
            return true;
        }
        return false;
        }
    }

