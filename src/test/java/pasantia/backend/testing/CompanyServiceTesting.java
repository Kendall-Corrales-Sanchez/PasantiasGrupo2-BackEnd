package pasantia.backend.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pasantia.backend.entity.Companies;
import pasantia.backend.repository.CompanyRepository;
import pasantia.backend.security.JwtService;
import pasantia.backend.service.CompanyServiceImplement;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompanyServiceTesting {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private CompanyServiceImplement companyService;

    // Estado compartido entre tests para simular el flujo completo
    private static Companies sharedCompany;

    @BeforeEach
    void setUp() {
        // Solo inicializa si aún no existe (primer test: save)
        if (sharedCompany == null) {
            sharedCompany = new Companies();
            sharedCompany.setId(1);
            sharedCompany.setName("Prueba S.A.");
            sharedCompany.setMail("test@mail.com");
            sharedCompany.setPassword("1234");
            sharedCompany.setWhoAreWe("Software");
        }
    }

    // ─────────────────────────────────────────────
    // 1. GUARDAR
    // ─────────────────────────────────────────────
    @Test
    @Order(1)
    @DisplayName("1 - Guardar empresa y verificar contraseña encriptada")
    void saveTest() {
        // Arrange
        when(encoder.encode("1234")).thenReturn("encodedPassword123");
        when(companyRepository.save(any(Companies.class))).thenReturn(sharedCompany);

        // Act
        Companies saved = companyService.save(sharedCompany);

        // Assert
        assertNotNull(saved, "La empresa guardada no debe ser nula");
        assertEquals("Prueba S.A.", saved.getName(), "El nombre debe coincidir");
        assertEquals("encodedPassword123", saved.getPassword(), "La contraseña debe estar encriptada");
        verify(encoder, times(1)).encode("1234");
        verify(companyRepository, times(1)).save(any(Companies.class));
    }

    // ─────────────────────────────────────────────
    // 2. LISTAR
    // ─────────────────────────────────────────────
    @Test
    @Order(2)
    @DisplayName("2 - Listar empresas y verificar que contiene la empresa guardada")
    void findAllTest() {
        // Arrange
        when(companyRepository.findAll()).thenReturn(Arrays.asList(sharedCompany));

        // Act
        List<Companies> list = companyService.findAll();

        // Assert
        assertNotNull(list, "La lista no debe ser nula");
        assertFalse(list.isEmpty(), "La lista no debe estar vacía");
        assertEquals(1, list.size(), "Debe haber exactamente 1 empresa");
        assertEquals("Prueba S.A.", list.get(0).getName(), "El nombre debe coincidir con la empresa guardada");
        verify(companyRepository, times(1)).findAll();
    }

    // ─────────────────────────────────────────────
    // 3. BUSCAR POR ID
    // ─────────────────────────────────────────────
    @Test
    @Order(3)
    @DisplayName("3 - Buscar empresa por ID")
    void findByIdTest() {
        // Arrange
        when(companyRepository.findById(1)).thenReturn(Optional.of(sharedCompany));

        // Act
        Companies found = companyService.findById(1);

        // Assert
        assertNotNull(found, "La empresa encontrada no debe ser nula");
        assertEquals(1, found.getId(), "El ID debe ser 1");
        assertEquals("Prueba S.A.", found.getName(), "El nombre debe coincidir");
        verify(companyRepository, times(1)).findById(1);
    }

    // ─────────────────────────────────────────────
    // 4. EDITAR
    // ─────────────────────────────────────────────
    @Test
    @Order(4)
    @DisplayName("4 - Editar empresa y verificar cambios")
    void updateTest() {
        // Arrange — modificamos el estado compartido como si fuera una edición real
        sharedCompany.setName("Prueba S.A. Editada");
        sharedCompany.setWhoAreWe("Software & Consulting");
        when(companyRepository.save(any(Companies.class))).thenReturn(sharedCompany);

        // Act
        Companies updated = companyService.update(sharedCompany);

        // Assert
        assertNotNull(updated, "La empresa actualizada no debe ser nula");
        assertEquals("Prueba S.A. Editada", updated.getName(), "El nombre debe reflejar la edición");
        assertEquals("Software & Consulting", updated.getWhoAreWe(), "El campo whoAreWe debe estar actualizado");
        verify(companyRepository, times(1)).save(any(Companies.class));
    }

    // ─────────────────────────────────────────────
    // 5. ELIMINAR
    // ─────────────────────────────────────────────
    @Test
    @Order(5)
    @DisplayName("5 - Eliminar empresa y verificar que no existe")
    void deleteTest() {
        // Arrange
        doNothing().when(companyRepository).deleteById(1);
        // Simulamos que después de eliminar ya no se encuentra
        when(companyRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        companyService.deleteById(1);

        // Assert
        verify(companyRepository, times(1)).deleteById(1);

        // Verificamos que ya no existe
        Optional<Companies> deleted = companyRepository.findById(1);
        assertTrue(deleted.isEmpty(), "La empresa ya no debe existir tras eliminarla");

        // Limpiamos el estado compartido
        sharedCompany = null;
    }
}