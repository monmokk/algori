import java.util.Arrays;

public class Kth {
    int[] solution(int[] arr, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int a, b, c;
            a = commands[i][0];
            b = commands[i][1];
            c = commands[i][2];
            int[] tmp = Arrays.copyOfRange(arr, a-1, b);
            Arrays.sort(tmp);
            answer[i] = tmp[c-1];
        }

        return answer;
    }

    int[] progressiveSolution(int[] arr, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int[] tmp = Arrays.copyOfRange(arr, commands[i][0]-1, commands[i][1]);
            //copyOfRange(복사할 원본 배열, 시작 인덱스, 마지막 인덱스)
            Arrays.sort(tmp);
            answer[i] = tmp[commands[i][2]-1];
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2,5,3}, {4,4,1}, {1,7,3}};
        Kth kth = new Kth();
        System.out.println(Arrays.toString(kth.solution(arr, commands)));
        System.out.println(Arrays.toString(kth.progressiveSolution(arr, commands)));
    }
}
