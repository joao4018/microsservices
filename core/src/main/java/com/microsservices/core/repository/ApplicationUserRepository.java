package com.microsservices.core.repository;

import com.microsservices.core.model.ApplicationUser;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author joao4018
 */
public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);

}
