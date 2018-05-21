package cn.itcast.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.itcast.crm.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	//查询未关联定区的客户
	public List<Customer> findByFixedAreaIdIsNull();
	//查询已经关联的客户
	public List<Customer> findByFixedAreaId(String fixedAreaId);

	//关联客户的方法  自定义sql语句, 类似于hql
	@Query("update Customer set fixedAreaId=? where id = ? ")
	@Modifying
	public void updateFixedAreaId(String fixedAreaId, Integer id);
	
	//关联定区之前 , 先取消关联
	@Query("update Customer set fixedAreaId=null where fixedAreaId=?")
	@Modifying
	public void clearFixedAreaId(String fixedAreaId);
	// 不需要写sql 语句,  底层有查询的方法
	public Customer findByTelephone(String telephone);
	
	@Query("update Customer set type=1 where telephone=?")
	@Modifying
	public void updateType(String telephone);

	
	
	
}
