import java.util.LinkedList;
import java.util.Queue;

public class Truck {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        int totalWeight = 0;
        int time = 0;

        for (int i : truck_weights) {
            while (true) {
                if (queue.isEmpty()) { // 다리에 트럭 없
                    queue.offer(i);
                    totalWeight += i;
                    time++;
                    break;
                } else if (queue.size() == bridge_length) { //다리에 트럭 들어갈 자리가 없음
                    totalWeight -= queue.poll();
                } else { // 다리에 자리 있음.
                    if (weight >= totalWeight + i) {
                        queue.offer(i);
                        totalWeight += i;
                        time++;
                        break;
                    } else { // 무게초과
                        queue.offer(0);
                        time++;
                    }
                }
            }
        }
        answer += time + bridge_length;

        return answer;
    }

    public static void main(String[] args) {
        Truck truck = new Truck();
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7, 4, 5, 6};
        System.out.println(truck.solution(bridge_length, weight, truck_weights));
    }
}
