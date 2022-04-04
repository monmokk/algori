import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayReverse {
    void reverseArray(int[] arr) {
        int[] reverseArr = new int[arr.length];

        for (int i = arr.length -1, j = 0; i >= 0; i--, j++) {
            reverseArr[j] = arr[i];
        }
        System.out.println("arr: " + Arrays.toString(arr));
        System.out.println("reverseArr:" + Arrays.toString(reverseArr));

    }

    void reverseCollection(int[] arr) {
        Integer[] ia = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        List<Integer> list = Arrays.asList(ia);
        Collections.reverse(list);

        System.out.println("arr: " + Arrays.toString(arr));
        System.out.println("reverseArr: " + Arrays.toString(list.toArray()));
    }

    public static void main(String[] args) {
        ArrayReverse arrayReverse = new ArrayReverse();
        int[] arr = {1, 2, 3, 4, 5};
        arrayReverse.reverseArray(arr);
        arrayReverse.reverseCollection(arr);
    }

}
