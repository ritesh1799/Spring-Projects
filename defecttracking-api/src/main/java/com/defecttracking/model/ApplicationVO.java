package com.defecttracking.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationVO {
    private long applicationId;
    private String description;
    private String applicationName;
    private String owner;

}
