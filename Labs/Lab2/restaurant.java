// Абстрактный базовый класс Establishment (Заведение)
abstract class Establishment {
    // Поля (инкапсуляция)
    protected String name;
    protected String address;
    protected int capacity;
    //capacity - вместимость

    //Каунтер 
    private static int objectCounter = 0;

    // Конструктор по умолчанию
    public Establishment() {
        objectCounter++;
    }

    // Конструктор с параметрами
    public Establishment(String name, String address, int capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        objectCounter++;
    }

    // Абстрактные методы (абстракция)
    public abstract void operate();

    public abstract void close();

    // Геттеры и сеттеры (инкапсуляция)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Статический метод для получения количества созданных объектов
    public static int getObjectCounter() {
        return objectCounter;
    }
}

// Класс Cafe (Кафе) — наследуется от Establishment
class Cafe extends Establishment {
    private String menuType;

    // Конструктор по умолчанию
    public Cafe() {
        super();
        this.menuType = "Standard";
    }

    // Конструктор с параметрами (перегрузка)
    public Cafe(String name, String address, int capacity, String menuType) {
        super(name, address, capacity);
        this.menuType = menuType;
    }

    // Переопределение абстрактных методов operate и close
    @Override
    public void operate() {
        System.out.println(name + " По адресу: " + address + " Кафе Открыто и обслуживает клиентов. " + " Вместимостью " + capacity + " Меню - " + menuType);
    }

    @Override
    public void close() {
        System.out.println(name + " Кафе Закрыто на обслуживание.");
    }

    // Геттеры и сеттеры
    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
}

// Класс Shop (Магазин) — наследуется от Establishment
class Shop extends Establishment {
    private String productType;

    // Конструктор по умолчанию
    public Shop() {
        super();
        this.productType = "General";
    }

    // Конструктор с параметрами (перегрузка)
    public Shop(String name, String address, int capacity, String productType) {
        super(name, address, capacity);
        this.productType = productType;
    }

    // Переопределение абстрактных методов operate и close
    @Override
    public void operate() {
        System.out.println(name + " По адресу: " + address + " Магазин Открыт и торгует товарами. " + " Вместимостью " + capacity + " Продукты - " + productType);
    }

    @Override
    public void close() {
        System.out.println(name + " Магазин Закрыт.");
    }

    // Геттеры и сеттеры
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}

// Класс Library (Библиотека) — наследуется от Establishment
class Library extends Establishment {
    private int bookCount;

    // Конструктор по умолчанию
    public Library() {
        super();
        this.bookCount = 1000;
    }

    // Конструктор с параметрами (перегрузка)
    public Library(String name, String address, int capacity, int bookCount) {
        super(name, address, capacity);
        this.bookCount = bookCount;
    }

    // Переопределение абстрактных методов operate и close
    @Override
    public void operate() {
        System.out.println(name + " По адресу: " + address + " Библиотека Открыта для посетителей. " + " Вместимостью " + capacity + " Количество книг - " + bookCount);
    }

    @Override
    public void close() {
        System.out.println(name + " Библиотека Закрыта.");
    }

    // Геттеры и сеттеры
    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }
}

class sklad extends Library{
    private String Type;


    public sklad() {
        super();
        this.Type = "Full";
    }

    public sklad(String name, String address, int capacity, int bookCount, String Type) {
        super(name, address, capacity, bookCount);
        this.Type = Type;
    }

    @Override
    public void operate() {
        System.out.println(name + address + " Склад открыт и " + Type);
    }

    @Override
    public void close() {
        System.out.println(name + " Склад закрыт и пуст.");
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
}

// Главный класс для демонстрации работы
public class restaurant {
    public static void main(String[] args) {
        // Создание объектов
        Cafe cafe = new Cafe("Caffe Milano", " ул. Ленина, 10", 50, "Italian");
        Shop shop = new Shop("Tech Store", " ул. Советская, 25", 100, "Electronics");
        Library library = new Library("Городская библиотека", " ул. Кирова, 5", 200, 5000);
        sklad sklad = new sklad ("Городская библиотека", " ул. Кирова, 5", 200, 5000, "Заполняется");
        // Вызов методов operate и close
        cafe.operate();
        cafe.close();

        shop.operate();
        shop.close();

        library.operate();
        library.close();
        sklad.operate();
        sklad.close();

        // Вывод количества созданных объектов
        System.out.println("Количество созданных заведений: " + Establishment.getObjectCounter());
    }
}
