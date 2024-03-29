package com.spring.cache.mapper;

import com.spring.cache.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author biyuyang
 */
public interface EmployeeMapper {

  @Select("select * from employee where id = #{id}")
  Employee getEmpById(Integer id);

  @Update("update employee set lastName=#{lastName},email=#{email},gender=${gender},d_id=#{dId} where id=#{id} ")
  void updateEmp(Employee employee);

  @Delete("delete from employee where id=#{id}")
  void deleteEmpById(Integer id);

  @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
  void insertEmployee(Employee employee);
}
