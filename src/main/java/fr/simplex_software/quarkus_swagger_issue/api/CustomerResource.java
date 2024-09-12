package fr.simplex_software.quarkus_swagger_issue.api;

import fr.simplex_software.quarkus_swagger_issue.domain.*;
import fr.simplex_software.quarkus_swagger_issue.service.*;
import jakarta.enterprise.context.*;
import jakarta.inject.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.*;
import java.nio.charset.*;
import java.util.*;

@ApplicationScoped
@Path("customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource
{
  @Inject
  CustomerService customerService;

  @GET
  public List<CustomerDTO> getCustomers()
  {
    return customerService.getCustomers();
  }

  @GET
  @Path("/{id}")
  public CustomerDTO getCustomer(@PathParam("id") Long id)
  {
    return customerService.getCustomer(id);
  }

  @GET
  @Path("/email/{email}")
  public CustomerDTO getCustomerByEmail(@PathParam("email") String email)
  {
    return customerService.getCustomerByEmail(URLDecoder.decode(email, StandardCharsets.UTF_8));
  }

  @POST
  public CustomerDTO createCustomer(CustomerDTO customerDTO)
  {
    return customerService.createCustomer(customerDTO);
  }

  @PUT
  public CustomerDTO updateCustomer(CustomerDTO customerDTO)
  {
    return customerService.updateCustomer(customerDTO);
  }

  @DELETE
  public void deleteCustomer(CustomerDTO customerDTO)
  {
    customerService.deleteCustomer(customerDTO.id());
  }
}
