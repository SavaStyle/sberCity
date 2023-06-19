package org.example;

import org.example.model.City;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "resources/Задача ВС Java Сбер.csv";
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        scanner.useDelimiter("\r?\n");
        ArrayList<City> Cities = new ArrayList<>();

        while (scanner.hasNext()) {
            City city = parseCSVLine(scanner.next());
            Cities.add(city);
        }
        scanner.close();

        Cities.sort((o1, o2) -> o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));

        for (City city : Cities)
            System.out.println(city + "\n");

        List<City> sortedByDistrict = Cities.stream().sorted(
                Comparator.comparing(City::getDistrict).thenComparing(City::getName)
        ).collect(Collectors.toList());

        for (City city : sortedByDistrict)
            System.out.println(city);


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