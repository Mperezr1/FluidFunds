package com.fluidfunds.app.repository;

import com.fluidfunds.app.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository // Spring: Marks this interface as a bean for component scanning
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    /**
     * Finds a user by their email address.
     * Spring Data JPA automatically implements this method based on its name.
     *
     * @param email The email to search for.
     * @return An Optional containing the User if found, otherwise empty.
     */
    Optional<UserModel> findByEmail(String email);

}