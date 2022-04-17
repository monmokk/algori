import java.util.HashSet;
import java.util.Set;

public class ExpressN {
    private int n;
    private int target;
    private int answer = Integer.MAX_VALUE;
    public int solution(int N, int number) {
        n = N;
        target = number;
        dfs(0, 0);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private void dfs(int count, int prev) {
        if (count > 8) {
            answer = -1;
            return;
        }

        if (prev == target) {
            answer = Math.min(answer, count);
            return;
        }

        int tmp = n;
        for (int i = 0; i < 8 - count; i++) {
            int newCount = count + i + 1;
            dfs(newCount, prev + tmp);
            dfs(newCount, prev - tmp);
            dfs(newCount, prev / tmp);
            dfs(newCount, prev * tmp);

            tmp = tmp * 10 + n;
        }
    }

    // TODO: 2022/04/17
    public int dpSolution(int N, int number) {
        int answer = -1;
        // ****** 확인되지 않은 대입. java.util.Set[]을 java.util.Set<java.lang.Integer>[]에...? 해결 방법은?
        Set<Integer>[] sets = new Set[9];
        int t = N;
        for (int i = 1; i < 9 ; i++) {
            sets[i] = new HashSet<>();
            sets[i].add(t);
            t = t * 10 + N;
        }
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < i; j++) {
                for (Integer a : sets[j]) {
                    for (Integer b : sets[i - j]) {
                        sets[i].add(a + b);
                        sets[i].add(a - b);
                        sets[i].add(b - a);
                        sets[i].add(a * b);
                        if (b != 0) {
                            sets[i].add(a / b);
                        }
                        if (a != 0) {
                            sets[i].add(b / a);
                        }
                    }
                }
            }
        }
        for (int i = 1; i <9 ; i++) {
            if (sets[i].contains(number)) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        ExpressN expressN = new ExpressN();
        int n = 5;
        int number = 12;

        System.out.println(expressN.solution(n, number));
        System.out.println(expressN.dpSolution(n,number));

        n = 2;
        number = 11;

        System.out.println(expressN.solution(n, number));
        System.out.println(expressN.dpSolution(n,number));
    }
}

