package app.linuxduck.com.tamapet;

import android.util.Log;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Pet {
    private static final int SHOT_COST = 100;
    private String name;
    private int age;
    private int hunger;
    private int thirst;
    private int health;
    private int death;
    private int happy;
    private int ageTime;
    private int awayTime;
    private int randomEvent;
    private int randomEventPercentage;
    private String creature;
    private ArrayList<String> events;

    public String getName(){return name;}
    public int getAge(){return age;}
    public int getAgeTime(){return ageTime;}
    public int getHunger(){return hunger;}
    public int getThirst(){return thirst;}
    public int gethealth(){return health;}
    public int getDeath(){return death;}
    public int getHappy(){return happy;}
    public int getRandomEvent(){return randomEventPercentage;}
    public String getCreature(){return creature;}

    public Pet(){}

    public Pet(String newName, int newAge, int newHunger, int newThirst, int newHealth,
               int newDeath, int newHappy, int newAgeTime, int newAwayTime, int newRandomEventPercentage, String newCreature){
        name = newName;
        age = newAge;
        hunger = newHunger;
        thirst = newThirst;
        health = newHealth;
        death = newDeath;
        happy = newHappy;
        ageTime = newAgeTime;
        awayTime = newAwayTime;
        randomEventPercentage = newRandomEventPercentage;
        events = new ArrayList<>();
        creature = newCreature;

        // Fell down stairs 0
        events.add(name + " упал с лестницы! Он чувствует боль" +
                "и глупость… счастье (–10) и здоровье (–15).");

        // Received roses 1
        events.add(name + "  получены загадочные розы! ♥ ♥ ♥ счастье (+15)");

        // Got picked on 2
        events.add(name + " сегодня его дразнили… счастье (–10)");

        // Got in a fight 3
        events.add(name + " подрался с другим питомцем! счастье (-10) и здоровье (-15)");

        // Long weird story 4
        events.add(name + " чуть не получил мячом по голове. пытаясь\n" +
                "увернуться от мяча, он упал на траву. это было на вершине крутого холма, так что"
                + name +
                "покатился кубарем. счастье(-10) ");

        // Cheetoh 5
        events.add(name + " нашел чипсы и съел их. голод (+2) здоровье (-2)");

        // New friend 6
        events.add(name + " завел нового друга!! ♥ ♥ счастье (+20)");

        // Destroyed your home 7
        events.add(name + " разрушил ваш дом(. счастье (-15)");

        // Ate bugs 8
        events.add(name + "был найдем, жующим снаружи все подряд." +
                "Это что, беличий хвост? здоровье (-15)");

        // Stole 9
        events.add(name + " украл конфету из прилавка! счастье (-15) и здоровье (-10)");

        // Said mean things 10
        events.add(name + " говорил действительно злые вещи, которые были совершенно неуместны! счастье (-10)");

        // Shinigami 11
        events.add(name + " был посещен Шинигами. Крутость (+100), но это не входит в статы, так что…");

        // Stubbed toe 12
        events.add(name + " ушиб палец на ноге. упс! здоровье (-2)");

        // Got lost 13
        events.add(name + " потерялся по пути в школу. счастье (-5)");

        // Tried to make a cake 14
        events.add(name + " дождь из цветов и шоколадок!!! счастье (+5)");

        // Failed at something new 15
        events.add(name + " попытался научиться чему-то новому, но не получилось :( счастье (-5)");

        // Succeeded at learning something new! 16
        events.add(name + " получился выучить что-то новое!! счастье (+10)");

        // Tried to hack the pentagon 17
        events.add(name + " попытался взломать пентагон, но не получилось(… счастье (-20)");

        // Tried to learn binary exploitation 18
        events.add(name + " получилось взломать пентагон!!! счастье (+10)");

        // Went for a walk 19
        events.add(name + " прогулялся по парку, погода солнечная. счастье (+10)");

        // Went for a walk2 20
        events.add(name + " прогулялся по парку во время дождя! счастье (+20)");

        // Worms 21
        events.add(name + " скушал червяков, тк они никому не нравятся. счастье (-20) и здоровье (-20)");

    }
    public void updateRandomEvent(){
        randomEventPercentage--;
        if(randomEventPercentage <= 0){
            randomEventPercentage = 0;
        }
    }
    public void updateHunger(int calories, boolean treat){
        if(hunger + calories > 100) {
            hunger = 100;
        }
        else if(hunger + calories <= 0){
            hunger = 0;
            updateHealth(calories);
        }
        else{
            if(treat){
                hunger += calories;
                updateHappy(5, false);
                updateHealth(-2);
            }
            else{
                hunger += calories;
            }
        }
    }

    public void updateThirst(int water){
        if(thirst + water > 100)
            thirst = 100;
        else if(thirst + water <= 0) {
            thirst = 0;
            updateHealth(water);
        }
        else
            thirst += water;
    }

    public void updateHealth(int heal){
        if(health + heal > 100)
            health = 100;
        else if(health + heal <= 0) {
            updateDeath(heal);
            health = 0;
        }
        else
            health += heal;
    }
    public void updateDeath(int major){
        major = abs(major);
        if(death + major > 100) {
            death = 100;
        }
        else if(death + major <= 0){
            death = 0;
        } else {
            death += major;
        }
    }

    public void updateHappy(int gameCost, boolean isShot){
        if(happy + gameCost > 100){
            happy = 100;
        } else if(happy + gameCost <= 0){
            if(!isShot)
                updateHealth(-(abs(happy + gameCost)));
            happy = 0;
        } else {
            happy += gameCost;
        }
    }

    public void giveShot(){
        updateHealth(SHOT_COST);
        updateHappy(-60, true);
    }

    public void updateAwayTime(int time){
        // 15 минут
        if((time - awayTime) / 900 > 0){
            int hoursAway = (time - awayTime) / 900;
            updateHunger(-hoursAway, false);
            updateThirst(-hoursAway);
            updateHappy(-hoursAway, false);
        }
        awayTime = time;
    }

    public boolean ageUp(int time){
        if(time - ageTime >= 86400){
            age += (time - ageTime) / 86400;
            ageTime = time;
            return true;
        }
        return false;
    }

    public boolean isDead(){
        if(death >= 100)
            return true;
        return false;
    }

    public void play(){
        updateHappy(20, false);
    }

    public String randomEvent(){
        randomEvent = abs((int) (Math.random() * events.size()) - 1);
        if(randomEvent == 0){
            updateHappy(-10, false);
            updateHealth(-15);
        } else if(randomEvent == 1){
            updateHappy(15, false);
        } else if(randomEvent == 2){
            updateHappy(-10, false);
        } else if(randomEvent == 3){
            updateHappy(-10, false);
            updateHealth(-15);
        } else if(randomEvent == 4){
            updateHappy(-10, false);
        } else if(randomEvent == 5){
            updateHunger(2, false);
            updateHealth(-2);
        } else if(randomEvent == 6){
            updateHappy(20, false);
        } else if(randomEvent == 7){
            updateHappy(-15, false);
        } else if(randomEvent == 8){
            updateHealth(-15);
        } else if(randomEvent == 9){
            updateHappy(-15, false);
            updateHealth(-10);
        } else if(randomEvent == 10){
            updateHappy(-10, false);
        } else if(randomEvent == 12){
            updateHealth(-2);
        } else if(randomEvent == 13){
            updateHappy(-5, false);
        } else if(randomEvent == 14){
            updateHappy(5, false);
        } else if(randomEvent == 15){
            updateHappy(-5, false);
        } else if(randomEvent == 16){
            updateHappy(10, false);
        } else if(randomEvent == 17){
            updateHappy(-20, false);
        } else if(randomEvent == 18){
            updateHappy(10, false);
        } else if(randomEvent == 19){
            updateHappy(10, false);
        } else if(randomEvent == 20){
            updateHappy(20, false);
        } else if(randomEvent == 21){
            updateHappy(-20, false);
            updateHealth(-20);
        }
        return events.get(randomEvent);
    }
}
