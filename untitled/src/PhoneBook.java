import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    public boolean solution(String[] phoneBook) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < phoneBook.length; i++)
            map.put(phoneBook[i], i);
        for (String s : phoneBook)
            for (int j = 0; j < s.length(); j++)
                if (map.containsKey(s.substring(0, j)))
                    return false;

        return true;
    }

    public boolean progressiveSolution(String[] phoneBook) {
        for (int i = 0; i <phoneBook.length; i++) {
            for (int j = i + 1; j < phoneBook.length; j++) {
                if (phoneBook[i].startsWith(phoneBook[j])) { return false; }
                if (phoneBook[j].startsWith(phoneBook[i])) { return false; }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        String[] phone_book = {"119", "97674223", "1195524421"}; //"119", "97674223", "1195524421"   "123","456","789"
        System.out.println(phoneBook.solution(phone_book));
        System.out.println(phoneBook.progressiveSolution(phone_book));

    }
}
