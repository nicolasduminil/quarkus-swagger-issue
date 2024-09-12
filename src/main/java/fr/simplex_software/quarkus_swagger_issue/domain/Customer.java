package fr.simplex_software.quarkus_swagger_issue.domain;

import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.*;

@Entity
@Table(name = "CUSTOMERS")
@Cacheable
public class Customer
{
  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "FIRST_NAME", nullable = false, length = 40)
  private String firstName;
  @Column(name ="LAST_NAME", nullable = false, length = 40)
  private String lastName;
  @Column(name = "EMAIL", nullable = false, length = 40)
  private String email;
  @Column(name = "PHONE", nullable = false, length = 40)
  private String phone;

  public Customer()
  {
  }

  public Customer(Long id, String firstName, String lastName, String email, String phone)
  {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
  }

  public Customer(String firstName, String lastName, String email, String phone)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone(String phone)
  {
    this.phone = phone;
  }
}
