package com.dicka.soap.soapcrudoperation.repository;

import com.dicka.soap.soapcrudoperation.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
