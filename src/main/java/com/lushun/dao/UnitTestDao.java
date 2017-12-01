package com.lushun.dao;

import com.lushun.entity.JobDetail;
import com.lushun.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UnitTestDao {
    int  saveTestResult(List<JobDetail> jobDetails);
}
