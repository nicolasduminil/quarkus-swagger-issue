package fr.simplex_software.quarkus_swagger_issue.repository;

import fr.simplex_software.quarkus_swagger_issue.domain.*;
import io.quarkus.hibernate.orm.panache.*;
import jakarta.enterprise.context.*;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {}
