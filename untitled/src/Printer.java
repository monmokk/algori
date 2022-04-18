import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Printer {
    static class Task{
        int location;
        int priority;

        public Task(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Task> queue = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Task(i, priorities[i]));
        }

        int now = 0;
        while (!queue.isEmpty()) {
            Task cur = queue.poll();
            boolean flag = false;
            for (Task t : queue) {
                if (t.priority > cur.priority) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                queue.add(cur);
            } else {
                now ++;
                if (cur.location == location) {
                    answer = now;
                    break;
                }
            }
        }
        return answer;
    }

    public int progressiveSolution(int[] priorities, int location) {
        int answer = 0;
        int l = location;

        Queue<Integer> queue = new LinkedList<>();
        for (int i : priorities) {
            queue.add(i);
        }
        Arrays.sort(priorities);
        int size = priorities.length-1;

        while (!queue.isEmpty()) {
            Integer i = queue.poll();
            if (i == priorities[size - answer]) {
                answer++;
                l--;
                if (l < 0) break;
            } else {
                queue.add(i);
                l--;
                if (l<0) l =queue.size()-1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;

        Printer printer = new Printer();
        System.out.println(printer.solution(priorities, location));
        System.out.println(printer.progressiveSolution(priorities, location));

        priorities = new int[]{1, 1, 9, 1, 1, 1};
        location = 0;
        System.out.println(printer.solution(priorities, location));
        System.out.println(printer.progressiveSolution(priorities, location));
    }
}
