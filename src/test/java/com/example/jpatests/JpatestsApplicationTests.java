package com.example.jpatests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpatestsApplicationTests {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderItemRepository orderItemRepository;

	int orderId = 0;

	@Test
	public void contextLoads() {
	}

	@Before
	public void setUp() {
		
		/* 
		Role admin = new Role("ROLE_ADMIN");
		roleRepository.save(admin);

		User user = new User("admin");		
        userRepository.save(user);
        user.addRole(admin);
		userRepository.save(user); */
		
		orderItemRepository.deleteAll();
		orderRepository.deleteAll();

		Order order = new Order();			
		OrderItem orderItem = new OrderItem();				
		order.addOrderItem(orderItem);
		
		orderRepository.save(order);
		orderItemRepository.save(orderItem);
		this.orderId = order.getId();	
	}

	@Test
    public void getUseradmin() {
		User user = userRepository.getUserByName("admin");
		assertThat(user.getName()).isEqualTo("admin");
	}

	@Test 
	public void getOrderCreated() {
		Optional<Order> order = orderRepository.findById(this.orderId);
		assertThat(order).isNotNull();
	}

	@Test 
	public void getOrderItems() {
		List<OrderItem> orderItems = orderItemRepository.findAll();
		assertThat(orderItems.size()).isEqualTo(1);
	}

	@Test 
	public void getOrderItemsFromOrder() {
		Order order = orderRepository.findOrderWithItems(this.orderId);		
		assertThat(order).isNotNull();
		assertThat(order.getOrderItems().size()).isEqualTo(1);
	}
}
