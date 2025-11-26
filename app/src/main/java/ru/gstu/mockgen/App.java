package ru.gstu.mockgen;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        int count = 10; // Default: 10 records
        String outputFilename = "output.json";

        if (args.length > 0) {
            try {
                count = Integer.parseInt(args[0]);
                if (count <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format. Using default: 10");
            }
        }

        if (args.length > 1) {
            outputFilename = args[1];
        }

        System.out.println("Generating " + count + " random records...");

        Person[] persons = DataGenerator.generatePersons(count);

        try {
            JsonWriter.writePersonsToJson(persons, outputFilename);
            System.out.println("Data successfully written to: " + outputFilename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}