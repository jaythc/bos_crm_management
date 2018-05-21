package cn.itcast.crm.service;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import cn.itcast.crm.domain.Customer;

public class ClientTest {

	public static void main(String[] args) {

		Customer c = new Customer();
		c.setUsername("test");
		c.setTelephone("4564");
		WebClient.create("http://localhost:9011/crm_management/services/customerService/savecustomer")
				.type(MediaType.APPLICATION_JSON).post(c);

	}

}
