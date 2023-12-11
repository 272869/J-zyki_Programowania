import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ciąg liczb, oddzielając je spacją:");
        String input = scanner.nextLine();
        scanner.close();

        String[] numbersArray = input.split(" ");
        Set<Integer> hashSet = new HashSet<>();
        Set<Integer> treeSet = new TreeSet<>();

        for (String numberStr : numbersArray) {
            int number = Integer.parseInt(numberStr);
            hashSet.add(number);
            treeSet.add(number);
        }

        System.out.println("Elementy w HashSet:");
        for (int num : hashSet) {
            System.out.print(num + " ");
        }

        System.out.println("\n\nElementy w TreeSet:");
        for (int num : treeSet) {
            System.out.print(num + " ");
        }
    }
}