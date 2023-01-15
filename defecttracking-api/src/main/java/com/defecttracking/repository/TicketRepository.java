package com.defecttracking.repository;

import com.defecttracking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
