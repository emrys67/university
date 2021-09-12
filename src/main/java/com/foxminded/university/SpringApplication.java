package com.foxminded.university;

import com.foxminded.university.config.SpringConfig;
import com.foxminded.university.dao.ClassroomJdbcDao;
import com.foxminded.university.entities.Classroom;
import com.foxminded.university.services.ClassroomService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );
//        ClassroomJdbcDao classroomJdbcDao = context.getBean(ClassroomJdbcDao.class);
//        classroomJdbcDao.delete((long) 500);
        ClassroomService classroomService = context.getBean(ClassroomService.class);
        classroomService.deleteClassroomById(500);
    }
}
