import java.util.List;
import java.util.stream.Collectors;

public class StringCollectionProcess {
    public static void main(String[] args) {
        var data = List.of("sumeet", "shah", "ok");

        var output = data.stream()
                .collect(Collectors.joining(",","[","]"));

        System.out.println("result: ");
        System.out.println(output);


    }
}