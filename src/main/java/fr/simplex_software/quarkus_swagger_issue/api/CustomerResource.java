package fr.simplex_software.quarkus_swagger_issue.api;

import fr.simplex_software.quarkus_swagger_issue.domain.*;
import fr.simplex_software.quarkus_swagger_issue.repository.*;
import io.quarkus.hibernate.orm.rest.data.panache.*;
import io.quarkus.rest.data.panache.*;

@ResourceProperties(path = "customers")
public interface CustomerResource extends PanacheRepositoryResource<CustomerRepository, Customer, Long> {}
