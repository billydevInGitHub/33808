package com.sudhirt.practice.springdatajdbcpractice.repository;

import com.sudhirt.practice.springdatajdbcpractice.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

}
