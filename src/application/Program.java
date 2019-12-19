package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employees;
import model.services.EmployeeService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.println("The file name is employee and is located in the temp folder");
		System.out.print("Inform full file path: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Employees> list = new ArrayList<Employees>();

			String line = br.readLine();
			while (line != null) {
				String[] fiels = line.split(",");
				list.add(new Employees(fiels[0], fiels[1], Double.parseDouble(fiels[2])));
				line = br.readLine();
			}

			System.out.println("Full list of employees");
			for (Employees nickname : list) {
				System.out.println(nickname);
			}
			System.out.println();

			System.out.print("Enter Enter a reference salary amount: ");
			double salary = sc.nextDouble();

			System.out.println("Email of people whose salary is more than " + String.format("U$ %.2f",salary));

			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

			List<String> email = list.stream()
					.filter(e -> e.getSalary() > salary)
					.map(e -> e.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());

			email.forEach(System.out::println);

			EmployeeService es = new EmployeeService();

			double sum = es.filteredSum(list, e -> e.getName().charAt(0) == 'M');

			System.out.println();

			System.out.println("Summation of salary of people whose name starts with 'M': " + String.format("U$ %.2f",sum));

		}

		catch (IOException error) {
			System.out.println("Error" + error.getMessage());

		}

		sc.close();

	}
}
