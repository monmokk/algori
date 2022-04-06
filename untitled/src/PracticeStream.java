import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

//        double avg = students.stream()
//                //학생 객체 숫자로 매핑
//                .mapToInt(Student::getScore)
//                //최종처리
//                .average()
//                .getAsDouble();

        OptionalDouble avg = students.stream()
                //학생 객체 숫자로 매핑
                .mapToInt(Student::getScore)
                //최종처리
                .average();
        if (avg.isPresent()) System.out.println(avg.getAsDouble());
        else System.out.println("으음");

        System.out.println(avg);

        Stream<Student> stream = students.stream();
        stream.forEach(student -> System.out.println(student.getName()));
    }

    public int sum;

    void returnNum() {
       // int sum = 0;
        IntStream stream = IntStream.rangeClosed(1, 100);
        stream.forEach(value -> sum += value);

        System.out.println(sum);
    }

    void filtering() {
        List<String> names = Arrays.asList(
                "정꾸꾸", "김꾸꾸", "퐁꾸꾸", "명꾸꾸", "정퐁"
        );
        names.stream()
                .distinct()
                .forEach(System.out::println);

        names.stream()
                .filter(n -> n.startsWith("정"))
                .forEach(System.out::println);
    }

    void doFlatMapping() {
        List<String> list = Arrays.asList(
                "정포동 퐁포포포퐁", "스페이스바 구분 중"
        );

        list.stream()
                .flatMap(data -> Arrays.stream(data.split(" ")))
                .forEach(System.out::println);

        List<String> list1 = Arrays.asList("10, 20, 30", "40, 50, 60");
        list1.stream()
                .flatMapToInt(data -> {
                    String[] strings = data.split(",");
                    int[] ints = new int[strings.length];
                    for (int i = 0; i < strings.length; i++) {
                        ints[i] = Integer.parseInt(strings[i].trim());
                    }
                    return Arrays.stream(ints);
                })
                .forEach(System.out::println);
    }

    void doMapping() {
        List<Student> students = Arrays.asList(
                new Student("정꾸꾸", 100),
                new Student("정퐁퐁", 20),
                new Student("강몽", 100)
        );

        students.stream()
                .mapToInt(Student::getScore)
                .forEach(System.out::println);
    }

    void doLooping() {
        int[] ints = {1, 2, 3, 4, 5};

        System.out.println("peek을 마지막에 호출한 경우");
//        Arrays.stream(ints)
//                .filter(a -> a % 2 == 0)
//                .peek(System.out::println); // 처리 되지 않음. peek은 중간처리 메소드

        System.out.println("최종 처리 메소드를 마지막에 호출한 경우");
        int total = Arrays.stream(ints)
                .filter(a -> a % 2 == 0)
                .peek(System.out::println)
                .sum();
        System.out.println("총합: " + total); // 뒤에 sum()등 다른 메서드가 와야 처리 가능

        System.out.println("forEach 마지막에 호출");
        Arrays.stream(ints)
                .filter(a -> a%2 == 0)
                .forEach(System.out::println); //forEach()는 최종메서드
    }

    void doCustomize() {
        //reduce 커스텀 집계
        List<Student> students = Arrays.asList(
                new Student("정꾸꾸", 90),
                new Student("퐁꾸꾸", 89),
                new Student("몽꾸꾸", 99)
        );

        int sum1 = students.stream()
                .mapToInt(Student::getScore)
                .sum();

        System.out.println("sum1 : " + sum1);

        int sum2 = students.stream()
                .map(Student::getScore)
                .reduce(Integer::sum)
                .get();
        System.out.println("sum2: " + sum2 );
    }

    void collectFilter() {
        List<Student> studentList = Arrays.asList(
                new Student("jjw", 10, Student.Sex.MALE),
                new Student("mon", 9, Student.Sex.FEMALE),
                new Student("pong", 7, Student.Sex.FEMALE)
        );

        List<Student> maleList = studentList.stream()
                .filter(s -> s.getSex() == Student.Sex.MALE)
                .collect(Collectors.toList());
        maleList
                .forEach(s -> System.out.println(s.getName()));

        System.out.println();

        Set<Student> femaleSet = studentList.stream()
                .filter(s -> s.getSex() == Student.Sex.FEMALE)
                .collect(Collectors.toCollection(HashSet::new));
        femaleSet
                .forEach(s -> System.out.println(s.getName()));

        Map<Student.Sex, List<Student>> mapBySex = studentList.stream()
                .collect(Collectors.groupingBy(Student::getSex));
//        mapBySex.get(Student.Sex.MALE).stream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        PracticeStream practiceStream = new PracticeStream();
//        practiceStream.practiceStr1();
//        practiceStream.practiceStrFor();
//        practiceStream.practiceStrM();
//        practiceStream.handlingStream();
//        practiceStream.returnNum();
//        practiceStream.filtering();
        //practiceStream.doFlatMapping();
        //practiceStream.doMapping();
//        practiceStream.doLooping();
//        practiceStream.doCustomize();
        practiceStream.collectFilter();
    }
}
