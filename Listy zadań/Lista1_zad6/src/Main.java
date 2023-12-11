import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
public class Main {
    public static void main(String[] args) {
        TreeMap<String, Integer> population_map = new TreeMap<>();

        population_map.put("Wrocław", 640000);
        population_map.put("Warszawa", 1800000);
        population_map.put("Gdańsk", 420000);
        population_map.put("Kraków", 230000);
        population_map.put("Częstochowa", 72000);

        Population_Comparator comparator=new Population_Comparator(population_map);
        TreeMap<String,Integer> sorted_population_map = new TreeMap<>(comparator);
        sorted_population_map.putAll(population_map);

        for(Map.Entry<String, Integer> entry : sorted_population_map.entrySet()){
            System.out.println(entry.getKey()+ ": "+entry.getValue());
        }
    }
}