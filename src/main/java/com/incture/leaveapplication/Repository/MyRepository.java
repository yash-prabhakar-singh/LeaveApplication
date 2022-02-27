package com.incture.leaveapplication.Repository;

import com.incture.leaveapplication.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends JpaRepository<Employee, Long> {

    Employee findByEmail( String email);
}
