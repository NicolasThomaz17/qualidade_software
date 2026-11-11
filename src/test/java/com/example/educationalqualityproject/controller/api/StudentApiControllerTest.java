package com.example.educationalqualityproject.controller.api;

import com.example.educationalqualityproject.entity.Student;
import com.example.educationalqualityproject.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentApiController.class)
class StudentApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void returnsAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(List.of());

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void returnsStudentWhenFound() throws Exception {
        Student student = new Student("Ada", "ada@example.test", "S-1");
        student.setId("student-1");
        when(studentService.getStudentById("student-1")).thenReturn(Optional.of(student));

        mockMvc.perform(get("/api/students/student-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("student-1"))
                .andExpect(jsonPath("$.name").value("Ada"));
    }

    @Test
    void returnsNotFoundForUnknownStudent() throws Exception {
        when(studentService.getStudentById("missing")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/students/missing"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createsStudentWhenUnique() throws Exception {
        Student saved = new Student("Ada", "ada@example.test", "S-1");
        saved.setId("student-1");
        when(studentService.existsByEmail("ada@example.test")).thenReturn(false);
        when(studentService.existsByRegistrationNumber("S-1")).thenReturn(false);
        when(studentService.saveStudent(any(Student.class))).thenReturn(saved);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Ada","email":"ada@example.test","registrationNumber":"S-1"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("student-1"))
                .andExpect(jsonPath("$.name").value("Ada"));
    }

    @Test
    void rejectsStudentWithDuplicateEmail() throws Exception {
        when(studentService.existsByEmail("ada@example.test")).thenReturn(true);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Ada","email":"ada@example.test","registrationNumber":"S-1"}
                                """))
                .andExpect(status().isConflict());
    }

    @Test
    void deletesExistingStudent() throws Exception {
        when(studentService.getStudentById("student-1"))
                .thenReturn(Optional.of(new Student("Ada", "ada@example.test", "S-1")));

        mockMvc.perform(delete("/api/students/student-1"))
                .andExpect(status().isNoContent());

        verify(studentService).deleteStudent("student-1");
    }
}
