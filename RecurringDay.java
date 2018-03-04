
import java.util.Calendar;
import java.util.Date;


public class RecurringDay {
    private int hourOfDay, minute, second;
    private Calendar calendar = Calendar.getInstance();

    public RecurringDay(int hourOfDay, int minute, int second) {
        this(hourOfDay, minute, second, new Date());
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.second = second;
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        if (!calendar.getTime().before(date)) {
            calendar.add(Calendar.DATE, -1);
        }
    }

 
        public Date next() {
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
 
}
