
//"exp_2.3 => Java Programs Using Lambda Expressions and Stream Operations"

import java.util.*;
import java.util.stream.*;
import java.util.Comparator;
import java.util.Optional;

public class Main1 {//Lambda Stream

    static class Employee {
        String name;
        int age;
        double salary;

        Employee(String name, int age, double salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format("Employee{name='%s', age=%d, salary=%.2f}", name, age, salary);
        }
    }

    static class Student {
        String name;
        double marks;

        Student(String name, double marks) {
            this.name = name;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return String.format("Student{name='%s', marks=%.2f}", name, marks);
        }
    }

    static class Product {
        String name;
        double price;
        String category;

        Product(String name, double price, String category) {
            this.name = name;
            this.price = price;
            this.category = category;
        }

        @Override
        public String toString() {
            return String.format("Product{name='%s', price=%.2f, category='%s'}", name, price, category);
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("Alice", 30, 70000),
            new Employee("Bob", 25, 60000),
            new Employee("Charlie", 35, 80000),
            new Employee("David", 28, 55000),
            new Employee("Eve", 40, 95000)
        ));

        System.out.println("Original Employees List:");
        employees.forEach(System.out::println);

        employees.sort((e1, e2) -> e1.name.compareToIgnoreCase(e2.name));
        System.out.println("\nEmployees sorted by name:");
        employees.forEach(System.out::println);

        employees.sort(Comparator.comparingInt(e -> e.age));
        System.out.println("\nEmployees sorted by age:");
        employees.forEach(System.out::println);

        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("\nEmployees sorted by salary (descending):");
        employees.forEach(System.out::println);

        List<Student> students = new ArrayList<>(Arrays.asList(
            new Student("John", 82.5),
            new Student("Jane", 74.0),
            new Student("Mark", 90.0),
            new Student("Sally", 68.5),
            new Student("Tom", 77.0)
        ));

        System.out.println("\nStudents scoring above 75%, sorted by marks:");
        students.stream()
            .filter(s -> s.marks > 75)
            .sorted(Comparator.comparingDouble(s -> s.marks))
            .map(s -> s.name)
            .forEach(System.out::println);

        List<Product> products = new ArrayList<>(Arrays.asList(
            new Product("Laptop", 1200, "Electronics"),
            new Product("Smartphone", 800, "Electronics"),
            new Product("T-Shirt", 25, "Clothing"),
            new Product("Jeans", 50, "Clothing"),
            new Product("Coffee Maker", 100, "Home Appliances"),
            new Product("Blender", 150, "Home Appliances"),
            new Product("Headphones", 200, "Electronics"),
            new Product("Jacket", 120, "Clothing")
        ));

        Map<String, List<Product>> productsByCategory = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));

        System.out.println("\nProducts grouped by category:");
        productsByCategory.forEach((category, prods) -> {
            System.out.println(category + ":");
            prods.forEach(p -> System.out.println("  " + p));
        });

        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
            .collect(Collectors.groupingBy(
                p -> p.category,
                Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
            ));

        System.out.println("\nMost expensive product in each category:");
        mostExpensiveByCategory.forEach((category, productOpt) -> {
            productOpt.ifPresent(product -> System.out.println(category + ": " + product));
        });

        double avgPrice = products.stream()
            .collect(Collectors.averagingDouble(p -> p.price));

        System.out.printf("\nAverage price of all products: %.2f\n", avgPrice);
    }

}
