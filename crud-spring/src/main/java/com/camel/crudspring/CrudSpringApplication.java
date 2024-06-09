package com.camel.crudspring;

import com.camel.crudspring.enums.Category;
import com.camel.crudspring.model.Lesson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.camel.crudspring.model.Course;
import com.camel.crudspring.repositories.CourseRepository;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	@Profile("dev")
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();
			for(int i = 0 ; i <20; i++){

			Course c1 = new Course();
			c1.setName("Angular" + i);
			c1.setCategory(Category.FRONT_END);

			Lesson l1 = new Lesson();
			l1.setName("Intro" + i);
			l1.setUrlYoutube("watch?v=123");
			l1.setCourse(c1);

			Lesson l11 = new Lesson();
			l11.setName("Profile" + i);
			l11.setUrlYoutube("watch?v=321");
			l11.setCourse(c1);

			c1.getLessons().add(l1);
			c1.getLessons().add(l11);
			courseRepository.save(c1);

			Course c2 = new Course();
			c2.setName("ReactJS" + i);
			c2.setCategory(Category.FRONT_END);
			Lesson l2 = new Lesson();
			l2.setName("Intro" + i);
			l2.setUrlYoutube("watch?v=234");
			l2.setCourse(c2);
			c2.getLessons().add(l2);
			courseRepository.save(c2);

			Course c3 = new Course();
			c3.setName("Javaa" + i);
			c3.setCategory(Category.BACK_END);
			Lesson l3 = new Lesson();
			l3.setName("Intro");
			l3.setUrlYoutube("watch?v=345");
			l3.setCourse(c3);
			c3.getLessons().add(l3);
			courseRepository.save(c3);

			Course c4 = new Course();
			c4.setName("Microsoft C#"  + i);
			c4.setCategory(Category.BACK_END);
			Lesson l4 = new Lesson();
			l4.setName("Intro"  + i);
			l4.setUrlYoutube("watch?v=456");
			l4.setCourse(c4);
			c4.getLessons().add(l4);
			courseRepository.save(c4);
			}
		};

	}

}
