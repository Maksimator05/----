import java.util.*;
import java.util.concurrent.*;

/*
 * Вариант 9
 */

public class WarehouseTransferWithBarrier {
    private static final int MAX_WEIGHT = 150; // Максимальный вес для одной поездки

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Список товаров с их весами
        List<Item> items = Arrays.asList(
            new Item(50), new Item(40), new Item(60),
            new Item(30), new Item(70), new Item(80),
            new Item(20), new Item(40), new Item(50),
            new Item(10), new Item(90), new Item(30)
        );

        // Создаем пул потоков с 3 грузчиками
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Создаем CyclicBarrier, который синхронизирует 3 грузчиков
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("All loaders are ready"));

        // Для каждого грузчика создаём задачу
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        // Разбиваем список товаров на несколько частей для каждого грузчика
        List<List<Item>> partitions = partitionItems(items, 3);

        for (int i = 0; i < 3; i++) {
            int loaderId = i;
            List<Item> loaderItems = partitions.get(i);

            // Асинхронное выполнение задачи для каждого грузчика
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    processItemsForLoader(loaderId, loaderItems, barrier);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, executor);

            futures.add(future);
        }

        // Ожидаем завершения всех задач
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // Завершаем работу пула потоков
        executor.shutdown();
    }

    // Метод для обработки товаров для одного грузчика
    private static void processItemsForLoader(int loaderId, List<Item> items, CyclicBarrier barrier) throws InterruptedException {
        int currentWeight = 0;
        List<Item> toCarry = new ArrayList<>();

        for (Item item : items) {
            if (currentWeight + item.getWeight() > MAX_WEIGHT) {
                // Если грузчик набрал максимальный вес, он переносит товары
                deliverItems(loaderId, toCarry);

                // Очищаем список и начинаем с нового набора товаров
                currentWeight = 0;
                toCarry.clear();
            }
            // Добавляем товар в список для переноса
            toCarry.add(item);
            currentWeight += item.getWeight();
        }

        // Переносим оставшиеся товары, если есть
        if (!toCarry.isEmpty()) {
            deliverItems(loaderId, toCarry);
        }

        // Грузчик ждет других на барьере
        try {
            barrier.await();  // Ожидает других грузчиков на барьере
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    // Метод для симуляции переноса товаров
    private static void deliverItems(int loaderId, List<Item> items) throws InterruptedException {
        System.out.println("loader " + loaderId + " transfers: " + items);
        Thread.sleep(1000);  // Симуляция времени переноса
        System.out.println("loader " + loaderId + " completed the transfer.");
    }

    // Разбиение списка товаров на части для каждого грузчика
    private static List<List<Item>> partitionItems(List<Item> items, int numPartitions) {
        List<List<Item>> partitions = new ArrayList<>();
        int partitionSize = (int) Math.ceil((double) items.size() / numPartitions);

        for (int i = 0; i < numPartitions; i++) {
            int fromIndex = i * partitionSize;
            int toIndex = Math.min((i + 1) * partitionSize, items.size());
            partitions.add(new ArrayList<>(items.subList(fromIndex, toIndex)));
        }

        return partitions;
    }
}

// Класс для товара с его весом
class Item {
    private int weight;

    public Item(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Item{" + "weight=" + weight + '}';
    }
}

/*
 * Объяснение:
 * 
 * CyclicBarrier:
 * CyclicBarrier — это синхронизатор, который позволяет нескольким потокам (в нашем случае грузчикам) ждать друг друга. 
 * Когда все потоки достигнут барьера, они будут продолжать выполнение. В нашем примере барьер используется для того, 
 * чтобы грузчики могли синхронизироваться и отправиться на склад только когда все они собрали свои товары.
 * В коде barrier.await() заставляет каждый грузчик остановиться и ожидать других, пока все они не достигнут барьера. 
 * Как только все грузчики достигнут барьера, они могут отправиться на склад.
 * 
 * Распределение товаров:
 * Товары разбиваются между грузчиками с помощью метода partitionItems(). Каждый грузчик обрабатывает свою часть товаров.
 * 
 * Процесс переноса товаров:
 * Каждый грузчик собирает товары до тех пор, пока их суммарный вес не превышает 150 кг. Когда грузчик достигает этого лимита, 
 * он выполняет перенос товаров на склад и ждет других.
 * 
 * Асинхронное выполнение с использованием CompletableFuture:
 * Для каждого грузчика создается CompletableFuture.runAsync(), что позволяет каждому грузчику выполнять свою задачу асинхронно.
 * 
 * Синхронизация:
 * Все грузчики ждут на барьере, пока не завершат свои операции, а затем отправляются на склад.
 * В конце каждого этапа, когда все грузчики завершат сбор своих товаров, мы выводим сообщение, что все готовы для переноса товаров на склад.
 */