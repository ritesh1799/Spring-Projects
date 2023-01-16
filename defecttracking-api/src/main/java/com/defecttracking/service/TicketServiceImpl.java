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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService{

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<TicketVO> findAll() {
        log.info("Inside the ticket release findAll method");
        List<Ticket> tickets = ticketRepository.findAll();
        log.info("find all ticket response: {}",tickets);
        List<TicketVO> ticketVOS = tickets.parallelStream().map(ticket -> {
            TicketVO ticketVO = new TicketVO();
            ticketVO.setTicketId(ticket.getTicketId());
            ticketVO.setDescription(ticket.getDescription());
            ticketVO.setStatus(ticket.getStatus());
            ticketVO.setTitle(ticket.getTitle());

            ApplicationVO applicationVO = new ApplicationVO();
            applicationVO.setApplicationId(ticket.getApplication().getApplicationId());
            applicationVO.setApplicationName(ticket.getApplication().getApplicationName());
            applicationVO.setDescription(ticket.getApplication().getDescription());
            applicationVO.setOwner(ticket.getApplication().getOwner());
            ticketVO.setApplicationVO(applicationVO);

            ReleaseVO releaseVO = new ReleaseVO();
            releaseVO.setRelease_date(ticket.getRelease().getRelease_date());
            releaseVO.setReleaseId(ticket.getRelease().getReleaseId());
            releaseVO.setDescription(ticket.getRelease().getDescription());
            ticketVO.setReleaseVO(releaseVO);
            return ticketVO;
        }).collect(Collectors.toList());
        return ticketVOS;
    }

    @Override
    public TicketVO TicketFindById(int id) throws Exception {
        log.info("Inside the TicketServiceImpl.TicketFindById");

        Optional<Ticket> ticket = ticketRepository.findById(id);
        log.info("find ticket response:{}",ticket);

        TicketVO ticketVO = new TicketVO();
        if(ticket.isPresent()){
            log.info("Data is present");

            ticketVO.setTicketId(ticket.get().getTicketId());
            ticketVO.setDescription(ticket.get().getDescription());
            ticketVO.setStatus(ticket.get().getDescription());
            ticketVO.setTitle(ticket.get().getTitle());

            ApplicationVO applicationVO = new ApplicationVO();
            applicationVO.setApplicationId(ticket.get().getApplication().getApplicationId());
            applicationVO.setApplicationName(ticket.get().getApplication().getApplicationName());
            applicationVO.setDescription(ticket.get().getApplication().getDescription());
            applicationVO.setOwner(ticket.get().getApplication().getOwner());
            ticketVO.setApplicationVO(applicationVO);

            ReleaseVO releaseVO = new ReleaseVO();
            releaseVO.setReleaseId(ticket.get().getRelease().getReleaseId());
            releaseVO.setDescription(ticket.get().getDescription());
            releaseVO.setRelease_date(ticket.get().getRelease().getRelease_date());
            ticketVO.setReleaseVO(releaseVO);}

        return ticketVO;
    }
}
