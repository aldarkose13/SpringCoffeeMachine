package com.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;
    private final HolidayService holidayService;
    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository, HolidayService holidayService){
        this.coffeeRepository = coffeeRepository;
        this.holidayService = holidayService;
    }

    public List<CoffeeEntity> getAllCoffee(){
        return  coffeeRepository.findAll();
    }
    public CoffeeEntity saveCoffee(CoffeeEntity coffee) {
        return coffeeRepository.save(coffee);
    }
    public String getMostOrderedCoffeeName() {
        List<Object[]> results = coffeeRepository.findMostOrderedCoffees();

        if (!results.isEmpty()) {
            // The first entry will be the most ordered coffee
            Object[] mostOrdered = results.get(0);
            return "Most ordered coffee is: " + mostOrdered[0] + " with " + mostOrdered[1] + " orders.";
        }
        return "No orders found.";
    }
    public boolean isCoffeeMachineWorking(String countryCode) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Almaty"));

        // Check if today is within working hours
        boolean isWithinWorkingHours = now.getHour() >= 8 && now.getHour() < 18;

        // Check if today is a weekday (Monday to Friday)
        boolean isWeekday = now.getDayOfWeek() != DayOfWeek.SATURDAY && now.getDayOfWeek() != DayOfWeek.SUNDAY;

        // Check if today is a holiday
        List<Holiday> holidays = holidayService.getHolidays(now.getYear(), countryCode);
        boolean isHoliday = holidays.stream().anyMatch(holiday -> holiday.getDate().equals(now.toLocalDate()));


        // The coffee machine works only if it's a weekday, not a holiday, and within working hours
        boolean res = isWithinWorkingHours && isWeekday && !isHoliday;
        System.out.println("res " + res);
        return res;
    }



}
