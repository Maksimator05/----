import java.io.File;
import java.io.FileNotFoundException; 
import java.util.*; 
 
public class TopWords { 
    public static void main(String[] args) { 
        // указываем путь к файлу 
        String filePath = "C:\\Типо диск D\\Apps\\Доклады и работы по универу\\ИТ 2 семак\\Лабы\\Labs\\Lab6\\texts.txt"; 
        
        // создаем объект File 
        File file = new File(filePath); 
        
        // создаем объект Scanner для чтения файла 
        Scanner scanner = null; 
        try { 
            scanner = new Scanner(file); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        
        // создаем объект Map для хранения слов и их количества
        Map<String, Integer> wordFrequency = new HashMap<String, Integer>();
        
        // читаем файл по словам и добавляем их в Map
        while(scanner.hasNext()) {
            String word = scanner.next();
            // Integer freq = wordFrequency.get(word);
            wordFrequency.put(word, (wordFrequency.get(word) == null ? 1 : wordFrequency.get(word) + 1));
        }
        
        // закрываем Scanner 
        scanner.close();
        
        // создаем список из элементов Map 
        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            list.add(entry);
        }
        
        // сортируем список по убыванию количества повторений 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override 
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue() >= o2.getValue() ? -1 : 1);
            }
        });

        // выводим топ-10 слов 
        int cnt = 0;
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            if (++cnt == 10) break;
        }
    } 
    //выводим результат 
    // ******* 
}