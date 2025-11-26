package ru.gstu.mockgen;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private static final String TEST_FILE = "test_output.json";

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testGeneratePersonNotNull() {
        Person person = DataGenerator.generatePerson();
        assertNotNull(person);
        assertNotNull(person.getName());
        assertNotNull(person.getSurname());
        assertTrue(person.getAge() >= 18 && person.getAge() <= 77);
        assertNotNull(person.getEmail());
        assertNotNull(person.getPhone());
        assertNotNull(person.getAddress());
    }

    @Test
    void testGeneratePersonsCount() {
        int count = 5;
        Person[] persons = DataGenerator.generatePersons(count);
        assertEquals(count, persons.length);
        for (Person p : persons) {
            assertNotNull(p);
        }
    }

    @Test
    void testWritePersonsToJsonCreatesFile() throws IOException {
        Person[] persons = {
                new Person("Test", "User", 25, "test.user@example.com", "+79991234567", "ул. Тестовая, д. 1"),
                new Person("John", "Doe", 30, "john.doe@example.com", "+79997654321", "ул. Примерная, д. 2")
        };

        JsonWriter.writePersonsToJson(persons, TEST_FILE);

        Path path = Paths.get(TEST_FILE);
        assertTrue(Files.exists(path), "Файл должен быть создан");
        assertTrue(Files.size(path) > 0, "Файл не должен быть пустым");

        // Проверка валидности JSON
        ObjectMapper mapper = new ObjectMapper();
        Person[] loaded = mapper.readValue(Files.readString(path), Person[].class);
        assertEquals(2, loaded.length);
        assertEquals("Test", loaded[0].getName());
        assertEquals("John", loaded[1].getName());
    }
}