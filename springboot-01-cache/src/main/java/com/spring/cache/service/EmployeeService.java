package com.spring.cache.service;

import com.spring.cache.bean.Employee;
import com.spring.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author biyuyang
 */
@Service
public class EmployeeService {

  @Autowired
  private EmployeeMapper employeeMapper;

  /**
   * CacheManager
   *    作用
   *      管理多个缓存Cache组件,对缓存的真正的CRUD操作在Cache组件中,每个缓存组件都有自己唯一的名字
   * @Cacheable
   *    作用
   *      将方法的运行结果进行缓存,以后再要相同的数据,直接从缓存中取
   *    常用属性
   *      cacheNames/value：指定缓存组件的名字;将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存；
   *
   *      key：缓存数据使用的key；可以用它来指定。默认是使用方法参数的值  1-方法的返回值
   *              编写SpEL； #id;参数id的值   #a0  #p0  #root.args[0]
   *              getEmp[2]
   *
   *      keyGenerator：key的生成器；可以自己指定key的生成器的组件id
   *              key/keyGenerator：二选一使用;
   *
   *
   *      cacheManager：指定缓存管理器；或者cacheResolver指定获取解析器(两者二选一)
   *
   *      condition：指定符合条件的情况下才缓存；
   *              ,condition = "#id>0"
   *          condition = "#a0>1"：第一个参数的值>1的时候才进行缓存
   *
   *      unless:否定缓存；当unless指定的条件为true，方法的返回值就不会被缓存；可以获取到结果进行判断
   *              unless = "#result == null"
   *              unless = "#a0==2":如果第一个参数的值是2，结果不缓存；
   *      sync：是否使用异步模式
   *        如果为true,则unless不起作用了
   *
   * 原理
   *    1.自动配置类:CacheAutoConfiguration
   *    2.缓存的配置类
   *      CacheConfigurationImportSelector类中查看
   *      org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
   *      org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
   *      org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
   *      org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
   *      org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
   *      org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
   *      org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
   *      org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
   *      org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
   *      org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
   *    3.哪个配置类自动生效了呢?
   *      查看各配置类上的条件注解@Conditional相关注解
   *      也可通过在配置文件中使用debug=true,查看自动配置报告,然后再控制台搜索CacheConfiguration,
   *        发现SimpleCacheConfiguration命中了,即生效了
   *    4.SimpleCacheConfiguration
   *      作用
   *        给容器中注册了一个CacheManager:ConcurrentMapCacheManager
   *        而ConcurrentMapCacheManager实现了CacheManager接口,可以获取缓存名称,也可以根据缓存名称获取缓存对象
   *          也可以获取和创建ConcurrentMapCache类型的缓存组件,ConcurrentMapCache的作用是将数据保存在ConcurrentMap中
   *    5.@Cacheable运行流程
   *      1.方法运行之前,先查询缓存组件Cache,按照cacheNames指定的名字进行获取:
   *        (CacheManager先获取相应的缓存组件Cache),第一次获取Cache,如果为null,则会自动创建
   *        使用ConcurrentHashMap来存储key和对应的Cache
   *      2.去Cache中查找缓存的内容,根据一个key,默认就是方法的参数;
   *        key是按照某种策略生成的,默认使用KeyGenerator生成,默认使用SimpleKeyGenerator生成Key
   *          SimpleKeyGenerator生成key的默认策略:
   *            SimpleKeyGenerator.generateKey(Object... params)
   *              如果没有参数:key = new SimpleKey();
   *              只有一个参数:key = 参数的值;
   *              多个参数:   key = new SimpleKey(params);
   *      3.没有查到缓存就调用目标方法(查询数据库)
   *      4.将目标方法返回的结果,放进缓存中
   *    核心:
   *      1).使用CacheManager(默认是ConcurrentMapCacheManager)按照名称得到Cache(默认是ConcurrentMapCache)组件
   *      2).key使用KeyGenerator生成的,默认是SimpleKeyGenerator
   * @param id id
   * @return Employee
   */
  @Cacheable(value = "emp",key="#id")
  public Employee getById(Integer id){
    System.out.println("EmployeeService: "+Thread.currentThread().getName());
    System.out.println("访问数据库查询"+id+"号员工");
    return employeeMapper.getEmpById(id);
  }

  /**
   * @CachePut:保证方法被调用,又希望结果被缓存
   *
   * @param employee emp
   * @return emp
   */
  @CachePut(value = "emp",key = "#employee.id")
  public Employee updateEmp(Employee employee){
    employeeMapper.updateEmp(employee);
    return employee;
  }

}
