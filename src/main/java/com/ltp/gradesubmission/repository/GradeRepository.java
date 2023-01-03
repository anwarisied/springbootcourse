package com.ltp.gradesubmission.repository;

import com.ltp.gradesubmission.entity.Grade;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface GradeRepository extends CrudRepository<Grade, Long> {
    Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);

    void deleteByStudentIdAndCourseId(Long studentId, Long courseId);
}