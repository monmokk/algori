import java.util.Arrays;
import java.util.HashMap;
import static java.util.stream.Collectors.*;

public class Camouflage {
    public int solution(String[][] clothes){
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<>();
        for (String[] clothe : clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
        }
        for (String key : map.keySet()) {
            answer *= (map.get(key) + 1);
        }
        answer -= 1;

        return answer;
    }

    public int streamSolution(String[][] clothes){
        return Arrays.stream(clothes)
                .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
                .values()
                .stream().reduce(1L, (x, y) -> x * (y + 1)).intValue() - 1;
        //.collect(reducing(1L, (x, y) -> x * (y + 1))).intValue() - 1;
    }



    public static void main(String[] args) {
        Camouflage camouflage = new Camouflage();
        String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};


        System.out.println(camouflage.solution(clothes));
        System.out.println(camouflage.streamSolution(clothes));
    }
}
