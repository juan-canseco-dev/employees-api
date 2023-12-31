# Employees Api
Simple api made with Spring Boot 3 using MySQL for the database.

## Persistence Tests
```java
@SpringBootTest
@DisplayName("Employees Repository Integration Tests")
public class EmployeesTests extends MySQLTestBase {
    @Autowired
    private EmployeeRepository repository;

    @AfterEach
    public void cleanup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Create Employee")
    public void createEmployeeTest() {

        var newEntity = Employee.builder()
                .firstname("John")
                .lastname("Doe").build();

        var createdEntity = repository.saveAndFlush(newEntity);

        assertTrue(createdEntity.getId() > 0L);
        assertEquals(newEntity.getFirstname(), createdEntity.getFirstname());
        assertEquals(newEntity.getLastname(), createdEntity.getLastname());
    }
}
```
## Integration Tests
```java
@SpringBootTest
@AutoConfigureMockMvc
public class CreateEmployeeTests extends MySQLTestBase {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private EmployeeRepository repository;
    @AfterEach
    public void cleanup() {
        repository.deleteAll();
    }
    @Test
    public void createEmployeeStatusShouldBeOk() throws Exception {

        var createdDto = new CreateEmployeeDto("John", "Doe");

        var request = MockMvcRequestBuilders
                .post("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createdDto));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"));
    }

    @Test
    public void createEmployeeWhenValidationErrorStatusShouldBe400() throws Exception{
        var createdDto = new CreateEmployeeDto("", "Doe");

        var request = MockMvcRequestBuilders
                .post("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createdDto));

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

}
```
## Libraries
* JPA
* Test Containers


