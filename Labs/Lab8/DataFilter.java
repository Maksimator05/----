import java.util.List;

public class DataFilter {
    @DataProcessor
    public void filterData(List<String> data) {
        // Пример фильтрации: убираем строки, содержащие "so"
        data.removeIf(line -> line.contains("so"));
    }
}
