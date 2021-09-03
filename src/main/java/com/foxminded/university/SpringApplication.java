package com.foxminded.university;

import com.foxminded.university.dao.ClassroomJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );
        ClassroomJdbcDao classroomJdbcDao = context.getBean(ClassroomJdbcDao.class);
        classroomJdbcDao.prr();
        context.close();
    }
}
