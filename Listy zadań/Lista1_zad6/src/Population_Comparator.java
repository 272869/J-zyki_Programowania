import java.util.Comparator;
import java.util.Map;

public class Population_Comparator implements Comparator<String> {
    private Map<String, Integer> baseMap;
    public Population_Comparator(Map<String, Integer> baseMap){
        this.baseMap=baseMap;
    }
    public int compare(String city1, String city2){
        if(baseMap.get(city1)>=baseMap.get(city2)){
            return 1;
        }else {
            return -1;
        }
    }
}
