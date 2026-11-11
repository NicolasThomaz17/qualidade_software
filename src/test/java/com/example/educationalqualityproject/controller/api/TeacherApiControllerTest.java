package com.example.educationalqualityproject.controller.api;

import com.example.educationalqualityproject.entity.Teacher;
import com.example.educationalqualityproject.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeacherApiController.class)
class TeacherApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Test
    void returnsAllTeachers() throws Exception {
        when(teacherService.getAllTeachers()).thenReturn(List.of());

        mockMvc.perform(get("/api/teachers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void returnsNotFoundForUnknownTeacher() throws Exception {
        when(teacherService.getTeacherById("missing")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/teachers/missing"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createsTeacherWhenEmailIsUnique() throws Exception {
        Teacher saved = new Teacher("Grace", "grace@example.test", "Computing");
        saved.setId("teacher-1");
        when(teacherService.existsByEmail("grace@example.test")).thenReturn(false);
        when(teacherService.saveTeacher(any(Teacher.class))).thenReturn(saved);

        mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Grace","email":"grace@example.test","department":"Computing"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("teacher-1"))
                .andExpect(jsonPath("$.name").value("Grace"));
    }

    @Test
    void rejectsTeacherWithDuplicateEmail() throws Exception {
        when(teacherService.existsByEmail("grace@example.test")).thenReturn(true);

        mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"name":"Grace","email":"grace@example.test","department":"Computing"}
                                """))
                .andExpect(status().isConflict());
    }
}
