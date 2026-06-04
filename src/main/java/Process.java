import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Process {

    public static void main(String[] args) {

        // need to find top 2 highest paid employee per department
        var employees = new ArrayList<Employee>();
        employees.add(new Employee("IT", "John", 11000));
        employees.add(new Employee("IT", "Jane", 6000));
        employees.add(new Employee("IT", "Radhe", 600400));
        employees.add(new Employee("HR", "Jack", 150000));
        employees.add(new Employee("HR", "JackShyam", 4500));
        employees.add(new Employee("HR", "Jill", 2500));
        employees.add(new Employee("IT", "Jim", 3000));

        // approach 1
        approachChange(1);
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

        printData(collect);

        //approach 2

        approachChange(2);

        var groupedByDepartment = employees
                .stream()
                .collect(Collectors.groupingBy(x -> x.department));

        var finalMap = new HashMap<String, List<Employee>>();
        groupedByDepartment.forEach((key, value) -> {
            var sortedEmployee = value
                    .stream()
                    .sorted((b, a) -> Integer.compare(a.salary, b.salary))
                    .limit(2)
                    .toList();

            finalMap.put(key, sortedEmployee);

        });

        printData(finalMap);

        approachChange(3);

        var finalMap3 = new HashMap<String, List<Employee>>();

        groupedByDepartment.forEach((key, value) -> {

            var queue = new PriorityQueue<Employee>(Comparator.comparing(a -> a.salary));

            value.forEach(
                    emp -> {
                        queue.add(emp);
                        if (queue.size() > 2) {
                            queue.poll();
                        }

                    }

            );

            finalMap3.put(key, queue.stream().toList());

        });
        printData(finalMap3);

    }

    private static void approachChange(int approachNumber) {

        int counter = 10;

        System.out.println("=".repeat(counter) + "Approach " + approachNumber + "=".repeat(counter));
    }

    private static void printData(Map<String, List<Employee>> collect) {

        System.out.println("_".repeat(30));

        for (var entry : collect.entrySet()) {

            System.out.println("______");
            System.out.println("key:" + entry.getKey());
            System.out.println("value: " + entry.getValue());

        }
        System.out.println("_".repeat(30));
    }
}