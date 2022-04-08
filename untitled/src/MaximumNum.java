import java.util.ArrayList;
import java.util.Collections;

public class MaximumNum {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();

        ArrayList<String> arrayList = new ArrayList<>();
        for (Integer num:numbers) {
            arrayList.add(String.valueOf(num));
        }
        //Collections.sort(arrayList, (a, b) -> (b + a).compareTo(a + b));
        arrayList.sort((a, b) -> (b + a).compareTo(a + b));
        if (arrayList.get(0).startsWith("0")) return "0";
        for (String s:arrayList) {
            //answer += s; 아래꺼는 StringBuilder이용.
            //문자열을 합칠때는 빌더가 더 성능이 좋답니다
            answer.append(s);
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        MaximumNum maximumNum = new MaximumNum();
        int[] numbers = {6, 10, 2};
        System.out.println(maximumNum.solution(numbers));
    }
}
