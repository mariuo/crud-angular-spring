package com.camel.crudspring.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.camel.crudspring.model.Course;
import com.camel.crudspring.repositories.CourseRepository;

import lombok.AllArgsConstructor;
@Validated
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
    public ResponseEntity<Course> createCourses(@RequestBody @Valid Course course){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable @NotNull @Positive Long id){
        return courseRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course){
        return courseRepository.findById(id)
                .map(recordFound -> {
                            recordFound.setName(course.getName());
                            recordFound.setCategory(course.getCategory());
                            Course updated = courseRepository.save(recordFound);
                            return ResponseEntity.ok().body(updated);

                        })

                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id){
        return courseRepository.findById(id)
                .map(recordFound -> {
                        courseRepository.deleteById(id);
                        return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
