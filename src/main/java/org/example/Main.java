package org.example;

import org.example.model.City;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "resources/Задача ВС Java Сбер.csv";
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        scanner.useDelimiter("\r?\n");
        ArrayList<City> cities = new ArrayList<>();

        HashMap<String, Integer> regions = new HashMap<String, Integer>();

        while (scanner.hasNext()) {
            City city = parseCSVLine(scanner.next());

            if (!regions.containsKey(city.getRegion())) {
                regions.put(city.getRegion(), 1);
            } else {
                regions.put(city.getRegion(), regions.get(city.getRegion()) + 1);
            }
            cities.add(city);
        }
        scanner.close();

        for (HashMap.Entry<String, Integer> item : regions.entrySet()) {
            System.out.printf("%s-%d \n", item.getKey(), item.getValue());
        }


 /*
 поиск мах населения
        City[] cityArray = new City[cities.size()];
        cities.toArray(cityArray);
     Long max = 0L;
        int id = 0;
        for (int i = 0; i < cityArray.length; i++) {
            if (cityArray[i].getPopulation() > max) {
                max = cityArray[i].getPopulation();
                id = i;
            }
        }
        DecimalFormat df = new DecimalFormat("###,###,###");
        System.out.println("[" + id + "] = " + df.format(max));
        */


/*
сортировка по названию городов
        cities.sort((o1, o2) -> o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));

        for (City city : cities)
            System.out.println(city + "\n");

сортировка по региону и городу
        List<City> sortedByDistrict = cities.stream().sorted(
                Comparator.comparing(City::getDistrict).thenComparing(City::getName)
        ).collect(Collectors.toList());

        for (City city : sortedByDistrict)
            System.out.println(city);
    */

    }

    private static City parseCSVLine(String next) {
        String[] lines = next.split(";");

        City nextCity = new City();
        nextCity.setName(lines[1]);
        nextCity.setRegion(lines[2]);
        nextCity.setDistrict(lines[3]);
        nextCity.setPopulation(Long.parseLong(lines[4]));
        if (lines.length == 6) {
            nextCity.setFoundation(lines[5]);
        } else {
            nextCity.setFoundation(" ");
        }
        return nextCity;
    }
}