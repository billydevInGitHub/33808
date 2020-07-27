package com.sudhirt.practice.springdatajdbcpractice.entity;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("AUTHOR")
public class Author extends Auditable {

	@Id
	private Long id;

	private String firstName;

	private String lastName;

	private LocalDate dateOfBirth;

}
