package model.services;

import java.util.List;
import java.util.function.Predicate;

import entities.Employees;

public class EmployeeService {

	public double filteredSum(List<Employees> list, Predicate<Employees> criterion) {
		double sum = 0.0;
		for (Employees nickname : list) {
			if (criterion.test(nickname)) {
				sum += nickname.getSalary();
			}
		}

		return sum;

	}

}
