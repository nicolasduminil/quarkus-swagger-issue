package fr.simplex_software.quarkus_swagger_issue.tests;

import fr.simplex_software.quarkus_swagger_issue.domain.*;
import io.quarkus.test.junit.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.parsing.*;
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
  private static Long customerId;

  @Test
  @Order(10)
  public void testCreateCustomer()
  {
    Customer customer = new Customer("John", "Doe", "john.doe@email.com", "1234567890");
    given().body(customer).contentType(ContentType.JSON).when().post("/customers").then().statusCode(HttpStatus.SC_CREATED);
  }

  @Test
  @Order(40)
  public void testGetCustomers()
  {
    Customer[] customer = given().when().get("/customers").then().statusCode(HttpStatus.SC_OK).extract().body().as(Customer[].class);
    assertThat(customer).hasSize(1);
    customerId = customer[0].getId();
  }

  @Test
  @Order(50)
  public void testUpdateCustomer()
  {
    Customer customer = new Customer("Jane", "Doe", "jane.doe@email.com", "1234567890");
    assertThat(given().body(customer).accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("id", customerId).when().put("/customers/{id}").then().statusCode(HttpStatus.SC_NO_CONTENT));
  }

  @Test
  @Order(60)
  public void testGetCustomer()
  {
    assertThat(given().pathParam("id", customerId).when().get("/customers/{id}").then().statusCode(HttpStatus.SC_OK).extract().body()
      .as(Customer.class).getFirstName()).isEqualTo("Jane");
  }

  @Test
  @Order(90)
  public void testDeleteCustomer()
  {
    given().when().pathParam("id", customerId).delete("/customers/{id}").then().statusCode(HttpStatus.SC_NO_CONTENT);
  }
}
