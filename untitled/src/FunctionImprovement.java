import java.util.*;

public class FunctionImprovement {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] tmp = new int[100];
        int day = 0;

        for (int i = 0; i < progresses.length ; i++) {
            while (progresses[i] + (speeds[i] * day) < 100) {
                day++;
            }
            tmp[day]++;
        }
        int count = 0;

        for (int n : tmp) {
            if (n != 0) {
                count++;
            }
        }
        int[] answer = new int[count];

        count = 0;

        for (int n : tmp) {
            if (n != 0) {
                answer[count++] = n;
            }
        }
        return answer;
    }

    public int[] queueSolution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            queue.add((int) Math.ceil((100.0 - progresses[i]) / speeds[i]));
        }

        List<Integer> answer = new ArrayList<>();
        while (!queue.isEmpty()) {
            int day = queue.poll();
            int cnt = 1;

            while (!queue.isEmpty() && day >= queue.peek()) {
                cnt++;
                queue.poll();
            }
            answer.add(cnt);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {

        FunctionImprovement functionImprovement = new FunctionImprovement();

        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        String str = Arrays.toString(functionImprovement.solution(progresses, speeds));
        String qStr = Arrays.toString(functionImprovement.queueSolution(progresses, speeds));
        System.out.println(str);
        System.out.println(qStr);

        progresses = new int[]{95, 90, 99, 99, 80, 99};
        speeds = new int[]{1, 1, 1, 1, 1, 1};

        str = Arrays.toString(functionImprovement.solution(progresses, speeds));
        qStr = Arrays.toString(functionImprovement.queueSolution(progresses, speeds));
        System.out.println(str);
        System.out.println(qStr);

    }
}
