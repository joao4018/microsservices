package com.microsservices.core.repository;



import com.microsservices.core.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author joao4018
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
}
