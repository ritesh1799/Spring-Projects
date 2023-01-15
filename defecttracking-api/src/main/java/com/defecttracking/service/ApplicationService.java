package com.defecttracking.service;

import com.defecttracking.model.ApplicationVO;
import java.util.List;

public interface ApplicationService {
    List<ApplicationVO> findAll();

}
