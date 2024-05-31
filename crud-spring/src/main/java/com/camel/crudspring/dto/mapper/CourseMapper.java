package com.camel.crudspring.dto.mapper;

import com.camel.crudspring.dto.CourseDTO;
import com.camel.crudspring.enums.Category;
import com.camel.crudspring.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course){
        if(course == null)return null;

        return new CourseDTO(course.getId(), course.getName(), "Front-End");
    }

    public Course toEntity(CourseDTO dto){
        if(dto == null) return null;

        Course entity = new Course();
        if(dto.id() != null){
            entity.setId(dto.id());
        }
        entity.setName(dto.name());
        entity.setCategory(Category.FRONT_END);
        entity.setStatus("Active");
        return entity;
    }
}
