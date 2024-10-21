import java.util.HashMap;
import java.util.Scanner;


class Car {
    String make;  // Марка автомобиля
    String model; 
    int year;     

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

   
    @Override
    public String toString() {
        return make + " " + model + " (" + year + ")";
    }
}

public class CarPark {
    // HashMap для хранения автомобилей
    HashMap<String, Car> cars = new HashMap<>();

    // Метод для добавления автомобиля
    public void addCar(String licensePlate, String make, String model, int year) {
        cars.put(licensePlate, new Car(make, model, year));
    }

    // Метод для поиска автомобиля
    public Car getCar(String licensePlate) {
        return cars.get(licensePlate);
    }

    // Метод для удаления автомобиля
    public void removeCar(String licensePlate) {
        cars.remove(licensePlate);
    }

    // Метод для вывода всех автомобилей
    public void showAllCars() {
        if (cars.isEmpty()) {
            System.out.println("Автостоянка пуста.");
        } else {
            for (String licensePlate : cars.keySet()) {
                System.out.println("Номерной знак: " + licensePlate + ", " + cars.get(licensePlate));
            }
        }
    }

    public static void main(String[] args) {
        CarPark carPark = new CarPark();
        Scanner scanner = new Scanner(System.in);

        carPark.addCar("ABC123", "Toyota", "Mark 2", 2010);
        carPark.addCar("XYZ789", "Chevrolet", "Chevele", 2015);
        carPark.addCar("LMN456", "Ford", "Mustang", 2018);

       
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

        System.out.println("Введите номерной знак для поиска:");
        String searchPlate = scanner.nextLine();
        Car foundCar = carPark.getCar(searchPlate);
        if (foundCar != null) {
            System.out.println("Найдено: " + foundCar);
        } else {
            System.out.println("Машина не найдена.");
        }

        
        System.out.println("Введите номерной знак, чтобы удалить автомобиль:");
        String removePlate = scanner.nextLine();
        carPark.removeCar(removePlate);

        
        carPark.showAllCars();
    }
}