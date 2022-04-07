package com.incture.leaveapplication.Repository;

import com.incture.leaveapplication.Entity.Leave;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    @Query("SELECT l FROM Leave l WHERE l.level=0 ORDER BY l.id ASC ")
    List<Leave> findLeavesforManager();

    @Query("SELECT l FROM Leave l WHERE l.level=1 ORDER BY l.id ASC ")
    List<Leave> findLeavesforHR();
}
