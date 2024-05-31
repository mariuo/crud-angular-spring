package com.camel.crudspring.services;

import com.camel.crudspring.exception.RecordNotFoundException;
import com.camel.crudspring.model.Course;
import com.camel.crudspring.repositories.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    public Course findById(@PathVariable @NotNull @Positive Long id){
        return courseRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(id));
    }

    public Course createCourses(@RequestBody @Valid Course course){
        return courseRepository.save(course);
    }

    public Course update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course){
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    return courseRepository.save(recordFound);
                }).orElseThrow(()-> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id){
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));

    }
}
