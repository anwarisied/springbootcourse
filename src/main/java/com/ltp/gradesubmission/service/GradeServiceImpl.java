package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.CourseNotFoundException;
import com.ltp.gradesubmission.exception.GradeNotFoundException;
import com.ltp.gradesubmission.exception.StudentNotFoundException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {
    GradeRepository gradeRepository;
    StudentRepository studentRepository;
    CourseRepository courseRepository;

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (grade.isPresent()) {
            return grade.get();
        } else {
            throw new GradeNotFoundException(studentId, courseId);
        }
    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            grade.setStudent(student.get());
        } else {
            throw new StudentNotFoundException(studentId);
        }

        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            grade.setCourse(course.get());
        } else {
            throw new CourseNotFoundException(studentId);
        }
        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (grade.isPresent()) {
            Grade unwrapedGrade = grade.get();
            unwrapedGrade.setScore(score);
            return gradeRepository.save(unwrapedGrade);
        } else {
            throw new GradeNotFoundException(studentId, courseId);
        }
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Course> getCourseGrades(Long courseId) {
        List<Course> courses = gradeRepository.findByCourseId(courseId);
        return courses;
    }

    @Override
    public List<Grade> getAllGrades() {
        return null;
    }

}
