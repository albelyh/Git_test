import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product("ham", 365));
        list.add(new Product("duck", 405));
        list.add(new Product("milk", 60));
        list.add(new Product("pasta", 350));
        int input = 0;
        while (input != 3) {
            System.out.println("""
                    1. Узнать калорийность продукта                                        
                    2. Добавить продукт                                        
                    3. Выход
                    """);
            input = scan.nextInt();
            switch (input) {
                case 1 -> {
                    System.out.println("Введите название продукта");
                    String name = scan.next();
                    boolean search = false;
                    for (Product product : list) {
                        if (product.getProductName().equalsIgnoreCase(name)) {
                            search = true;
                            System.out.println("Калорийность составляет " + product.getCalories());
                            System.out.println();
                            break;
                        }
                    }
                    if (!search) {
                        System.out.println("Данного продукта нет в списке\n");
                    }
                }
                case 2 -> {
                    System.out.println("Введите название продукта");
                    String newName = scan.next();
                    System.out.println("Введите количество калорий");
                    int numberOfCalories = scan.nextInt();
                    boolean result = list.contains(new Product(newName, numberOfCalories));
                    if (!result) {
                        list.add(new Product(newName, numberOfCalories));
                        System.out.println("Продукт успешно добавлен\n");
                    }

                }
            }
        }
    }
}

