package fr.simplex_software.quarkus_swagger_issue.exceptions;

public class CustomerNotFoundException extends RuntimeException
{
  public CustomerNotFoundException(String message)
  {
    super(message);
  }

  public CustomerNotFoundException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
