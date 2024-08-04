import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        String path = "C:\\Users\\User\\IdeaProjects\\Git_test\\saveToFile.txt";
        Scanner scan = new Scanner(System.in);
        ArrayList<Product> list = new ArrayList<>();
        loadFromFile(list, path);
        if (list.isEmpty()) {
            list.add(new Product("ham", 365));
            list.add(new Product("duck", 405));
            list.add(new Product("milk", 60));
            list.add(new Product("pasta", 350));
        }
        int input = 0;
        while (input != 4) {
            System.out.println("""
                    1. Узнать калорийность продукта                                        
                    2. Добавить продукт  
                    3. Удалить продукт                                      
                    4. Выход
                    """);
            input = scan.nextInt();
            switch (input) {
                case 1 -> {
                    System.out.println("Введите название продукта");
                    String name = scan.next();
                    System.out.println("Введите вес продукта в граммах");
                    int weight = scan.nextInt();
                    boolean search = false;
                    for (Product product : list) {
                        if (product.getProductName().equalsIgnoreCase(name)) {
                            search = true;
                            System.out.println("Калорийность составляет " + (weight / 100) * product.getCalories());
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
                    } else {
                        System.out.println("Данный продукт уже есть в списке");
                    }

                }
                case 3 -> {
                    System.out.println("Введите название продукта");
                    String name = scan.next();
                    remove(list, name);
                }
                case 4 -> {
                    saveToFile(list, path);
                    System.out.println("Выход");
                }
            }
        }

    }

    static void saveToFile(ArrayList<Product> list, String path) {

        try {
            PrintWriter pw = new PrintWriter(path);
            for (Product product : list) {
                pw.println(product.productName + ";" + product.calories);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<Product> loadFromFile(ArrayList<Product> list, String path) {

        try {
            File file = new File(path);
            if (file.exists()) {
                Scanner scan = new Scanner(new File(path));
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] array = line.split(";");
                    list.add(new Product(array[0], Integer.parseInt(array[1])));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    static ArrayList<Product> remove(ArrayList<Product> list, String name) {
        boolean result = false;
        for (Product product : list) {
            if (product.productName.equals(name)) {
                result = list.remove(product);
                break;
            }
        }
        if (result) {
            System.out.println("Продукт удален");
        } else {
            System.out.println("Данного продукта нет в списке");
        }
        return list;
    }
}


