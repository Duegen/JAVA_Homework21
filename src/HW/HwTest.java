package HW;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HwTest {
	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;
    
	@BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }
	
	@AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }
	
	@Test
	void testDisplayAverageMinMaxSalaryIllegalArgs() {
		assertThrows(IllegalArgumentException.class, () -> {
			HwAppl.displayAverageMinMaxSalary(null);
		});
	}
	
	@Test
	void testDisplayAverageMinMaxSalaryEmpty() {
		List<Employee> emptyEmployees = new ArrayList<Employee>(List.of());
		HwAppl.displayAverageMinMaxSalary(emptyEmployees);
		assertEquals("Employee list is empty", outputStream.toString().trim());
	}
	
	@Test
	void testDisplayAverageMinMaxSalaryOneElement() {
		
		List<Employee> employees = new ArrayList<>(List.of(new Employee(3,  "Alice Johnson",  "TechCorp",    2450)));
		String expected = "Average salary = 2450,00\nMinimal salary = 2450\nMaximum salary = 2450\n";
		
		HwAppl.displayAverageMinMaxSalary(employees);
		assertEquals(expected, outputStream.toString());
	}
	
	@Test
	void testDisplayAverageMinMaxSalarySuccess() {
		List<Employee> employees = new ArrayList<>(List.of(
	            new Employee(3,  "Alice Johnson",  "TechCorp",    1000),
	            new Employee(7,  "Bob Martinez",   "Innovasoft",  2000),
	            new Employee(12, "Clara Smith",    "DevHouse",    3000)
	    ));
		String expected = "Average salary = 2000,00\nMinimal salary = 1000\nMaximum salary = 3000\n";
		HwAppl.displayAverageMinMaxSalary(employees);
		assertEquals(expected, outputStream.toString());
	}
	
	@Test
	void testDisplayAverageMinMaxSalarySuccessWithNull() {
		List<Employee> employees = new ArrayList<>(Arrays.asList(
	            new Employee(3,  "Alice Johnson",  "TechCorp",    1000),
	            new Employee(7,  "Bob Martinez",   "Innovasoft",  2000),
	            null,
	            new Employee(12, "Clara Smith",    "DevHouse",    3000)
	    ));
		String expected = "Average salary = 2000,00\nMinimal salary = 1000\nMaximum salary = 3000\n";
		HwAppl.displayAverageMinMaxSalary(employees);
		assertEquals(expected, outputStream.toString());
	}
	
	@Test
	void testDisplayAverageSalaryIllegalArgs() {
		assertThrows(IllegalArgumentException.class, () -> {
			HwAppl.displayAverageSalary(null, "company");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			HwAppl.displayAverageSalary(new ArrayList<Employee>(List.of()), null);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			HwAppl.displayAverageSalary(new ArrayList<Employee>(List.of()), " ");
		});
	}
	
	@Test
	void testDisplayAverageSalaryEmpty() {
		List<Employee> emptyEmployees = new ArrayList<Employee>(List.of());
		HwAppl.displayAverageSalary(emptyEmployees, "company");
		assertEquals("Employees for company 'company' not found", outputStream.toString().trim());
	}
	
	@Test
	void testDisplayAverageSalaryNotFound() {
		List<Employee> employees = new ArrayList<>(List.of(
	            new Employee(3,  "Alice Johnson",  "TechCorp",    1000),
	            new Employee(7,  "Bob Martinez",   "Innovasoft",  2000),
	            new Employee(12, "Clara Smith",    "DevHouse",    3000)
	    ));
		HwAppl.displayAverageSalary(employees, "company");
		assertEquals("Employees for company 'company' not found", outputStream.toString().trim());
	}
	
	@Test
	void testDisplayAverageSalaryOneElement() {
		List<Employee> employees = new ArrayList<>(List.of(
	            new Employee(3,  "Alice Johnson",  "TechCorp",    1000),
	            new Employee(7,  "Bob Martinez",   "Innovasoft",  2000),
	            new Employee(12, "Clara Smith",    "DevHouse",    3000)
	    ));
		HwAppl.displayAverageSalary(employees, "TechCorp");
		assertEquals("Average salary ('TechCorp') = 1000,00", outputStream.toString().trim());
	}
	
	@Test
	void testDisplayAverageSalarySuccess() {
		List<Employee> employees = new ArrayList<>(List.of(
	            new Employee(3,  "Alice Johnson",  "TechCorp",  1000),
	            new Employee(7,  "Bob Martinez",   "TechCorp",  2000),
	            new Employee(12, "Clara Smith",    "TechCorp",  3000)
	    ));
		HwAppl.displayAverageSalary(employees, "TechCorp");
		assertEquals("Average salary ('TechCorp') = 2000,00", outputStream.toString().trim());
	}
	
	@Test
	void testDisplayAverageSalarySuccessWithNull() {
		List<Employee> employees = new ArrayList<>(Arrays.asList(
	            new Employee(3,  "Alice Johnson",  "TechCorp",  1000),
	            new Employee(7,  "Bob Martinez",   "TechCorp",  2000),
	            null,
	            new Employee(12, "Clara Smith",    "TechCorp",  3000)
	    ));
		HwAppl.displayAverageSalary(employees, "TechCorp");
		assertEquals("Average salary ('TechCorp') = 2000,00", outputStream.toString().trim());
	}
		
	@Test
	void testDisplayEmployeesAvgSalaryIllegalArgs() {
		assertThrows(IllegalArgumentException.class, () -> {
			HwAppl.displayEmployeesAvgSalary(null);
		});
	}

	@Test
	void testDisplayEmployeesAvgSalaryEmpty() {
		List<Employee> emptyEmployees = new ArrayList<Employee>(List.of());
		HwAppl.displayEmployeesAvgSalary(emptyEmployees);
		assertEquals("Employee list is empty", outputStream.toString().trim());
	}

	@Test
	void testDisplayEmployeesAvgSalaryOneElement() {
		
		List<Employee> employees = new ArrayList<>(List.of(new Employee(3,  "Alice Johnson",  "TechCorp",    2450)));
		
		HwAppl.displayEmployeesAvgSalary(employees);
		assertEquals("Average salary: 2450,00", outputStream.toString().trim());
	}
	
	@Test
	void testDisplayEmployeesAvgSalarySuccess() {
		List<Employee> employees = new ArrayList<>(List.of(
	            new Employee(3,  "Alice Johnson",  "TechCorp",  1000),
	            new Employee(7,  "Bob Martinez",   "TechCorp",  2000),
	            new Employee(12, "Clara Smith",    "TechCorp",  3000)
	    ));
		String expected = "Average salary: 2000,00\n" + "Employee name: Clara Smith, salary: 3000";
		
		HwAppl.displayEmployeesAvgSalary(employees);
		assertEquals(expected, outputStream.toString().trim());
	}
	
	@Test
	void testDisplayEmployeesAvgSalarySuccessWithNull() {
		List<Employee> employees = new ArrayList<>(Arrays.asList(
	            new Employee(3,  "Alice Johnson",  "TechCorp",  1000),
	            new Employee(7,  "Bob Martinez",   "TechCorp",  2000),
	            null,
	            new Employee(12, "Clara Smith",    "TechCorp",  3000)
	    ));
		String expected = "Average salary: 2000,00\n" + "Employee name: Clara Smith, salary: 3000";
		
		HwAppl.displayEmployeesAvgSalary(employees);
		assertEquals(expected, outputStream.toString().trim());
	}
	
	@Test
	void testdisplayShuffledArrayIllegalArg() {
		assertThrows(IllegalArgumentException.class, () -> {
			HwAppl.displayShuffledArray(null);
		});
	}
	
	@Test
	void testDisplayShuffledArrayEmpty() {
		int[] arrEmpty = {};
		HwAppl.displayShuffledArray(arrEmpty);
		assertEquals("Array is empty", outputStream.toString().trim());
	}
	
	@Test
	void testDisplayShuffledArraySuccess() {
		int[] arr = {1,2,3,4,5};
		HwAppl.displayShuffledArray(arr);
	}
	
	@Test
	void testSportLotoIllegalArgs() {
		assertThrows(IllegalArgumentException.class, () -> {
			HwAppl.sportLoto(5, 1, 10);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			HwAppl.sportLoto(1, 10, -2);
		});
	}
	
	@Test
	void testSportLotoSuccess() {
		HwAppl.sportLoto(1, 49, 7);
	}
}
