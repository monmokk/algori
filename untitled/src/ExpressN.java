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

    public static void main(String[] args) {
        ExpressN expressN = new ExpressN();
        int n = 5;
        int number = 12;

        System.out.println(expressN.solution(n, number));
        n = 2;
        number = 11;
        System.out.println(expressN.solution(n, number));
    }
}

