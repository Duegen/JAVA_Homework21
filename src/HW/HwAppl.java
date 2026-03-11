package HW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HwAppl {

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>(List.of(
	            new Employee(3,  "Alice Johnson",  "TechCorp",    2450),
	            new Employee(7,  "Bob Martinez",   "Innovasoft",  3800),
	            new Employee(12, "Clara Smith",    "DevHouse",    1750),
	            new Employee(19, "David Lee",      "TechCorp",    2100),
	            new Employee(24, "Eva Brown",      "Nexagen",     3200),
	            new Employee(31, "Alice Johnson",  "Innovasoft",  1500),
	            new Employee(38, "Frank Wilson",   "DevHouse",    2900),
	            new Employee(43, "Grace Taylor",   "TechCorp",    3650),
	            new Employee(51, "Bob Martinez",   "Nexagen",     1200),
	            new Employee(56, "Henry Davis",    "Innovasoft",  2750),
	            new Employee(62, "Isla Anderson",  "TechCorp",    3100),
	            new Employee(67, "Clara Smith",    "DevHouse",    1950),
	            new Employee(73, "Jack Thompson",  "Nexagen",     2300),
	            new Employee(79, "Karen White",    "TechCorp",    3900),
	            new Employee(84, "David Lee",      "Innovasoft",  1100),
	            new Employee(88, "Liam Harris",    "DevHouse",    2600),
	            new Employee(91, "Eva Brown",      "Nexagen",     3400),
	            new Employee(94, "Mia Robinson",   "TechCorp",    1800),
	            new Employee(97, "Frank Wilson",   "Innovasoft",  2200),
	            new Employee(99, "Grace Taylor",   "DevHouse",    3050)
	        ));
		
		System.out.println("=======[Average Min Max salary]==========================");
		displayAverageMinMaxSalary(employees);
		System.out.println("\n=======[Average salary for company]======================");
		displayAverageSalary(employees, "Nexagen");
		System.out.println("\n=======[Employees with salary greater then average]======");
		displayEmployeesAvgSalary(employees);
		System.out.println("\n=======[Array shaffling]=================================");
		int[] sortedArr = new Random().ints(20, 1, 100).distinct().limit(20).sorted().toArray();
		displayShuffledArray(sortedArr);
		System.out.println("\n=======[Sport loto]======================================");
		sportLoto(1, 49, 7);
		
	}
	
	public static void displayAverageMinMaxSalary(List<Employee> employees) {
		if(Objects.isNull(employees))
			throw new IllegalArgumentException("Employee array is null");
		IntSummaryStatistics stats = employees.stream()
        	.filter(Objects::nonNull)
        	.map(emp -> emp.salary)
        	.mapToInt(n -> n)
        	.summaryStatistics();
		if(stats.getCount() > 0)
			System.out.printf("Average salary = %.2f\nMinimal salary = %d\nMaximum salary = %d\n",
				stats.getAverage(), stats.getMin(), stats.getMax());
		else
			System.out.println("Employee list is empty");
	}
	
	public static void displayAverageSalary(List<Employee> employees,String company) {
		if(Objects.isNull(employees))
			throw new IllegalArgumentException("Employee array is null");
		if(Objects.isNull(company) || company.isBlank())
			throw new IllegalArgumentException("Illegal company name");
		
		OptionalDouble salaryEverage = employees.stream()
	        	.filter(Objects::nonNull)
	        	.filter(emp -> company.equals(emp.company))
	        	.map(emp -> emp.salary)
	        	.mapToInt(n -> n)
	        	.average();
		if(salaryEverage.isPresent())
			System.out.printf("Average salary ('%s') = %.2f\n", company, salaryEverage.getAsDouble());
		else
			System.out.printf("Employees for company '%s' not found", company);
	}
	
	public static void displayEmployeesAvgSalary(List<Employee> employees){
		if(Objects.isNull(employees))
			throw new IllegalArgumentException("Employee list is null");
		
		OptionalDouble salaryEverage = employees.stream()
	        	.filter(Objects::nonNull)
	        	.map(emp -> emp.salary)
	        	.mapToInt(n -> n)
	        	.average();
		
		if(salaryEverage.isPresent()) {
			System.out.printf("Average salary: %.2f\n", salaryEverage.getAsDouble());
			employees.stream()
				.filter(Objects::nonNull)
				.filter(emp -> emp.salary > salaryEverage.getAsDouble())
				.map(emp -> new SubEmployee(emp.name, emp.salary))
				.forEach(emp -> System.out.printf("Employee name: %s, salary: %d\n", emp.subName,emp.subSalary));
		}
		else
			System.out.println("Employee list is empty");
	}
	
	public static void displayShuffledArray(int[] ar) {
		if(Objects.isNull(ar))
			throw new IllegalArgumentException("Array is null");
		if(ar.length == 0) {
			System.out.println("Array is empty");
			return;
		}
		System.out.printf("Original array: ");
		Arrays.stream(ar).forEach(elem -> System.out.print(elem + " "));
		System.out.println();
		Random rand = new Random();
		System.out.print("Shaffled array: "); 
		IntStream.generate(() -> new Random().nextInt(0, ar.length))
        	.distinct()
        	.limit(ar.length)
        	.forEach(index -> System.out.print(ar[index] + " "));
		System.out.println();
	}
	
	public static void sportLoto(int min, int max, int numberDigits) {
		if(max < min || numberDigits < 1 || numberDigits > IntStream.rangeClosed(min, max).count()) 
			throw new IllegalArgumentException("Illegal args");
		IntStream.generate(() -> new Random().nextInt(min, max + 1))
        .distinct()
        .limit(numberDigits)
        .forEach(number -> System.out.print(number + " "));
		System.out.println();
	}
}
