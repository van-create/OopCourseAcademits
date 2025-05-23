package ru.academits.eliseev.lambdas_task1;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("Артем", 20),
                new Person("Василий", 29),
                new Person("Андрей", 15),
                new Person("Андрей", 12),
                new Person("Иван", 28)
        );

        // А
        List<String> uniqueNames = persons.stream()
                .map(Person::name)
                .distinct()
                .toList();

        // Б
        System.out.println(uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", ".")));

        // В
        List<Person> personsUnder18 = persons.stream()
                .filter(person -> person.age() < 18)
                .toList();

        OptionalDouble personsUnder18AverageAge = personsUnder18.stream()
                .mapToInt(Person::age)
                .average();

        personsUnder18AverageAge.ifPresentOrElse(
                value -> System.out.println("Средний возраст людей младше 18 лет: " + value),
                () -> System.out.println("Нет людей младше 18 лет")
        );

        // Г
        Map<String, Double> averageAgesByNames = persons.stream()
                .collect(Collectors.groupingBy(
                        Person::name,
                        Collectors.averagingInt(Person::age)
                ));
        System.out.println("Map, в котором ключ - имя человека, значение - средний возраст людей с этим именем: " + averageAgesByNames);

        // Д
        System.out.println("Люди, возраст которых от 20 до 45 лет, в порядке убывания возраста: " +
                persons.stream()
                        .filter(person -> person.age() >= 20 && person.age() <= 45)
                        .sorted((p1, p2) -> p2.age() - p1.age())
                        .map(Person::name)
                        .collect(Collectors.joining(", "))
        );
    }
}
