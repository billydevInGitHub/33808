/**
 * 
 */
package com.billydev.security.repositories;

import com.billydev.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ramesh Fadatare
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>
{
	
}

