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

    void handlingStream() {
        List<Student> students = Arrays.asList(
                new Student("포동구리", 1),
                new Student("포옹", 100),
                new Student("꾸꾸", 100)
        );

        double avg = students.stream()
                //학생 객체 숫자로 매핑
                .mapToInt(Student::getScore)
                //최종처리
                .average()
                .getAsDouble();
        System.out.println(avg);

        Stream<Student> stream = students.stream();
        stream.forEach(student -> System.out.println(student.getName()));
    }


    public static void main(String[] args) {
        PracticeStream practiceStream = new PracticeStream();
        practiceStream.practiceStr1();
        practiceStream.practiceStrFor();
        practiceStream.practiceStrM();
        practiceStream.handlingStream();
    }
}
