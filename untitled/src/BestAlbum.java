import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BestAlbum {
    public static class Music implements Comparable<Music> {
        private final int played;
        private final int id;
        private final String genre;

        public Music(String genre, int played, int id) {
            this.played = played;
            this.id = id;
            this.genre = genre;
        }

        @Override
        public int compareTo(Music o) {
            if (this.played == o.played) return this.id - o.id;
            return o.played - this.played;
        }

        public String getGenre() {return genre;}
    }

    public int[] progressiveSolution(String[] genres, int[] plays) {
        return IntStream.range(0, genres.length)
                .mapToObj(i -> new Music(genres[i], plays[i], i))
                .collect(Collectors.groupingBy(Music::getGenre))
                .entrySet().stream()
                .sorted((a, b) -> sum(b.getValue()) - sum(a.getValue()))
                .flatMap(x->x.getValue().stream().sorted().limit(2))
                .mapToInt(x->x.id).toArray();
    }

    private int sum(List<Music> val) {
        int answer = 0;
        for (Music music : val) {
            answer += music.played;
        }
        return answer;
    }

    public int[] solution(String[] genres, int[] plays) {
        int[] answer;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }

        //key -> genre
        ArrayList<String> genre = new ArrayList<>(map.keySet());
//        ArrayList<String> genre = new ArrayList<>();
//        genre.addAll(map.keySet());
        genre.sort((o1, o2)->map.get(o2)-map.get(o1)); //key값에 해당하는 값

        ArrayList<Integer> list = new ArrayList<>();
        for (String g : genre) {
            //재생횟수가 가장 큰 인덱스
            int max = 0;
            int firstIdx = -1;
            for (int j = 0; j < genres.length; j++) {
                if (g.equals(genres[j]) && max < plays[j]) {
                    max = plays[j];
                    firstIdx = j;
                }
            }

            //재생횟수가 두번째로 큰 인덱스
            max = 0;
            int secondIdx = -1;
            for (int j = 0; j < genres.length; j++) {
                if (g.equals(genres[j]) && max < plays[j] && j != firstIdx) {
                    max = plays[j];
                    secondIdx = j;
                }
            }
            list.add(firstIdx);
            if (secondIdx >= 0) list.add(secondIdx);
        }
        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }


    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        BestAlbum bestAlbum = new BestAlbum();
        String answer = Arrays.toString(bestAlbum.solution(genres, plays));
        System.out.println(answer);

        String answer1 = Arrays.toString(bestAlbum.progressiveSolution(genres, plays));
        System.out.println(answer1);
    }
}
