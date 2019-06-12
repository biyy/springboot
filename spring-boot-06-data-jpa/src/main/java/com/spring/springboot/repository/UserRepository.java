package com.spring.springboot.repository;

import com.spring.springboot.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//继承JpaRepository来完成对数据库的操作
@Repository
public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

  List<User> findByLastNameLike(String lastName);

  @Query("select id,lastName,email from User where lastName like ?1 ")
  List<User> findByLastName(String lastName);

  @Query(value = "select * from tbl_user where last_name like ?1 ",nativeQuery = true)
  List<User> findByLastNames(String lastName);
}
