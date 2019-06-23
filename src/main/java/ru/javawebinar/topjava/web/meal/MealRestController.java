package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController
{
    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<Meal> getAll()
    {
        return service.getAll(authUserId());
    }

    public MealTo get(int id)
    {
        return convert(service.get(id, authUserId()));
    }

    public MealTo create(Meal meal)
    {
        return convert(service.create(meal));
    }

    public void delete(int id)
    {
        service.delete(id);
    }

    public MealTo update(int id)
    {
        final Meal meal = service.get(id, authUserId());
        service.update(meal);
        return convert(meal);
    }

    private MealTo convert(Meal meal)
    {
        return new MealTo(meal.getId(), meal.getUserId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), meal.isNew());
    }
}