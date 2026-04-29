package pasantia.backend.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pasantia.backend.entity.Students;
import pasantia.backend.repository.StudentRepository;
import pasantia.backend.service.StudentServiceImplement;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTesting {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImplement studentService;

    private Students student;

    @BeforeEach
    void setUp() {
        student = new Students();
        student.setId(1);
        student.setName("María González");
        student.setMail("maria@example.com");
        student.setPassword("securePass123");
        student.setCv("cv_maria.pdf");
        student.setInterestCarrer("Ingeniería en Sistemas");
        student.setBirthday(new Date(2020,12,11));
    }

    // ─── 1. SAVE ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("save: guarda y retorna el estudiante correctamente")
    void save_ShouldReturnSavedStudent() {
        when(studentRepository.save(student)).thenReturn(student);

        Students result = studentService.save(student);

        assertNotNull(result);
        assertEquals(student.getId(), result.getId());
        assertEquals("María González", result.getName());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    @DisplayName("save: persiste todos los campos del estudiante")
    void save_ShouldPersistAllFields() {
        when(studentRepository.save(student)).thenReturn(student);

        Students result = studentService.save(student);

        assertEquals("maria@example.com", result.getMail());
        assertEquals("cv_maria.pdf", result.getCv());
        assertEquals("Ingeniería en Sistemas", result.getInterestCarrer());
        assertNotNull(result.getBirthday());
        verify(studentRepository, times(1)).save(student);
    }

    // ─── 2. FIND ALL ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("findAll: retorna todos los estudiantes registrados")
    void findAll_ShouldReturnAllStudents() {
        Students student2 = new Students();
        student2.setId(2);
        student2.setName("Carlos Pérez");
        student2.setMail("carlos@example.com");

        when(studentRepository.findAll()).thenReturn(Arrays.asList(student, student2));

        List<Students> result = studentService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("María González", result.get(0).getName());
        assertEquals("Carlos Pérez", result.get(1).getName());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findAll: retorna lista vacía cuando no hay estudiantes")
    void findAll_ShouldReturnEmptyList_WhenNoStudents() {
        when(studentRepository.findAll()).thenReturn(Collections.emptyList());

        List<Students> result = studentService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(studentRepository, times(1)).findAll();
    }

    // ─── 3. FIND BY ID ───────────────────────────────────────────────────────

    @Test
    @DisplayName("findById: retorna el estudiante cuando existe el ID")
    void findById_ShouldReturnStudent_WhenIdExists() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        Students result = studentService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("María González", result.getName());
        verify(studentRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("findById: lanza excepción cuando el ID no existe")
    void findById_ShouldThrowException_WhenIdNotFound() {
        when(studentRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(java.util.NoSuchElementException.class,
                () -> studentService.findById(99));

        verify(studentRepository, times(1)).findById(99);
    }

    // ─── 4. UPDATE ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("update: guarda y retorna el estudiante con los datos actualizados")
    void update_ShouldReturnUpdatedStudent() {
        student.setName("María González Actualizada");
        student.setInterestCarrer("Ciencias de Datos");
        when(studentRepository.save(student)).thenReturn(student);

        Students result = studentService.update(student);

        assertNotNull(result);
        assertEquals("María González Actualizada", result.getName());
        assertEquals("Ciencias de Datos", result.getInterestCarrer());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    @DisplayName("update: usa save internamente y no realiza operaciones adicionales")
    void update_ShouldOnlyCallSaveOnce() {
        when(studentRepository.save(student)).thenReturn(student);

        studentService.update(student);

        verify(studentRepository, times(1)).save(student);
        verifyNoMoreInteractions(studentRepository);
    }

    // ─── 5. DELETE BY ID ─────────────────────────────────────────────────────

    @Test
    @DisplayName("deleteById: llama al repositorio con el ID correcto")
    void deleteById_ShouldCallRepositoryDeleteById() {
        doNothing().when(studentRepository).deleteById(1);

        studentService.deleteById(1);

        verify(studentRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("deleteById: no realiza operaciones adicionales tras eliminar")
    void deleteById_ShouldOnlyCallDeleteOnce() {
        doNothing().when(studentRepository).deleteById(1);

        studentService.deleteById(1);

        verify(studentRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(studentRepository);
    }
}
