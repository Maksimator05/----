// import java.lang.reflect.Array;

public class MyStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(10);
        
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.push(4);
        System.out.println(stack.pop());
    }
}

class Stack<T> {
    private T[] data;
    private int size;
    private int capacity;

    // Конструктор с заданной емкостью стека
    public Stack(int capacity) {
        this.capacity = capacity;
        this.data = (T[]) new Object[capacity]; // Создание массива для хранения элементов
        this.size = 0;
    }

    // Метод для добавления элемента в стек
    public void push(T element) {
        if (size == capacity) {
            throw new IllegalStateException("Stack Overflow");
        }
        data[size++] = element;  // Добавляем элемент в стек и увеличиваем размер
    }

    // Метод для удаления элемента из стека
    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        T topElement = data[--size];  // Уменьшаем размер и извлекаем верхний элемент
        data[size] = null;  // Очищаем ссылку на удаленный элемент (необязательно, но полезно для сборщика мусора)
        return topElement;
    }

    // Метод для получения верхнего элемента стека без его удаления
    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }
}

/*
В Java нельзя напрямую создать массив обобщенного типа (T[]), 
потому что Java не поддерживает создание массивов с параметрическим типом на этапе компиляции. 
Это связано с тем, что в Java используется типовая стирание, и тип T известен только во время выполнения, 
а массивы — это структура данных, которая требует конкретного типа во время компиляции.

Тем не менее, можно обойти это ограничение с помощью приведения типов (в том числе создания массива типа Object[], 
а затем приведения его к T[]), но при этом потребуется использовать небезопасное приведение типов. 
Для этого можно передать Class<T> в качестве параметра конструктора, что позволит создать массив с помощью Array.newInstance().

Вот рабочий пример:

import java.lang.reflect.Array;

public class GenericArray<T> {
    private T[] array;

    // Конструктор, который принимает размер массива и тип данных
    public GenericArray(Class<T> clazz, int size) {
        // Создаем массив типа T с использованием Array.newInstance()
        @SuppressWarnings("unchecked")
        this.array = (T[]) Array.newInstance(clazz, size); // Приведение типа в безопасной форме
    }

    // Метод для доступа к элементам массива
    public T get(int index) {
        return array[index];
    }

    // Метод для добавления элемента
    public void set(int index, T value) {
        array[index] = value;
    }

    // Метод для получения длины массива
    public int length() {
        return array.length;
    }

    public static void main(String[] args) {
        // Пример использования с Integer
        GenericArray<Integer> integerArray = new GenericArray<>(Integer.class, 10);
        integerArray.set(0, 42);
        System.out.println(integerArray.get(0));

        // Пример использования с String
        GenericArray<String> stringArray = new GenericArray<>(String.class, 5);
        stringArray.set(0, "Hello");
        System.out.println(stringArray.get(0));
    }
}

Пояснение:
Array.newInstance(clazz, size) — это метод из пакета java.lang.reflect, 
который позволяет создавать массивы с обобщенным типом во время выполнения. 
Это решение работает, потому что Array.newInstance() может создать массив любого типа, переданного в параметре clazz.

clazz (параметр типа Class<T>) — это объект класса, который передается в конструктор. 
Он необходим для того, чтобы JVM знал тип, который мы хотим использовать для создания массива, 
так как во время выполнения тип T будет известен только в рамках этого параметра.

@SuppressWarnings("unchecked") — это аннотация, которая используется для подавления предупреждений компилятора о 
небезопасных приведениях типов. Мы привели Object[] к T[], что является небезопасным действием, 
но в данном контексте это приемлемо.

set() и get() — стандартные методы для работы с элементами массива.

Почему это решение работает:
Массивы в Java должны быть созданы с конкретным типом во время компиляции, но с помощью рефлексии (Array.newInstance) 
мы можем обойти это ограничение и создать массив динамически.
При использовании Class<T> передается точный тип, который используется для создания массива, и 
Java гарантирует, что такой массив можно создать в момент выполнения.

Альтернативные подходы:
Использование коллекций: Если массивы не являются обязательными, можно использовать стандартные коллекции, 
такие как ArrayList, которые позволяют хранить элементы с обобщенными типами без необходимости в рефлексии.

Приведение типов: Приведение типа Object[] к T[] может быть сделано напрямую, но это вызовет предупреждения компилятора, 
так как это небезопасно. Выше мы использовали безопасный вариант с рефлексией.

Этот подход позволяет эффективно работать с обобщенными типами в Java, 
не получая ошибок компиляции и без необходимости в прямом создании массивов с обобщенными типами.
 */