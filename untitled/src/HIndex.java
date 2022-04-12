import java.util.Arrays;

public class HIndex {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i; // 논문편수

            if (citations[i] >= h) {
                answer = h;
                break;
            }
        }
        return answer;
    }

    public int progressiveSolution(int[] citations) {
        Arrays.sort(citations);
        int max = 0;
        for (int i = citations.length-1; i>-1; i--) {
            int min = Math.min(citations[i], citations.length - i);
            if (max < min) max = min;
        }

        return max;
    }

    public static void main(String[] args) {
        HIndex hIndex = new HIndex();
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println(hIndex.solution(citations));
        System.out.println(hIndex.progressiveSolution(citations));


    }
}
