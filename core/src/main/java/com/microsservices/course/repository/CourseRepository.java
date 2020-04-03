package com.microsservices.course.repository;



import com.microsservices.course.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author William Suane
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
}
