package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 01,10,15), "Завтрак", 700),
                new UserMeal(LocalDateTime.of(2015, Month.JUNE, 01,13,20), "Обед", 1100)
        );
        List<UserMealWithExceed> filteredWithExceed = getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

        filteredWithExceed.stream().forEach((e) ->
            System.out.printf("%n%s\t\tКаллорийность: %d\t\tВремя: %s\t\tПревышение: %s.", e.getDescription(), e.getCalories(), e.getDateTime(), e.isExceed() ? "Да": "Нет")
        );

//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> resultMealExceedList = mealList.stream()
                .filter((s) -> {
                    boolean isExceeded = mealList.stream()
                            .filter((ml) -> LocalDate.from(ml.getDateTime()).isEqual(LocalDate.from(s.getDateTime())))
                            .mapToInt((el) -> el.getCalories()).sum() > caloriesPerDay;

                    return s.isBetween(startTime, endTime) && isExceeded;
                })
                .map((uMeal) -> new UserMealWithExceed(uMeal.getDateTime(), uMeal.getDescription(), uMeal.getCalories(), true))
                .collect(Collectors.toList());



        mealList.stream()
                .forEach((mlEx) -> {
                    int caloriesSum = mealList.stream()
                            .filter((ml) -> LocalDate.from(ml.getDateTime()).isEqual(LocalDate.from(mlEx.getDateTime())))
                            .mapToInt((e) -> e.getCalories()).sum();
                    System.out.printf("%s\t\tКалорийность: %d\t\tВремя: %s\t\tПревышение: %s\t\tСумма калорий: %d%n",
                            mlEx.getDescription(), mlEx.getCalories(), mlEx.getDateTime(),
                            (caloriesSum > caloriesPerDay) ? "Да": "Нет", caloriesSum);
                });


        return resultMealExceedList;
    }
}
