package ru.gstu.mockgen;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.Random;

public class DataGenerator {

    private static final Random random = new Random();

    private static String[] firstNames = {"Александр", "Мария", "Дмитрий", "Екатерина", "Иван", "Ольга", "Сергей", "Анна", "Николай", "Татьяна"};
    private static String[] lastNames = {"Иванов", "Петров", "Сидоров", "Кузнецов", "Смирнов", "Попов", "Васильев", "Морозов", "Федоров", "Григорьев"};
    private static String[] domains = {"gmail.com", "yandex.ru", "mail.ru", "hotmail.com", "outlook.com"};

    public static Person generatePerson() {
        String name = firstNames[random.nextInt(firstNames.length)];
        String surname = lastNames[random.nextInt(lastNames.length)];
        int age = 18 + random.nextInt(60); // от 18 до 77
        String email = name.toLowerCase() + "." + surname.toLowerCase() + "@" + domains[random.nextInt(domains.length)];
        String phone = "+375" + RandomStringUtils.randomNumeric(10);
        String address = "ул. " + RandomStringUtils.randomAlphabetic(5) + ", д. " + (1 + random.nextInt(100));

        return new Person(name, surname, age, email, phone, address);
    }

    public static Person[] generatePersons(int count) {
        Person[] persons = new Person[count];
        for (int i = 0; i < count; i++) {
            persons[i] = generatePerson();
        }
        return persons;
    }
}