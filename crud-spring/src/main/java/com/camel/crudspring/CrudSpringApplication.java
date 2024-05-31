package com.camel.crudspring;

import com.camel.crudspring.enums.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.camel.crudspring.model.Course;
import com.camel.crudspring.repositories.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();
			Course c1 = new Course();
			c1.setName("Angular");
			c1.setCategory(Category.FRONT_END);
			courseRepository.save(c1);

			Course c2 = new Course();
			c2.setName("ReactJS");
			c2.setCategory(Category.FRONT_END);
			courseRepository.save(c2);

			Course c3 = new Course();
			c3.setName("Javaa");
			c3.setCategory(Category.BACK_END);
			courseRepository.save(c3);

			Course c4 = new Course();
			c4.setName("Microsoft C#");
			c4.setCategory(Category.BACK_END);
			courseRepository.save(c4);
		};

	}

}
