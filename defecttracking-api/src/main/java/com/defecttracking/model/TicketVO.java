package com.defecttracking.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
public class TicketVO {
    private int ticketId;
    private String description;

    private List<ApplicationVO> applicationVOS;

    private List<ReleaseVO> releaseVOS;

    private String status;
    private String title;
}
