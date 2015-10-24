package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by holonavt on 24.10.15.
 */
public class InMemoryUserMealRepository implements UserMealRepository {

    private Map<Integer, UserMeal> repository = new ConcurentHashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    {
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        save(new UserMeal(LocalDateTime.of(2015, Month.JUNE, 01, 10, 15), "Завтрак", 700));
        save(new UserMeal(LocalDateTime.of(2015, Month.JUNE, 01, 13, 20), "Обед", 1100));
        save(new UserMeal(LocalDateTime.of(2015, Month.JUNE, 01, 19, 10), "Ужин", 800));
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew())
            userMeal.setId(counter.incrementAndGet());
        return repository.put(userMeal.getId(), userMeal);
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public UserMeal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<UserMeal> getAll() {
        return repository.values();
    }
}
