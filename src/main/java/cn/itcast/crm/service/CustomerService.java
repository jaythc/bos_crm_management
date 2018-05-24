package cn.itcast.crm.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import cn.itcast.crm.domain.Customer;

public interface CustomerService {
	
	//  根据客户地址 , 获取定区编号
	@Path("/customer/findFixedAreaIdByAddress")
	@GET
	@Produces({"application/xml","application/json"})
	public String findFixedAreaIdByAddress(@QueryParam("address") String address);
	
	
	// 前台用户登陆的接口方法
	@Path("/customer/login")
	@GET
	@Produces({"application/xml","application/json"})
	public Customer login(@QueryParam("telephone") String telephone,
			@QueryParam("password") String password);
	

	//保存客户信息
	@Path("/savecustomer")
	@POST
	@Consumes({"application/xml","application/json"})
	public void regist(Customer customer);
	
	//查询所有未关联客户列表
	@Path("/noassociatedcustomers")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Customer> findNoAssociatedCustomers();
	
	//查询已经关联定区客户列表
	// http://localhost:9011/crm_management/services/customerService/hasassociatedcustomers/dq001
	@Path("/hasassociatedcustomers/{fixedareaid}")
	@GET
	@Produces({"application/xml","application/json"})
	public List<Customer> findHasAssociatedCustomers(
			@PathParam("fixedareaid") String fixedAreaId);
	
	
	//http://localhost:9011/crm_management/services/customerService/hasassociatedcustomers?fixedareaid=dq001
	
	//把客户关联到定区上, 把所有客户id拼成字符串1,2,3
	@Path("/associatecustomertofixedarea")
	@PUT
	public void associateCustomerToFixedArea(
			@QueryParam("customerIdStr") String customerIdStr,
			@QueryParam("fixedAreaId") String fixedAreaId);
	
	/**
	 * 修改用户的邮箱状态, put请求方式, 用postman发送请求
	http://localhost:9011/crm_management/services/customerService/customer/updatetype/1307125
	 * @param telephone
	 */
	//修改用户的邮件激活状态
	@Path("/customer/updatetype/{telephone}")
	@PUT
	@Consumes({"application/xml","application/json"})
	public void updateType(@PathParam("telephone") String telepo);
	
	//http://localhost:9011/crm_management/services/customerService/customer/telephone/13071
	//通过电话号码, 查询客户的方法
	@Path("/customer/telephone/{telephone}")
	@GET
	@Produces({"application/xml","application/json"})
	public Customer findByTelephone(
			@PathParam("telephone") String telepo);
	
}
