package com.concretepage.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.concretepage.entity.Customer;

public class CustomerIT {

	public  static void main(String arg[]) {
		CustomerIT IT = new CustomerIT();
		IT.testFindByName();
	}
	public void testFindByName() {
		HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/hr/customer/find/{customerName}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<Customer> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Customer.class, "Zodiac");
        Customer customer = responseEntity.getBody();
       System.out.println(customer.toString());
	}
}
