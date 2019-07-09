package com.spring.cache;

import com.spring.cache.bean.Department;
import com.spring.cache.bean.Employee;
import com.spring.cache.mapper.DepartmentMapper;
import com.spring.cache.mapper.EmployeeMapper;
import com.spring.cache.service.EmployeeService;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

  @Autowired
  DataSource dataSource;

  @Autowired
  DepartmentMapper departmentMapper;
  @Autowired
  EmployeeMapper employeeMapper;
  @Autowired
  EmployeeService employeeService;
  @Test
  public void contextLoads() {
    Department byId = departmentMapper.getById(1);
    System.out.println(byId);
  }

  @Test
  public void getEmp(){
    Employee empById = employeeMapper.getEmpById(1);
    System.out.println(empById);
  }

  @Test
  public void testCacheAble(){
    Employee empById = employeeService.getById(1);
    System.out.println(empById);
  }

}
