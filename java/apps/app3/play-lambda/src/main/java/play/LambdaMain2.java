package play;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LambdaMain2 {

    public static void main(String[] args) {
        // List型でLambda
        List<String> list = new ArrayList<>();
        list.add("David");
        list.add("Mark");
        list.add("George");
        list.forEach(string -> System.out.println(string));

        // 配列でLambda
        String[] strArray = {"Ben", "John", "Sara", "Yoshio"};
        Arrays.stream(strArray).forEach(s -> System.out.println(s));

        // Map型でLambda
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Duan");
        map.put(2, "Michael");
        map.put(3, "Bob");
        map.forEach((k, v) -> System.out.println("key:" + k + " value:" + v));
        

        }
}
