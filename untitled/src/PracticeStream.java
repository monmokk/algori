import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PracticeStream {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("pp", "mm", "jj");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println); //메서드 참조
        //stream.forEach(name -> System.out.println(name));

        List<Student> list1 = Arrays.asList(
            new Student("퐁", 0),
            new Student("찌오니", 100)
        );

        Stream<Student> stream1 = list1.stream();
        stream1.forEach(student -> {
            String name = student.getName();
            int score = student.getScore();
            System.out.println(name + "-" + score);
        });
    }
}
