package com.camel.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.camel.crudspring.model.Course;
import com.camel.crudspring.repositories.CourseRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping
    public @ResponseBody List<Course> listCourses() {

        return courseRepository.findAll();
    }

    @PostMapping
//    @ResponseStatus(code = HttpStatus.CREATED )
    public ResponseEntity<Course> createCourses(@RequestBody Course course){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id){
        return courseRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course){
        return courseRepository.findById(id)
                .map(recordFound -> {
                            recordFound.setName(course.getName());
                            recordFound.setCategory(course.getCategory());
                            Course updated = courseRepository.save(recordFound);
                            return ResponseEntity.ok().body(updated);

                        })

                .orElse(ResponseEntity.notFound().build());
    }


}
