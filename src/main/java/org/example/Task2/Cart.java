package org.example.Task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

//    public void cardBalancing()
//    {
//        boolean proteins = false;
//        boolean fats = false;
//        boolean carbohydrates = false;
//
//        for (var food : foodstuffs)
//        {
//            if (!proteins && food.getProteins())
//                proteins = true;
//            else
//            if (!fats && food.getFats())
//                fats = true;
//            else
//            if (!carbohydrates && food.getCarbohydrates())
//                carbohydrates = true;
//            if (proteins && fats && carbohydrates)
//                break;
//        }
//
//        if (proteins && fats && carbohydrates)
//        {
//            System.out.println("Корзина уже сбалансирована по БЖУ.");
//            return;
//        }
//
//        for (var thing : market.getThings(clazz))
//        {
//            if (!proteins && thing.getProteins())
//            {
//                proteins = true;
//                foodstuffs.add(thing);
//            }
//            else if (!fats && thing.getFats())
//            {
//                fats = true;
//                foodstuffs.add(thing);
//            }
//            else if (!carbohydrates && thing.getCarbohydrates())
//            {
//                carbohydrates = true;
//                foodstuffs.add(thing);
//            }
//            if (proteins && fats && carbohydrates)
//                break;
//        }
//
//        if (proteins && fats && carbohydrates)
//            System.out.println("Корзина сбалансирована по БЖУ.");
//        else
//            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
//
//    }
    public void cardBalancing(){
        AtomicInteger tmp = new AtomicInteger(0);
        AtomicBoolean proteins = new AtomicBoolean(false);
        AtomicBoolean fats = new AtomicBoolean(false);
        AtomicBoolean carbohydrates = new AtomicBoolean(false);
        foodstuffs.stream()
                .forEach(food -> {
                    if(Food.class.isAssignableFrom(clazz)){
                        if(food.getProteins()){
                            proteins.setRelease(true);
                            tmp.getAndIncrement();
                        } else if (food.getCarbohydrates()) {
                            carbohydrates.setRelease(true);
                            tmp.getAndIncrement();
                        } else if (food.getFats()) {
                            fats.setRelease(true);
                            tmp.getAndIncrement();
                        }
                    }
                });
        if(tmp.get() == 3){
            System.out.println("Корзина уже сбалансирована по БЖУ.");
        } else {
            if (!proteins.get()){
                foodstuffs.add(market.getThings(clazz).stream()
                        .filter(Food::getProteins)
                        .findAny()
                        .get());
            } if (!fats.get()) {
                foodstuffs.add(market.getThings(clazz).stream()
                        .filter(Food::getFats)
                        .findAny()
                        .get());
            } if (!carbohydrates.get()){
                foodstuffs.add(market.getThings(clazz).stream()
                        .filter(Food::getCarbohydrates)
                        .findAny()
                        .get());
            }
            System.out.println("Ваша корзина сбалансирована.");
        }
    }


    //endregion

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }



    public void printFoodstuffs()
    {
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");*/


        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));
    }
}
