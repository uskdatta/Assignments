package MayBatchJava.Jun5A;



import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name;

    public Employee(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}

class Department {
    List<Employee> employees;

    public Department(List<Employee> employees) {
        this.employees=employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

public class EDP {
    public static void main(String[] args) {
        List<Department> departments=Arrays.asList(
                new Department(Arrays.asList(new Employee("Alice"), new Employee("Andrew"),new Employee("Bob"))),
                new Department(Arrays.asList(new Employee("Brenda"),new Employee("Charlie"), new Employee("Catherine"))),
                new Department(Arrays.asList(new Employee("David"), new Employee("Daniel"),new Employee("Eve")))
        );

        List<Employee> allEmployees=departments.stream()
                .flatMap(dept -> dept.getEmployees().stream())
                .collect(Collectors.toList());

        Map<Character,List<String>> groupedByInitial=allEmployees.stream()
                .map(Employee::getName)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.groupingBy(
                        name -> name.charAt(0),
                        Collectors.mapping(String::toString,
                                Collectors.collectingAndThen(Collectors.toList(),list -> {
                                    Collections.sort(list);
                                    return list;
                                }))
                ));

        groupedByInitial.forEach((k,v) -> System.out.println(k+": "+v));
        Collections.shuffle(allEmployees);
        List<List<Employee>> teams=new ArrayList<>();



        for (int i=0;i <5; i++) {
            teams.add(allEmployees.subList(i*2,Math.min((i+1)*2,allEmployees.size())));
        }


        List<List<Employee>> division1=teams.subList(0,2);
        List<List<Employee>> division2=teams.subList(2,4);
        List<List<Employee>> division3=teams.subList(4,5);
        System.out.println("\nDivisions:");
        System.out.println("Division 1: "+division1);
        System.out.println("Division 2: "+division2);
        System.out.println("Division 3: "+division3);
    }
}
