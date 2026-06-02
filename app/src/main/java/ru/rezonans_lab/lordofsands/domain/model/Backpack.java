package ru.rezonans_lab.lordofsands.domain.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Backpack {
    private final ArrayList<String> backpack = new ArrayList<>();//переделать на список Product??
    private int backpackCapacity;

    public Backpack() {
        backpackCapacity = 7;
    } //дада пока мэджик намбер

    public boolean lookForItem(String item) {
        return backpack.contains(item);
    }

    public String showItem(int i) {
        return backpack.get(i);
    }

    public int getBackpackSize () {
        return backpack.size();
    }

    public void deleteItemFromBackpack (int i) {
        backpack.remove(i);
    }

    public void addToBackPack(String item) {
        if (backpack.size() < backpackCapacity) {
            backpack.add(item);
        }
    }

    public List<String> getItems() {
        // Возвращаем копию или неизменяемую версию
        return new ArrayList<>(backpack);  // защитное копирование
    }

    public void clearBackPack() {
        backpack.clear();
    }
}
