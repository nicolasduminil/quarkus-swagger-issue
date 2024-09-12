package fr.simplex_software.quarkus_swagger_issue.domain;

import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper
{
  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
  Customer toEntity(CustomerDTO dto);
  CustomerDTO fromEntity(Customer entity);
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateEntityFromDTO(CustomerDTO customerDTO, @MappingTarget Customer customer);
}
