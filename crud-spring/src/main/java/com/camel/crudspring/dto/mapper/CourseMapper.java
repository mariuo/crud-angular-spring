package com.camel.crudspring.dto.mapper;

import com.camel.crudspring.dto.CourseDTO;
import com.camel.crudspring.dto.LessonDTO;
import com.camel.crudspring.enums.Category;
import com.camel.crudspring.enums.Status;
import com.camel.crudspring.model.Course;
import com.camel.crudspring.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course){
        if(course == null)return null;

        List<LessonDTO> lessonDTOList = course.getLessons()
                .stream().map(lesson -> new LessonDTO(
                        lesson.getId(), lesson.getName(), lesson.getUrlYoutube()
                )).collect(Collectors.toList());
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessonDTOList);
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

        List<Lesson> lessons = dto.lessons().stream().map(lessonDTO -> {
            var lesson = new Lesson();
            lesson.setId(lessonDTO.id());
            lesson.setName(lessonDTO.name());
            lesson.setUrlYoutube(lessonDTO.urlYoutube());
            lesson.setCourse(entity);
            return lesson;
        }).collect(Collectors.toList());

        entity.setLessons(lessons);
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
