package com.defecttracking.service;

import com.defecttracking.model.TicketVO;

import java.util.List;

public interface TicketService {
    List<TicketVO> findAll();
}
