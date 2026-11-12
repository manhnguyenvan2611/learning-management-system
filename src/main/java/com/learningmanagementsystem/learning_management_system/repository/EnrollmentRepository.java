package com.learningmanagementsystem.learning_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learningmanagementsystem.learning_management_system.model.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    @Query(value = "SELECT e FROM Enrollment e WHERE e.courseId = :courseId AND e.userId = :userId LIMIT 1")
    Enrollment findByUserIdAndCourseId(@Param("courseId") int courseId, @Param("userId") int userId);

    List<Enrollment> findByUserId(int userId);
}
