import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Truck {
    static class SubTruck {
        int weight;
        int move;

        public SubTruck(int weight) {
            this.weight = weight;
            this.move = 1;
        }

        public void moving() {
            move++;
        }
    }
    public int progressiveSolution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<SubTruck> waitQ = new LinkedList<>();
        Queue<SubTruck> moveQ = new LinkedList<>();

        for (int t : truck_weights) {
            waitQ.offer(new SubTruck(t));
        }

        int currentWeight = 0;

        while (!waitQ.isEmpty() || !moveQ.isEmpty()) {
            answer++;

            if (moveQ.isEmpty()) {
                SubTruck truck = waitQ.poll();
                currentWeight += truck.weight;
                moveQ.offer(truck);
                continue;
            }

            for (SubTruck truck : moveQ) {
                truck.moving();
            }

            if (Objects.requireNonNull(moveQ.peek()).move > bridge_length) {
                SubTruck truck = moveQ.poll();
                currentWeight -= truck.weight;
            }

            if (!waitQ.isEmpty() && currentWeight + waitQ.peek().weight <= weight) {
                SubTruck truck = waitQ.poll();
                currentWeight += truck.weight;
                moveQ.offer(truck);
            }
        }

        return answer;
    }
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
        System.out.println(truck.progressiveSolution(bridge_length, weight, truck_weights));
    }
}
