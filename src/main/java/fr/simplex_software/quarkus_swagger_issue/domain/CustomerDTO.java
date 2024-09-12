package fr.simplex_software.quarkus_swagger_issue.domain;

public record CustomerDTO(Long id, String firstName, String lastName, String email, String phone)
{
  public CustomerDTO(String firstName, String lastName, String email, String phone)
  {
    this(null, firstName, lastName, email, phone);
  }
}
