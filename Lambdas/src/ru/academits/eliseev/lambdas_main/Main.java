package ru.academits.eliseev.lambdas_main;

import ru.academits.eliseev.lambdas.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();

        persons.add(new Person("Артем", 20));
        persons.add(new Person("Василий", 29));
        persons.add(new Person("Андрей", 15));
        persons.add(new Person("Андрей", 12));
        persons.add(new Person("Иван", 28));

        // А
        List<String> uniquePersons = persons.stream()
                .map(Person::name)
                .distinct()
                .toList();

        // Б
        System.out.println("Имена:" + uniquePersons.stream()
                .collect(Collectors.joining(", ", " ", ".")));

        // В
        List<Person> lessThan18YearsOld = persons.stream()
                .filter(person -> person.age() < 18)
                .toList();

        OptionalDouble averageAge = lessThan18YearsOld.stream()
                .mapToInt(Person::age)
                .average();
        averageAge.ifPresent(value -> System.out.println("Средний возраст людей младше 18 лет: " + value));

        // Г
        Map<String, Double> averageAgeByName = persons.stream()
                .collect(Collectors.groupingBy(
                        Person::name,
                        Collectors.averagingInt(Person::age)
                ));
        System.out.println("Map, в котором ключ - имя человека, значение - средний возраст людей с этим именем: " + averageAgeByName);

        // Д
        List<Person> between20And45 = persons.stream()
                .filter(person -> person.age() >= 20 && person.age() <= 45).toList();

        System.out.println("Люди, возраст которых от 20 до 45 лет, в порядке убывания возраста: " +
                between20And45.stream()
                .sorted((p1, p2) -> -1 * (p1.age() - p2.age()))
                .map(Person::name)
                .collect(Collectors.joining(", "))
        );
    }
}
