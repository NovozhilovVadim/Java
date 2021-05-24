package ru.geekbrains;
import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {//обопщёный клас коробка, ограниченный классом fruit
    private ArrayList<T> box;//объявляем массив

    public Box(T... items) {//Конструктор класса создаёт экземпляр массива
        this.box = new ArrayList<T>(Arrays.asList(items));
    }

    public void add(T... items) {//добавляем фрукты в массив в зависимости от типа
        this.box.addAll(Arrays.asList(items));
        System.out.println("add passed");
    }

    public void remove(T... items) {//удаляем фрукт из массива
        for (T item: items) this.box.remove(item);
        System.out.println("remove passed");
    }

    public ArrayList<T> getItems() {
        return new ArrayList<T>(box);
    }

    public void clear() {//очищаем массив
        box.clear();
        System.out.println("clear passed");
    }

    public float getWeight() {//вес фруктов в коробке (массиве)
        if (box.size() == 0){
            System.out.println(" box empty");
            return 0;//Если пусто - возвращаем ноль
        }
        float weight = 0;//переменная веса
        for (T item: box) weight += item.getWeight();//пробегаем по массиву и плюсуем вес каждого объекта
        System.out.println("weight " + weight);
        return weight;//фозвращаем вес
    }

    public boolean compare(Box box) {//сравниваем вес массивов
        System.out.println("weight boxes:\n" + this.getWeight() + "\n" + box.getWeight());
        return this.getWeight() == box.getWeight();
    }

    public void transfer(Box<? super T> box) {//перекладываем фрукты в новый массив
        box.box.addAll(this.box);
        System.out.println("transfer passed");
        clear();
    }
}