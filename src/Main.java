import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
    }

    private static void task1() {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);
        //Ошибка возникает из-за деления на 0
        //Можно сделать обработчик внутри с помощьтю тернарного оператора и выводить 0
        int c = calc.devide.apply(a, b);
        calc.println.accept(c);

        //Второй вариант сделать условие уже в классе main
        if (b > 0) {
            c = calc.devide.apply(a, b);
            calc.println.accept(c);
        } else {
            System.out.println("Cannot divide by zero");
        }
    }

    private static void task2() {
        OnTaskDoneListener listener = System.out::println;
        Worker worker = new Worker(listener);
        worker.start();
    }

    private static void task3() {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> newList = new ArrayList<>();
        for (Integer value : intList) {
            if (value > 0) {
                if (value % 2 == 0) {
                    newList.add(value);
                }
            }
        }
        newList.sort(Comparator.naturalOrder());
        for (Integer value : newList) {
            System.out.println(value);
        }
    }

    private static void task4() {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long personCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        List<String> surnames = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .filter(person -> person.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());


        List<String> sortedSurnames = persons.stream()
                .filter(person -> (person.getAge() >= 18))
                .filter(person -> (person.getAge() <= 60 && person.getSex() == Sex.WOMAN)
                || (person.getAge() <= 65 && person.getSex() == Sex.MAN))
                .filter(person -> person.getEducation() == Education.HIGHER)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

}