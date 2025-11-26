package ru.gstu.mockgen;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class JsonWriter {

    public static void writePersonsToJson(Person[] persons, String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Красивый вывод

        String json = mapper.writeValueAsString(persons);

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json);
        }

        System.out.println("Data successfully written to: " + Paths.get(filename).toAbsolutePath());
    }
}