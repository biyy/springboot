package com.spring.springboot;

import com.spring.springboot.entity.User;
import com.spring.springboot.repository.UserRepository;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot06DataJpaApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	public void contextLoads() {
		//使用LinkedList插入快,比ArrayList快
		long startTime = System.currentTimeMillis();
		LinkedList<User> list = new LinkedList<>();
		for (int i = 0; i < 1000000; i++) {
			User user = new User();
			user.setLastName(UUID.randomUUID().toString().substring(0,6));
			user.setEmail(user.getLastName()+"@qq.com");
			list.addFirst(user);
		}
		System.out.println("LinkedList: "+(System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();
		ArrayList<User> list1 = new ArrayList<>();
		for (int i = 0; i < 1000000; i++) {
			User user = new User();
			user.setLastName(UUID.randomUUID().toString().substring(0,6));
			user.setEmail(user.getLastName()+"@qq.com");
			list1.add(user);
		}
		System.out.println("ArrayList: "+(System.currentTimeMillis() - startTime));
		//userRepository.saveAll(list);
	}

	@Test
	public void test(){
		List<User> list = userRepository.findByLastNameLike("12");
		System.out.println(list.size());
		for (User user : list) {
			System.out.println(user.getId());
		}
	}

	@Test
	public void test1(){
		List<User> list = userRepository.findByLastName("12");
		System.out.println(list.size());
		for (User user : list) {
			System.out.println(user.getId());
		}
	}

	@Test
	public void test2(){
	List<User> list = userRepository.findByLastNames("%12%");
		System.out.println(list.size());
		for (User user : list) {
			System.out.println(user.getId());
		}
	}

}
