// EmployeeRepository.java
package com.itsp.loginvalidationappsb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
