package org.example;

import org.example.model.City;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String fileName = "resources/Задача ВС Java Сбер.csv";
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        scanner.useDelimiter("\r?\n");

        while (scanner.hasNext()) {
            City city = parseCSVLine(scanner.next());
            System.out.println(city + "\n");
        }
        scanner.close();
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