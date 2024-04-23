package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cg.controller.StudentController;
import com.cg.entity.Student;
import com.cg.repository.StudentRepository;

public class StudentControllerTest {

    private MockMvc mockMvc;
    private StudentRepository studentRepository;
    private StudentController studentController;

    @BeforeEach
    public void setUp() {
        studentRepository = mock(StudentRepository.class);
        studentController = new StudentController(studentRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void testSaveStudent() throws Exception {
        // Prepare test data
        Student student = new Student();
        student.setId(1L);
        student.setName("Alice");
        student.setEmail("alice@example.com");
        student.setContact("1234567890");

        // Mock repository behavior
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Perform POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Alice\",\"email\":\"alice@example.com\",\"contact\":\"1234567890\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("alice@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contact").value("1234567890"));
    }
}
