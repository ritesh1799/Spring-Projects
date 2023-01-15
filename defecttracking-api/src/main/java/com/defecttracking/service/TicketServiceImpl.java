package com.defecttracking.service;

import com.defecttracking.entity.Ticket;
import com.defecttracking.model.ApplicationVO;
import com.defecttracking.model.ReleaseVO;
import com.defecttracking.model.TicketVO;
import com.defecttracking.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService{

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ReleaseService releaseService;
    @Override
    public List<TicketVO> findAll() {
        log.info("Inside the ticket release findAll method");
        List<Ticket> tickets = ticketRepository.findAll();
        log.info("find all ticket response: {}",tickets);
        List<TicketVO> ticketVOS = tickets.parallelStream().map(ticket -> {
            List<ApplicationVO> applicationVOS = null;
            applicationVOS = applicationService.findAll();

            List<ReleaseVO> releaseVOS = null;
            releaseVOS = releaseService.findAll();
            TicketVO ticketVO = new TicketVO();
            ticketVO.setTicketId(ticket.getTicketId());
            ticketVO.setApplicationVOS(applicationVOS);
            ticketVO.setReleaseVOS(releaseVOS);
            ticketVO.setDescription(ticket.getDescription());
            ticketVO.setStatus(ticket.getStatus());
            ticketVO.setTitle(ticket.getTitle());
            return ticketVO;
        }).collect(Collectors.toList());
        return ticketVOS;
    }
}
