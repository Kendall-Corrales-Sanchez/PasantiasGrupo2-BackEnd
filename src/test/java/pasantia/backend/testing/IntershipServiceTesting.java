package pasantia.backend.testing;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pasantia.backend.DTOs.InternshipDTO;
import pasantia.backend.entity.Companies;
import pasantia.backend.entity.Internships;
import pasantia.backend.entity.Modes;
import pasantia.backend.entity.Provinces;
import pasantia.backend.enumes.DayWeek;
import pasantia.backend.repository.InternshipsRepository;
import pasantia.backend.service.InternshipServiceImplement;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IntershipServiceTesting {
    @Mock
    private InternshipsRepository internshipsRepository;

    @InjectMocks
    private InternshipServiceImplement internshipService;

    private Internships internship;

    @BeforeEach
    void setUp() {
        Provinces province = new Provinces();
        province.setProvinceName("San José");

        Modes mode = new Modes();
        mode.setMode("Remoto");

        Companies company = new Companies();
        company.setName("TechCorp");

        internship = new Internships();
        internship.setId(1);
        internship.setNameInternship("Backend Developer Internship");
        internship.setDescription("Internship en desarrollo Java");
        internship.setFuncionality("Desarrollar APIs REST");
        internship.setRemuneration("500 USD");
        internship.setRequirement("Conocimientos en Spring Boot");
        internship.setExpirationDate(new Date(2020, 12, 11));
        internship.setDay(DayWeek.Monday);
        internship.setStarTime(LocalTime.of(8, 0));
        internship.setFinishTime(LocalTime.of(17, 0));
        internship.setProvince(province);
        internship.setMode(mode);
        internship.setCompany(company);
    }

    // ─── 1. SAVE ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("save: guarda y retorna la pasantía correctamente")
    void save_ShouldReturnSavedInternship() {
        when(internshipsRepository.save(internship)).thenReturn(internship);

        Internships result = internshipService.save(internship);

        assertNotNull(result);
        assertEquals(internship.getId(), result.getId());
        assertEquals("Backend Developer Internship", result.getNameInternship());
        verify(internshipsRepository, times(1)).save(internship);
    }

    @Test
    @DisplayName("save: persiste todos los campos de la pasantía")
    void save_ShouldPersistAllFields() {
        when(internshipsRepository.save(internship)).thenReturn(internship);

        Internships result = internshipService.save(internship);

        assertEquals("Internship en desarrollo Java", result.getDescription());
        assertEquals("Desarrollar APIs REST", result.getFuncionality());
        assertEquals("500 USD", result.getRemuneration());
        assertEquals("Conocimientos en Spring Boot", result.getRequirement());
        assertEquals(LocalTime.of(8, 0), result.getStarTime());
        assertEquals(LocalTime.of(17, 0), result.getFinishTime());
        verify(internshipsRepository, times(1)).save(internship);
    }

    // ─── 2. FIND ALL ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("findAll: retorna lista de DTOs con todos los campos mapeados")
    void findAll_ShouldReturnMappedDTOList() {
        when(internshipsRepository.findAll()).thenReturn(Arrays.asList(internship));

        List<InternshipDTO> result = internshipService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());

        InternshipDTO dto = result.get(0);
        assertEquals(1, dto.getId());
        assertEquals("Backend Developer Internship", dto.getNameInternship());
        assertEquals("Internship en desarrollo Java", dto.getDescription());
        assertEquals("Desarrollar APIs REST", dto.getFuncionality());
        assertEquals("500 USD", dto.getRemuneration());
        assertEquals("Conocimientos en Spring Boot", dto.getRequirement());
        assertEquals("San José", dto.getProvinceName());
        assertEquals("Remoto", dto.getModeName());
        assertEquals("TechCorp", dto.getCompanyName());
        verify(internshipsRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findAll: mapea a null cuando province, mode y company son nulos")
    void findAll_ShouldHandleNullRelations() {
        internship.setProvince(null);
        internship.setMode(null);
        internship.setCompany(null);

        when(internshipsRepository.findAll()).thenReturn(Arrays.asList(internship));

        List<InternshipDTO> result = internshipService.findAll();

        InternshipDTO dto = result.get(0);
        assertNull(dto.getProvinceName());
        assertNull(dto.getModeName());
        assertNull(dto.getCompanyName());
    }

    @Test
    @DisplayName("findAll: retorna lista vacía cuando no hay pasantías")
    void findAll_ShouldReturnEmptyList_WhenNoInternships() {
        when(internshipsRepository.findAll()).thenReturn(Collections.emptyList());

        List<InternshipDTO> result = internshipService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(internshipsRepository, times(1)).findAll();
    }

    // ─── 3. FIND BY ID ───────────────────────────────────────────────────────

    @Test
    @DisplayName("findById: retorna la pasantía cuando existe el ID")
    void findById_ShouldReturnInternship_WhenIdExists() {
        when(internshipsRepository.findById(1)).thenReturn(Optional.of(internship));

        Internships result = internshipService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Backend Developer Internship", result.getNameInternship());
        verify(internshipsRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("findById: lanza excepción cuando el ID no existe")
    void findById_ShouldThrowException_WhenIdNotFound() {
        when(internshipsRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(java.util.NoSuchElementException.class,
                () -> internshipService.findById(99));

        verify(internshipsRepository, times(1)).findById(99);
    }

    // ─── 4. UPDATE ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("update: guarda y retorna la pasantía con los datos actualizados")
    void update_ShouldReturnUpdatedInternship() {
        internship.setNameInternship("Frontend Developer Internship");
        internship.setRemuneration("600 USD");
        when(internshipsRepository.save(internship)).thenReturn(internship);

        Internships result = internshipService.update(internship);

        assertNotNull(result);
        assertEquals("Frontend Developer Internship", result.getNameInternship());
        assertEquals("600 USD", result.getRemuneration());
        verify(internshipsRepository, times(1)).save(internship);
    }

    @Test
    @DisplayName("update: usa save internamente y no realiza operaciones adicionales")
    void update_ShouldOnlyCallSaveOnce() {
        when(internshipsRepository.save(internship)).thenReturn(internship);

        internshipService.update(internship);

        verify(internshipsRepository, times(1)).save(internship);
        verifyNoMoreInteractions(internshipsRepository);
    }

    // ─── 5. DELETE BY ID ─────────────────────────────────────────────────────

    @Test
    @DisplayName("deleteById: llama al repositorio con el ID correcto")
    void deleteById_ShouldCallRepositoryDeleteById() {
        doNothing().when(internshipsRepository).deleteById(1);

        internshipService.deleteById(1);

        verify(internshipsRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("deleteById: no realiza operaciones adicionales tras eliminar")
    void deleteById_ShouldOnlyCallDeleteOnce() {
        doNothing().when(internshipsRepository).deleteById(1);

        internshipService.deleteById(1);

        verify(internshipsRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(internshipsRepository);
    }
}
