package cn.itcast.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.crm.dao.CustomerRepository;
import cn.itcast.crm.domain.Customer;
import cn.itcast.crm.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> findNoAssociatedCustomers() {
		return customerRepository.findByFixedAreaIdIsNull();
	}
	
	@Override
	public List<Customer> findHasAssociatedCustomers(String fixedAreaId) {
		return customerRepository.findByFixedAreaId(fixedAreaId);
	}

	//关联客户的方法
	@Override
	public void associateCustomerToFixedArea(String customerIdStr, String fixedAreaId) {
		//关联客户之前, 先解除关联
		customerRepository.clearFixedAreaId(fixedAreaId);
		//判断客户id是否为空
		if (StringUtils.isBlank(customerIdStr)) {
			return;
		}
		//切割客户的id 字符串
		String[] customerIdArray = customerIdStr.split(",");
		for (String idStr : customerIdArray) {
			Integer id = Integer.parseInt(idStr);
			customerRepository.updateFixedAreaId(fixedAreaId,id);
		}
	}

	//保存客户
	@Override
	public void regist(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public void updateType(String telepo) {
		customerRepository.updateType(telepo);
	}

	@Override
	public Customer findByTelephone(String telepo) {
		return customerRepository.findByTelephone(telepo);
	}

	@Override
	public Customer login(String telephone, String password) {
		return customerRepository.findByTelephoneAndPassword(telephone,password);
	}

	@Override
	public String findFixedAreaIdByAddress(String address) {
		return customerRepository.findFixedAreaIdByAddress(address);
	}

	
}
