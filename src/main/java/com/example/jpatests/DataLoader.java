package com.example.jpatests;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Order order = new Order();
        order.setCreated(LocalDateTime.now());
               
        OrderItem orderItem = new OrderItem();   
        order.addOrderItem(orderItem);
        orderRepository.save(order);
        orderItemRepository.save(orderItem);
        
		Role admin = new Role("ROLE_ADMIN");
		roleRepository.save(admin);

		User user = new User("admin");		
        userRepository.save(user);
        user.addRole(admin);
        userRepository.save(user);
    }

}