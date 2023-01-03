package com.ltp.gradesubmission.repository;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface GradeRepository extends CrudRepository<Grade, Long> {
    Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Transactional
    void deleteByStudentIdAndCourseId(Long studentId, Long courseId);

    List<Grade> findByStudentId(Long studentId);

    List<Course> findByCourseId(Long courseId);
}