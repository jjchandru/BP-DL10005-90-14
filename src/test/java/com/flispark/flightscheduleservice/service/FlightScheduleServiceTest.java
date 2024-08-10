package com.flispark.flightscheduleservice.service;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightScheduleServiceTest {
    @Test
    public void testDiffDays() throws Exception {
        FlightScheduleService flightScheduleService = new FlightScheduleService();
        
        // Use reflection to access the private method
        Method method = FlightScheduleService.class.getDeclaredMethod("diffDays", String.class, String.class);
        method.setAccessible(true);
        
        // Invoke the private method
        int diff = (int) method.invoke(flightScheduleService, "2024-06-03", "2024-09-11T06:00:00Z");
        System.out.println(Math.ceil(91 / 10.0));
        System.out.println((int) Math.ceil(91 / 10.0));
        
        assertEquals(100, diff);
    }
}