package me.noxiuam.noxlib.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil
{
    public String getCurrentTime(String format)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
