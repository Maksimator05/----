public class Main {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();

        // Регистрируем обработчики данных
        dataManager.registerDataProcessor(new DataFilter());
        dataManager.registerDataProcessor(new DataTransformer());
        dataManager.registerDataProcessor(new DataAggregator());

        // Загружаем данные из файла
        dataManager.loadData("C:\\Типо диск D\\Apps\\Доклады и работы по универу\\ИТ 2 семак\\Лабы\\Labs\\Lab8\\input.txt");

        // Обрабатываем данные с использованием многопоточности и Stream API
        dataManager.processData();

        // Сохраняем обработанные данные в новый файл
        dataManager.saveData("C:\\Типо диск D\\Apps\\Доклады и работы по универу\\ИТ 2 семак\\Лабы\\Labs\\Lab8\\output.txt");
    }
}
