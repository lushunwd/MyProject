package com.jd.dao;

import com.jd.entity.JobDetail;
import com.jd.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UnitTestDao {
    int  saveTestResult(List<JobDetail> jobDetails);
}
