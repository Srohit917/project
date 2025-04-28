package com.employees.employee.mapper;

import com.employees.employee.entity.Employee;
import com.employees.employee.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = {LocalDateMapper.class})
public interface EmployeeMapper {
    EmployeeDto EmployeeDaoToDto(Employee employee);

    Employee EmployeeDtoToDao(EmployeeDto EmployeeDto);

    @Mapping(target = "id", ignore = true)
    void updateEmployeeFromDto(EmployeeDto dto, @MappingTarget Employee employee);

}