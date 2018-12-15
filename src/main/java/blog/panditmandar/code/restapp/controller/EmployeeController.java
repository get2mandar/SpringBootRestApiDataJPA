package blog.panditmandar.code.restapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import blog.panditmandar.code.restap.exception.HTTPNotFoundException;
import blog.panditmandar.code.restapp.entity.Employee;
import blog.panditmandar.code.restapp.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepository;

	/**
	 * Get all Employee's list.
	 *
	 * @return the list
	 */
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return empRepository.findAll();
	}

	/**
	 * Gets Employees by id.
	 *
	 * @param empId
	 *            the employee id
	 * @return the employee by id
	 * @throws HTTPNotFoundException
	 *             the HTTP Resource not found exception
	 */
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeesById(@PathVariable(value = "id") Long empId)
			throws HTTPNotFoundException {
		Employee employee = empRepository.findById(empId)
				.orElseThrow(() -> new HTTPNotFoundException("Employee not found for :: " + empId));
		return ResponseEntity.ok().body(employee);
	}

	/**
	 * Create New Employee.
	 *
	 * @param employee
	 *            the employee for new creation
	 * @return the employee with id created
	 */
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		System.out.println(employee);
		return empRepository.save(employee);
	}

	/**
	 * Update Employee Response Entity.
	 *
	 * @param empId
	 *            the Employee id
	 * @param empDetails
	 *            the employee details to update
	 * @return the Employee Response Entity
	 * @throws HTTPNotFoundException
	 *             the HTTP Resource not found exception
	 */
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long empId,
			@Valid @RequestBody Employee empDetails) throws HTTPNotFoundException {
		Employee employee = empRepository.findById(empId)
				.orElseThrow(() -> new HTTPNotFoundException("Employee not found for :: " + empId));
		employee.setEmail(empDetails.getEmail());
		employee.setLastName(empDetails.getLastName());
		employee.setFirstName(empDetails.getFirstName());
		final Employee updatedEmployee = empRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	/**
	 * Delete Employee map.
	 *
	 * @param empId
	 *            the employee id
	 * @return the map
	 * @throws HTTPNotFoundException
	 *             the exception
	 */
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long empId) throws Exception {
		Employee employee = empRepository.findById(empId)
				.orElseThrow(() -> new HTTPNotFoundException("Employee not found for ID :: " + empId));
		empRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
