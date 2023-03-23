package com.packt.todolistjavacollection;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.packt.todolistjavacollection.repository.CrudRepositoryImp;

@SpringBootTest
class TodolistjavacollectionApplicationTests {

	@Autowired
	private CrudRepositoryImp repository;

	@Test
	//@DisplayName("First example test case")
	void contextLoads() {
		assertThat(repository).isNotNull();
	}

}
