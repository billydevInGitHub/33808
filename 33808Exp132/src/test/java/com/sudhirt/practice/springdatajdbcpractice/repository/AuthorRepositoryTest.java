package com.sudhirt.practice.springdatajdbcpractice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import com.sudhirt.practice.springdatajdbcpractice.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AuthorRepositoryTest {

	@Autowired
	private AuthorRepository authorRepository;

	@Test
	public void should_populate_audit_fields_properly_with_values() {
		Author author = Author.builder().firstName("John").lastName("Miller").dateOfBirth(LocalDate.of(1972, 03, 01))
				.build();
		authorRepository.save(author);
		assertThat(author.getCreatedBy()).isEqualTo("TEST");
		assertThat(author.getCreatedDate()).isNotNull();
		assertThat(author.getLastModifiedBy()).isEqualTo("TEST");
		assertThat(author.getLastModifiedDate()).isNotNull();
	}

	@Test
	public void should_save_successfully() {
		Author author = Author.builder().firstName("John").lastName("Miller").dateOfBirth(LocalDate.of(1972, 03, 01))
				.build();
		authorRepository.save(author);
		assertThat(author.getId()).isNotNull();
	}

}
