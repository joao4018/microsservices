package com.microsservices.course.endpoint.service;

import com.microsservices.course.model.Course;
import com.microsservices.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author joao4018
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseService {
    private final CourseRepository courseRepository;

    public Iterable<Course> list(Pageable pageable) {
        log.info("Listing all courses");
        return courseRepository.findAll(pageable);
    }
}