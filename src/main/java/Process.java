import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Process {

    public static void main(String[] args) {

        // need to find highest paid employee per department
        var employees = new ArrayList<Employee>();
        employees.add(new Employee("IT", "John", 11000));
        employees.add(new Employee("IT", "Jane", 6000));
        employees.add(new Employee("IT", "Radhe", 600400));
        employees.add(new Employee("HR", "Jack", 150000));
        employees.add(new Employee("HR", "JackShyam", 4500));
        employees.add(new Employee("HR", "Jill", 2500));
        employees.add(new Employee("IT", "Jim", 3000));

        var collect = employees.stream()
                .collect(Collectors.groupingBy(x -> x.department, Collector.of(
                        () -> new ArrayList<Employee>(),
                        (accumulator, employee) -> {
                            accumulator.add(employee);
                            accumulator.sort((x, y) -> {
                                if (x.salary > y.salary)
                                    return -1;
                                else if (x.salary.equals(y.salary))
                                    return 0;
                                else return 1;
                            });
                        },
                        (a, b) -> {
                            return a;
                        },
                        (a) -> a.stream().limit(2).toList()

                )));

        for (var entry : collect.entrySet()) {

            System.out.println("______");
            System.out.println("key:" + entry.getKey());
            System.out.println("value: " + entry.getValue());

        }

    }
}