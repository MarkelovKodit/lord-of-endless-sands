package ru.rezonans_lab.lordofsands.domain.model;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@AllArgsConstructor
public final class Hero {
    private final int MAX_STAT_POINTS = 40;
    private int health;
    private int dex;
    private int mindPower;

    private int charm;
    private int money;
    private int food;
    private int hunger;
    private boolean cursed;

    private boolean[] luck = new boolean[]{true, true, true, true, true, true};
    private int phisDamage;
    private String weapon;

    private Backpack backpack;
    private ArrayList<String> achievements;
//    private final Map<Stat, StatModifier> statModifiers = new HashMap<>();


    public Hero() {
//        statModifiers.put(Stat.HEALTH, this::changeHealth);
//        statModifiers.put(Stat.DEX, this::changeDex);
//        statModifiers.put(Stat.MIND, this::changeMindPower);
//        statModifiers.put(Stat.CHARM, this::changeCharm);
//        statModifiers.put(Stat.MONEY, this::changeMoney);
//        statModifiers.put(Stat.FOOD, this::changeFood);

//        System.out.println("Рождается протагонист!!!");
        backpack = new Backpack();
        achievements = new ArrayList<>(12);
        generateStartStats();
//        backpack.addToBackPack("Еда");
//        backpack.addToBackPack("чипсы рифлёные");
//        backpack.addToBackPack("чешуя с жопы дракона");
//        backpack.addToBackPack("остриженные ногти");
//        backpack.addToBackPack("пизябра"); //привет Рику и Морти, это приключение, тут должна быть пизябра
        backpack.addToBackPack("смертовизор");
        backpack.addToBackPack("нерабочий нигдеход");
//        backpack.addToBackPack("Браслет");//для проверки глав с участием тех или иных предметов, потом уберу это
    }

    public void generateStartStats() {
        health = Math.min((Dice.rollTheDice() + 7) * 2, 24);
        dex = Math.min((Dice.rollTheDice() + 7), 12);
        charm = MAX_STAT_POINTS - health - dex;
        makeLuck();
        makeLuck();
    }

    public void makeLuck() {
        int i = Dice.rollTheDice();
        luck[i - 1] = false;
    }


    public void changeStat(Stat stat, int amount) {
        //из-за поддержки старых API приходится пользоваться таким синтаксисом
        switch (stat) {
            case HEALTH:
                changeHealth(amount);
                break;
            case DEX:
                changeDex(amount);
                break;
            case MIND:
                changeMindPower(amount);
                break;
            case CHARM:
                changeCharm(amount);
                break;
            case MONEY:
                changeMoney(amount);
                break;
            case FOOD:
                changeFood(amount);
                break;
            case DMG:
                setPhisDamage(amount);
                break;
            default:
                throw new IllegalArgumentException("Unknown stat: " + stat);
        }
    }

    public boolean testLuck() {
        if (cursed) {
            cursed = false;
            return false;
        }
        //проверить не заполнен ли массив. позже доделаю TODO
        // а надо ли? заполнен значит кругом неудачи
        int i = Dice.rollTheDice();
        if (luck[i - 1]) {
            luck[i - 1] = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean testCharm() {
        int c = Dice.rollTheDice() + Dice.rollTheDice();
        if (c <= charm) {
            changeCharm(1);
            return true;
        } else {
            changeCharm(-1);
            return false;
        }
    }

    public String randomLoss() {
        String lostItem;
        int i = (int) ((backpack.getBackpackSize() - 1) * Math.random() + 1);
        if (backpack.showItem(i).equals("еда") & food >= 1) {
            lostItem = "Еда (1 единица)";
            changeFood(-1);
        } else {
            lostItem = backpack.showItem(i);
            backpack.deleteItemFromBackpack(i);
        }
        return lostItem;
    }

    public boolean eat() {
        if (food == 0) {
            return false;
        } else {
            health += 4;
            food -= 1;
            return true;
        }
    }

    public void changeHealth(int h) {
        health += h;
    }

    public void changeDex(int d) {
        dex += d;
    }

    public int getDmg() {
        return phisDamage;
    }

    public void changeDmg(int i) {
        phisDamage += i;
    }

    public void changeFood(int food) {
        this.food += food;
    }

    public void changeMoney(int money) {
        this.money += money;
    }

    public void changeMindPower(int mind) {
        mindPower += mind;
    }

    public void changeCharm(int charm) {
        this.charm += charm;
    }

    public void setHunger(int hunger) {
        this.hunger += hunger;
    }

//    private void changeHunger(int amount) {
//        hunger += amount;
//    }
//
//
//    public boolean getLuck(int i) {
//        return luck[i];
//    }

    //---------  конец геттеров сеттеров изменяторов

    public void clearLuck() {
        for (int i = 0; i < luck.length; i++) {
            if (!luck[i]) {
                luck[i] = true;
                break;
            }
        }
    }

    public void addAchieve(String achieve) {
        achievements.add(achieve);
    }

    public int randomHealthLoss(int x) {
        int dmg = 0;
        for (int i = 0; i < x; i++) {
            dmg += Dice.rollTheDice();
        }
        changeHealth(-dmg);
        return dmg;
    }
}