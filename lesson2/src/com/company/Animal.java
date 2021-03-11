package com.company;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Animal {
    private String [] illnesses;
    private boolean isHungry;
    private boolean isIll;
    private Date date;
    private String reportFeeding = "";
    private String name;
    public Animal(String[] illnesses, String name) {
        this.illnesses = Arrays.copyOf(illnesses, illnesses.length);
        isIll = illnesses.length != 0;
        this.isHungry = true;

    }

    public String getName() {
        return name;
    }

    public Animal(String name, String[] illnesses, String formatDate) {
        this(illnesses, name);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date now = new Date();
        try {
            date = dateFormat.parse(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        isHungry = (int)(now.getTime() - date.getTime())/  (60 * 60 * 1000) > 4;
        if (isHungry) {
            addDateToReport();
        }

    }

    /**
     * Добавить запись о кормлении животного
     */
    public String addDateToReport() {
        reportFeeding += date.toString() + "\n";
        return reportFeeding;
    }
    public String[] getIllnesses() {
        return Arrays.copyOf(illnesses, illnesses.length);
    }

    public void setIllnesses(String[] illnesses) {
        this.illnesses = Arrays.copyOf(illnesses, illnesses.length);
    }

    public boolean isHungry() {
        Date now = new Date();
        isHungry =  isHungry = (int)(now.getTime() - date.getTime())/  (60 * 60 * 1000) > 4;
        return isHungry;
    }

    /**
     * Кормление животного и вывод записей
     */
    public void feed() {
        isHungry = true;
        date = new Date();
        System.out.println(addDateToReport());
    }

    public boolean isIll() {
        return isIll;
    }

    public void setIll(boolean ill) {
        isIll = ill;
    }


    /**
     * Добавить запись о болезни животного и выводит
     * @param illness - название болезни
     */
    public void addIllness(String illness) {
        String res = "";
        illnesses = Arrays.copyOf(illnesses, illnesses.length + 1);
        illnesses[illnesses.length - 1] = illness;
        for (int i = 0; i < illnesses.length; i++) {
            res += illnesses[0] + "\n";
        }
        System.out.println(res);
    }
}
