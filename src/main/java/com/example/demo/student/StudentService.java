package com.example.demo.student;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());

		if (studentOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email already exists");
		}

		studentRepository.save(student);

		System.out.println(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exist = studentRepository.existsById(studentId);

		if (!exist) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,
					"A student with the id " + studentId + " does not exists!");
		}

		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(
			Long studentId,
			String name,
			String email) {
		Student student = studentRepository.findById(studentId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "A student with the id "));

		if (name != null && name.length() > 0 && !name.equals(student.getName())) {
			student.setName(name);
		}

		if (email != null && email.length() > 0 && !email.equals(student.getEmail())) {

			Optional<Student> optionalEmail = studentRepository.findStudentsByEmail(email);

			if (optionalEmail.isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email already exists!");
			}

			student.setEmail(email);
		}

	}
}
