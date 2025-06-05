
package MayBatchJava.Jun5A;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Student {
    String name;
    String gender;



    public Student(String name,String gender) {
        this.name=name;
        this.gender=gender;
    }
}

public class StudentPrefix {
    public static void main(String[] args) {
        List<Student> students=Arrays.asList(
            new Student("Alice","Female"),
            new Student("Bob","Male"),
            new Student("Carol","Female"),
            new Student("David","Male")
        );

        List<String> prefixedNames=students.stream()
            .map(s -> (s.gender.equalsIgnoreCase("Male") ? "Mr. " : "Ms. ") + s.name)
            .collect(Collectors.toList());
        prefixedNames.forEach(System.out::println);
    }
}
