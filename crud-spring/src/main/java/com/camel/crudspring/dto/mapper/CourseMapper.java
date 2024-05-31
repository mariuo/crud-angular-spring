package com.camel.crudspring.dto.mapper;

import com.camel.crudspring.dto.CourseDTO;
import com.camel.crudspring.enums.Category;
import com.camel.crudspring.enums.Status;
import com.camel.crudspring.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course){
        if(course == null)return null;

        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
    }

    public Course toEntity(CourseDTO dto){
        if(dto == null) return null;

        Course entity = new Course();
        if(dto.id() != null){
            entity.setId(dto.id());
        }
        entity.setName(dto.name());
        entity.setCategory(convertCategoryValue(dto.category()));
        entity.setStatus(Status.ACTIVE);
        return entity;
    }
    public Category convertCategoryValue(String value){
        if(value == null) return null;

        return switch (value){
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Invalid category: " +  value);
        };
    }
}
