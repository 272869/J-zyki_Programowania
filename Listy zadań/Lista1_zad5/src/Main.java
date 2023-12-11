import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        people.add(new Person("Anna", 30));
        people.add(new Person("Bartek", 25));
        people.add(new Person("Celina", 35));
        people.add(new Person("Daniel", 22));

        System.out.println("Lista przed sortowaniem:");
        for (Person person : people) {
            System.out.println(person.getName() + " - " + person.getAge() + " lat");
        }
        Collections.sort(people, new AgeComparator());
        System.out.println("\nLista po sortowaniu:");
        for (Person person : people) {
            System.out.println(person.getName() + " - " + person.getAge() + " lat");
        }
    }
}