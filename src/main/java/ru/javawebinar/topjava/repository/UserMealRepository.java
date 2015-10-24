package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * Created by holonavt on 24.10.15.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal);

    void delete (int id);

    UserMeal get(int id);

    Collection<UserMeal> getAll();
}
