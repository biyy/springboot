package com.spring.cache.mapper;

import com.spring.cache.bean.Department;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author biyuyang
 */
public interface DepartmentMapper {

  @Insert("insert into department values(#{departmentName})")
  int insert(Department department);

  @Select("select * from department where id=#{id}")
  Department getById(@Param("id") Integer id);
}
