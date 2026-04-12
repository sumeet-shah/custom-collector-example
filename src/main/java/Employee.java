class Employee {
    String department;
    String name;
    Integer salary;

    public Employee(String hr, String jack, Integer i) {
        this.department = hr;
        this.name = jack;
        this.salary = i;
    }

    @Override
    public String toString() {
        return "Employee{" +

                "name='" + name + '\'' +

                '}';
    }
}