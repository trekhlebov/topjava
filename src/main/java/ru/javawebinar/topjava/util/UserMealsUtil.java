package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 01, 10, 15), "Завтрак", 700),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 01, 13, 20), "Обед", 1100),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 01, 19, 10), "Ужин", 800)
        );
        List<UserMealWithExceed> filteredWithExceed = getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        filteredWithExceed = getFilteredMealsWithExceededByCycle(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        filteredWithExceed.stream().forEach(System.out::println);

//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesSumByDate =
                mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(ml -> TimeUtil.isBetween(ml.getDateTime().toLocalTime(), startTime, endTime))
                .map(ml -> new UserMealWithExceed(ml.getDateTime(), ml.getDescription(), ml.getCalories(),
                        caloriesSumByDate.get(ml.getDateTime().toLocalDate()) > caloriesPerDay ))
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceededByCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay){

        Map<LocalDate, Integer> coloriesSumByDate = new HashMap<>();
        for (UserMeal meal: mealList){
            LocalDate mealDate = meal.getDateTime().toLocalDate();
            coloriesSumByDate.put(mealDate, coloriesSumByDate.getOrDefault(mealDate, 0) + meal.getCalories());
        }

        List<UserMealWithExceed> mealExceededLst = new ArrayList<>();
        for(UserMeal meal: mealList){
            LocalDateTime dateTime = meal.getDateTime();
            if (TimeUtil.isBetween(dateTime.toLocalTime(), startTime, endTime)) {
                mealExceededLst.add(new UserMealWithExceed(dateTime, meal.getDescription(), meal.getCalories(),
                        coloriesSumByDate.get(dateTime.toLocalDate()) > caloriesPerDay));
            }
        }

        return mealExceededLst;
    }

}
