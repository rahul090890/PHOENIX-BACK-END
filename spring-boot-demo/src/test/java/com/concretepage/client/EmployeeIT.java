package com.concretepage.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.concretepage.entity.Employee;
import com.concretepage.entity.Project;
import com.concretepage.entity.Role;

@SpringBootApplication
public class EmployeeIT {

	
	public void testEmployeeList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/hr/employee/all";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Employee[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Employee[].class);
		Employee[] employees = responseEntity.getBody();
		for(Employee emp : employees) {
			System.out.println(emp.toString());
		}
	}
	
	public void testRoleTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/hr/role/all";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Role[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Role[].class);
		Role[] employees = responseEntity.getBody();
		for(Role emp : employees) {
			System.out.println(emp.toString());
		}
	}
	
	public void testProjectTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/hr/role/all";
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Project[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Project[].class);
		Project[] employees = responseEntity.getBody();
		for(Project emp : employees) {
			System.out.println(emp.toString());
		}
	}
	
	public static void main(String arg[]) {
		EmployeeIT IT = new EmployeeIT();
		IT.testProjectTest();
	}
	
	
}
