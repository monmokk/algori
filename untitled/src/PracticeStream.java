import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PracticeStream {

    public static void print(String str) {
        System.out.println(str+" - "+Thread.currentThread().getName());
    }

    void practiceStr1(){
        List<String> list = Arrays.asList("pp", "mm", "jj");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println); //메서드 참조
        //stream.forEach(name -> System.out.println(name));
    }

    void practiceStrFor(){
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

    void practiceStrM(){
        List<String> nameList = Arrays.asList(
                "예제", "만드는중","호옹", "글쿤"
        );
        Stream<String> stringStream = nameList.stream();
        stringStream.forEach(PracticeStream::print); //s->Sout(s)과 동일
        Stream<String> parallelStream = nameList.parallelStream();
        parallelStream.forEach(PracticeStream::print);
    }


    public static void main(String[] args) {
        PracticeStream practiceStream = new PracticeStream();
        practiceStream.practiceStr1();
        practiceStream.practiceStrFor();
        practiceStream.practiceStrM();
    }
}
