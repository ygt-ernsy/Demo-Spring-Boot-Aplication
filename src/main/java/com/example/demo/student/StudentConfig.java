package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Student yigit = new Student(
					"Yigit",
					"yigiterensoy@hotmail.com",
					LocalDate.of(2007, Month.JANUARY, 17));
			Student cınar = new Student(
					"Çınar",
					"cinarcirit@hotmail.com",
					LocalDate.of(2007, Month.JANUARY, 12));
			studentRepository.saveAll(List.of(yigit, cınar));
		};
	}

}
