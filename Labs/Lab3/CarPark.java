import java.util.LinkedList;
import java.util.Scanner;

// Класс для автомобиля с маркой, моделью и годом выпуска
class Car {
    String make;  // Марка автомобиля
    String model; // Модель автомобиля
    int year;     // Год выпуска

    // Конструктор
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Переопределяем метод toString для удобного вывода
    @Override
    public String toString() {
        return make + " " + model + " (" + year + ")";
    }
}

// Узел для хранения данных автомобиля и номерного знака
class CarNode {
    String licensePlate;
    Car car;

    public CarNode(String licensePlate, Car car) {
        this.licensePlate = licensePlate;
        this.car = car;
    }
}

public class CarPark {
    // Используем LinkedList для хранения автомобилей, чтобы управлять коллизиями
    LinkedList<CarNode> cars = new LinkedList<>();

    // Метод для добавления автомобиля
    public void addCar(String licensePlate, String make, String model, int year) {
        // Проверяем, существует ли автомобиль с таким номерным знаком
        for (CarNode node : cars) {
            if (node.licensePlate.equals(licensePlate)) {
                System.out.println("Автомобиль с таким номерным знаком уже существует.");
                return;
            }
        }
        // Если автомобиля с таким номерным знаком нет, добавляем его
        cars.add(new CarNode(licensePlate, new Car(make, model, year)));
    }

    // Метод для поиска автомобиля
    public Car getCar(String licensePlate) {
        for (CarNode node : cars) {
            if (node.licensePlate.equals(licensePlate)) {
                return node.car;
            }
        }
        return null; // Автомобиль не найден
    }

    // Метод для удаления автомобиля
    public void removeCar(String licensePlate) {
        cars.removeIf(node -> node.licensePlate.equals(licensePlate));
    }

    // Метод для вывода всех автомобилей
    public void showAllCars() {
        if (cars.isEmpty()) {
            System.out.println("Автостоянка пуста.");
        } else {
            for (CarNode node : cars) {
                System.out.println("Номерной знак: " + node.licensePlate + ", " + node.car);
            }
        }
    }

    public static void main(String[] args) {
        CarPark carPark = new CarPark();
        Scanner scanner = new Scanner(System.in);

        // Добавление предустановленных автомобилей
        carPark.addCar("ABC123", "Toyota", "Camry", 2010);
        carPark.addCar("XYZ789", "Honda", "Civic", 2015);
        carPark.addCar("LMN456", "Ford", "Mustang", 2018);

        // Добавление автомобиля вручную
        System.out.println("Введите номерной знак, чтобы добавить автомобиль:");
        String newLicensePlate = scanner.nextLine();
        System.out.println("Введите марку автомобиля:");
        String make = scanner.nextLine();
        System.out.println("Введите модель автомобиля:");
        String model = scanner.nextLine();
        System.out.println("Введите год выпуска автомобиля:");
        int year = Integer.parseInt(scanner.nextLine());

        carPark.addCar(newLicensePlate, make, model, year);
        System.out.println("Машина добавлена!");

        // Поиск автомобиля
        System.out.println("Введите номерной знак для поиска:");
        String searchPlate = scanner.nextLine();
        Car foundCar = carPark.getCar(searchPlate);
        if (foundCar != null) {
            System.out.println("Найдено: " + foundCar);
        } else {
            System.out.println("Машина не найдена.");
        }

        // Удаление автомобиля
        System.out.println("Введите номерной знак, чтобы удалить автомобиль:");
        String removePlate = scanner.nextLine();
        carPark.removeCar(removePlate);

        // Показ всех автомобилей
        carPark.showAllCars();

        scanner.close();
    }
}
