package me.noxiuam.noxlib.util.custom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil
{
    public String getCurrentTime(String format)
    {
        LocalDateTime now = LocalDateTime.now();
        return DateTimeFormatter.ofPattern(format).format(now);
    }

    public String getCurrentGenericTime()
    {
        LocalDateTime now = LocalDateTime.now();
        return DateTimeFormatter.ofPattern("hh:mm a MM/dd/yyyy").format(now);
    }
}
