package com.defecttracking.controller;

import com.defecttracking.model.TicketVO;
import com.defecttracking.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/ticket")
@Slf4j
public class TicketController {
    @Autowired
    TicketService ticketService;



    @GetMapping
    public ResponseEntity<List<TicketVO>> getTicket(){
        log.info("Inside the ticketController getTicket Method");
        List<TicketVO> ticketVOS = null;
        try{
            ticketVOS = ticketService.findAll();
            log.info("ticket are  not found");
            if(CollectionUtils.isEmpty(ticketVOS)){
                log.info("Ticket details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exception){
            log.info("Exception while calling getTicket", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<TicketVO>>(ticketVOS,HttpStatus.OK);
    }

}
