/*
 * В этом варианте создаются несколько потоков, каждый из которых будет обрабатывать свою строку матрицы, и искать максимальный элемент в своей строке.
 */
public class MaxElementInMatrix {
    public static void main(String[] args) throws InterruptedException {
        int[][] matrix = {
            {1, 5, 9},
            {3, 7, 8},
            {6, 2, 4}
        };

        // Массив для хранения результатов от каждого потока
        int[] results = new int[matrix.length];
        Thread[] threads = new Thread[matrix.length];

        // Запуск потока для каждой строки
        for (int i = 0; i < matrix.length; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                int maxInRow = matrix[row][0];
                for (int j = 1; j < matrix[row].length; j++) {
                    if (matrix[row][j] > maxInRow) {
                        maxInRow = matrix[row][j];
                    }
                }
                results[row] = maxInRow;
            });
            threads[i].start();
        }

        // Ожидание завершения всех потоков
        for (Thread thread : threads) {
            thread.join();
        }

        // Поиск наибольшего элемента среди результатов
        int globalMax = results[0];
        for (int i = 1; i < results.length; i++) {
            if (results[i] > globalMax) {
                globalMax = results[i];
            }
        }

        System.out.println("The largest element in the matrix: " + globalMax);
    }
}

/*
* В этом варианте используется пул потоков, чтобы разделить матрицу на части и каждый поток будет обрабатывать свою часть.
*/
/*
import java.util.concurrent.*;
import java.util.*;

public class MaxElementInMatrix {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[][] matrix = {
            {1, 5, 9},
            {3, 7, 8},
            {6, 2, 4}
        };

        // Размер матрицы
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Создание пула потоков
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Список для хранения Future объектов
        List<Future<Integer>> futures = new ArrayList<>();

        // Разбиение на части и запуск потоков
        for (int i = 0; i < rows; i++) {
            final int row = i;
            futures.add(executorService.submit(() -> {
                int maxInRow = matrix[row][0];
                for (int j = 1; j < cols; j++) {
                    if (matrix[row][j] > maxInRow) {
                        maxInRow = matrix[row][j];
                    }
                }
                return maxInRow;
            }));
        }

        // Ожидание завершения всех потоков и получение результатов
        int globalMax = Integer.MIN_VALUE;
        for (Future<Integer> future : futures) {
            int rowMax = future.get();
            if (rowMax > globalMax) {
                globalMax = rowMax;
            }
        }

        // Завершаем работу пула потоков
        executorService.shutdown();

        System.out.println("Наибольший элемент в матрице: " + globalMax);
    }
}
*/
/*
 Основные моменты:
 Вариант 1: Каждый поток работает с отдельной строкой, и результаты собираются в массив. После завершения всех потоков главный поток анализирует результаты и находит максимальное значение.
 Вариант 2: Используется ExecutorService для управления пулом потоков, где каждый поток обрабатывает свою строку матрицы. Результаты собираются через Future, и после завершения всех потоков главный поток анализирует результаты и находит максимальное значение.
 Оба подхода используют многозадачность для ускорения поиска наибольшего элемента в матрице, и отличаются методом управления потоками: в первом случае потоки создаются вручную, во втором — с помощью пула потоков.
 */