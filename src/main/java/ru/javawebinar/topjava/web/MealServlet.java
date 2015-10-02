package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * Created by holonavt on 23.09.15.
 */
public class MealServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        List<UserMealWithExceed> filteredWithExceed =
                UserMealsUtil.getFilteredMealsWithExceededByCycle(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        request.setAttribute("mealList", filteredWithExceed);

        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
    }
}
