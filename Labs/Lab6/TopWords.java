import java.io.File;
import java.io.FileNotFoundException; 
import java.util.*; 
 
public class TopWords { 
    public static void main(String[] args) {
        String filePath = "C:\\Типо диск D\\Apps\\Доклады и работы по универу\\ИТ 2 семак\\Лабы\\Labs\\Lab6\\texts.txt"; 
        
        File file = new File(filePath); 
        
        Scanner scanner = null; 
        try { 
            scanner = new Scanner(file); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        Map<String, Integer> wordFrequency = new HashMap<String, Integer>();
        
        while(scanner.hasNext()) {
            String word = scanner.next().replaceAll(",|\\.|\\s", "");
            wordFrequency.put(word, (wordFrequency.get(word) == null ? 1 : wordFrequency.get(word) + 1));
        }
        
        scanner.close();
        
        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            list.add(entry);
        }
        
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override 
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue() >= o2.getValue() ? -1 : 1);
            }
        });

        // выводим топ-10 слов 
        int count = 0;
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            if (++count == 10) break;
        }
    }
}