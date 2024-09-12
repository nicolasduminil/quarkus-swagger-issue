package fr.simplex_software.quarkus_swagger_issue.tests;

import fr.simplex_software.quarkus_swagger_issue.domain.*;
import io.quarkus.test.junit.*;
import io.restassured.http.*;
import org.apache.http.*;
import org.junit.jupiter.api.*;

import java.net.*;
import java.nio.charset.*;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCustomers
{
  private static final String JOHN_EMAIL = URLEncoder.encode("john.doe@email.com", StandardCharsets.UTF_8);
  private static final String JANE_EMAIL = URLEncoder.encode("jane.doe@email.com", StandardCharsets.UTF_8);

  @Test
  @Order(10)
  public void testCreateCustomer()
  {
    CustomerDTO customer = new CustomerDTO("John", "Doe", "john.doe@email.com", "1234567890");
    given().body(customer).contentType(ContentType.JSON).when().post("/customers").then().statusCode(HttpStatus.SC_OK);
  }

  @Test
  @Order(40)
  public void testGetCustomers()
  {
    assertThat(given().when().get("/customers").then().statusCode(HttpStatus.SC_OK).extract().body().as(CustomerDTO[].class)).hasSize(1);
  }

  @Test
  @Order(50)
  public void testUpdateCustomer()
  {
    CustomerDTO customerDTO = given().pathParam("email", JOHN_EMAIL).when().get("/customers/email/{email}").then().statusCode(HttpStatus.SC_OK)
      .extract().body().as(CustomerDTO.class);
    assertThat(customerDTO).isNotNull();
    CustomerDTO updatedCustomer = new CustomerDTO(customerDTO.id(), "Jane", "Doe", "jane.doe@email.com", "0987654321");
    assertThat(given().body(updatedCustomer).contentType(ContentType.JSON).when().put("/customers").then().statusCode(HttpStatus.SC_OK).extract().body()
      .as(CustomerDTO.class).firstName()).isEqualTo("Jane");
  }

  @Test
  @Order(60)
  public void testGetCustomer()
  {
    assertThat(given().pathParam("email", JANE_EMAIL).when().get("/customers/email/{email}").then().statusCode(HttpStatus.SC_OK).extract().body()
      .as(CustomerDTO.class).firstName()).isEqualTo("Jane");
  }

  @Test
  @Order(90)
  public void testDeleteCustomer()
  {
    CustomerDTO customerDTO = given().pathParam("email", JANE_EMAIL).when().get("/customers/email/{email}").then().statusCode(HttpStatus.SC_OK)
      .extract().body().as(CustomerDTO.class);
    given().body(customerDTO).contentType(ContentType.JSON).when().delete("/customers").then().statusCode(HttpStatus.SC_NO_CONTENT);
  }
}
