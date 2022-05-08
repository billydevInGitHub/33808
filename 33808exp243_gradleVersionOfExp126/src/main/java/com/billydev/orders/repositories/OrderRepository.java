/**
 * 
 */
package com.billydev.orders.repositories;

import com.billydev.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ramesh Fadatare
 *
 */
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
