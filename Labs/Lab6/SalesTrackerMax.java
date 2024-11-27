import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
// import java.util.Scanner;

public class SalesTrackerMax {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);

        SalesTracker salesTracker = new SalesTracker();
        
        salesTracker.addSale("prod1", 10);
        salesTracker.addSale("prod2", 5);
        salesTracker.addSale("prod3", 7);
        salesTracker.addSale("prod1", 3);

        salesTracker.displaySales();
        System.out.println("Total: " + salesTracker.calculateTotalSales());
        System.out.println("Most pop. prod.: " + salesTracker.findMostPopularItem());

        /*while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить продажу");
            System.out.println("2. Показать список проданных товаров");
            System.out.println("3. Посчитать общую сумму продаж");
            System.out.println("4. Найти наиболее популярный товар");
            System.out.println("5. Выйти");
            System.out.print("Выберите действие: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки
            
            switch (choice) {
                case 1:
                    // Добавление продажи
                    System.out.print("Введите название товара: ");
                    String item = scanner.nextLine();
                    System.out.print("Введите количество проданных единиц: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Считываем символ новой строки
                    salesTracker.addSale(item, quantity);
                    break;
                case 2:
                    // Показать список товаров
                    salesTracker.displaySales();
                    break;
                case 3:
                    // Посчитать общую сумму продаж
                    double totalSales = salesTracker.calculateTotalSales();
                    System.out.println("Общая сумма продаж: " + totalSales + " руб.");
                    break;
                case 4:
                    // Найти наиболее популярный товар
                    String mostPopularItem = salesTracker.findMostPopularItem();
                    System.out.println("Наиболее популярный товар: " + mostPopularItem);
                    break;
                case 5:
                    // Выход из программы
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        } */
    }
}

class SalesTracker {
    private ConcurrentHashMap<String, AtomicInteger> sales;

    public SalesTracker() {
        sales = new ConcurrentHashMap<>();
    }

    public void addSale(String item, int quantity) {
        sales.computeIfAbsent(item, k -> new AtomicInteger(0)).addAndGet(quantity);
    }

    public void displaySales() {
        if (sales.isEmpty()) {
            System.out.println("List empty");
        } else {
            System.out.println("List sales:");

            for (Map.Entry<String, AtomicInteger> entry : sales.entrySet()) {
                System.out.println("prod: " + entry.getKey() + ", count: " + entry.getValue());
            }
        }
    }

    public int calculateTotalSales() {
        int total = 0;
        
        for (Map.Entry<String, AtomicInteger> entry : sales.entrySet()) {
            total += entry.getValue().get();
        }

        return total;
    }

    public String findMostPopularItem() {
        if (sales.isEmpty()) {
            return "List empty";
        }

        String mostPopularItem = null;
        int maxSales = 0;

        for (Map.Entry<String, AtomicInteger> entry : sales.entrySet()) {
            if (entry.getValue().get() > maxSales) {
                maxSales = entry.getValue().get();
                mostPopularItem = entry.getKey();
            }
        }

        return mostPopularItem;
    }
}

/*
 * Пояснение:
 * ConcurrentHashMap: Это потокобезопасная версия HashMap, которая предназначена для многозадачных приложений, 
 * где несколько потоков могут одновременно модифицировать коллекцию. В отличие от обычного HashMap, 
 * ConcurrentHashMap позволяет безопасно работать с коллекцией в многопоточной среде, не блокируя весь контейнер при изменениях. 
 * Это достигается путем разделения коллекции на сегменты, что позволяет потокам работать с различными сегментами одновременно.
 * 
 * AtomicInteger: Это класс из пакета java.util.concurrent.atomic, который предоставляет потокобезопасные операции над целым числом. 
 * Он позволяет атомарно (без прерывания другими потоками) изменять значение целого числа. Обычный Integer не является потокобезопасным, и 
 * для того, чтобы безопасно изменять его в многозадачных приложениях, нужно использовать синхронизацию или другие механизмы. 
 * AtomicInteger позволяет избежать этого с помощью оптимизированных операций.
 * 
 * Отличия:
 * ConcurrentHashMap позволяет безопасно работать с коллекцией в многозадачной среде, а обычный HashMap — нет.
 * AtomicInteger обеспечивает потокобезопасное изменение значения целочисленной переменной, в отличие от Integer, который не предоставляет потокобезопасных операций.
 * 
 * Важное:
 * Использование ConcurrentHashMap и AtomicInteger делает программу потокобезопасной и эффективной при работе в многозадачной среде, 
 * например, если несколько потоков одновременно будут добавлять товары в учетную систему.
 */