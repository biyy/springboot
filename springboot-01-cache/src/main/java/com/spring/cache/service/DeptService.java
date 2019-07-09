package com.spring.cache.service;

import com.spring.cache.bean.Department;
import com.spring.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author biyuyang
 */
@Service
public class DeptService {

  @Autowired
  DepartmentMapper departmentMapper;

  /**
   *  缓存的数据能存入redis；
   *  第二次从缓存中查询就不能反序列化回来；
   *  存的是dept的json数据;CacheManager默认使用RedisTemplate<Object, Employee>操作Redis
   *
   *
   * @param id
   * @return
   */
//    @Cacheable(cacheNames = "dept",cacheManager = "deptCacheManager")
//    public Department getDeptById(Integer id){
//        System.out.println("查询部门"+id);
//        Department department = departmentMapper.getDeptById(id);
//        return department;
//    }

  // 使用缓存管理器得到缓存，进行api调用
  public Department getDeptById(Integer id){
    System.out.println("查询部门"+id);
    Department department = departmentMapper.getById(id);

    return department;
  }
}
