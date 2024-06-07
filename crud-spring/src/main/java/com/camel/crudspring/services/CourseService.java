package com.camel.crudspring.services;

import com.camel.crudspring.dto.CourseDTO;
import com.camel.crudspring.dto.mapper.CourseMapper;
import com.camel.crudspring.exception.RecordNotFoundException;
import com.camel.crudspring.model.Course;
import com.camel.crudspring.repositories.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper){
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> listCourses() {
        return courseRepository.findAll().stream().map(courseMapper::toDTO).collect(Collectors.toList());
    }

    public CourseDTO findById(@NotNull @Positive Long id){
        return courseRepository.findById(id).map(courseMapper::toDTO).orElseThrow(()-> new RecordNotFoundException(id));
    }

    public CourseDTO createCourses(@Valid CourseDTO dto){
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(dto)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @RequestBody @Valid @NotNull CourseDTO dto){
        return courseRepository.findById(id)
                .map(recordFound -> {
                    Course course = courseMapper.toEntity(dto);
                    recordFound.setName(dto.name());
                    recordFound.setCategory(courseMapper.convertCategoryValue(dto.category()));
                    recordFound.getLessons().clear();
                    course.getLessons().forEach(recordFound.getLessons()::add);

                    return courseMapper.toDTO(courseRepository.save(recordFound));
                }).orElseThrow(()-> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id){
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));

    }
}
