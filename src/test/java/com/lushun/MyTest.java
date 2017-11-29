package com.lushun;

import com.jd.dao.UnitTestDao;
import com.jd.dao.UserDao;
import com.jd.entity.JobDetail;
import com.jd.entity.User;
import com.jd.service.UserService;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.validation.constraints.AssertTrue;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by MyTest on 2017/11/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("production")
@ContextConfiguration(locations = {"classpath*:applicationContext.xml", "classpath*:springmvc.xml"})
public class MyTest {
    private static String jenkinsUrl = "http://192.168.157.86:8005";
    private static String userName = "admin";
    private static String password = "QAtest@QYXXHB";
    private static String view = "unit testing";

    @Resource
    private UserService userService;

    @Resource
    private UnitTestDao unitTestDao;

    @Test
    public void test01() {
        User user = new User();
        user.setUserName("鲁顺");
        List<User> list = userService.getUsers(user);
        list.get(0).getDescription();
        System.out.println("test01-----MyTest" + list.get(0).getDescription());
        Assert.assertTrue(true);
    }

    @Test
    public void getTestResults() throws URISyntaxException, IOException {
        JenkinsServer jenkins = new JenkinsServer(new URI(jenkinsUrl), userName, password);
        Map<String, Job> jobs = jenkins.getJobs(view);
        List<JobDetail> jobDetailList = new ArrayList<JobDetail>();
        String batchNo = "uint_" + String.valueOf(System.currentTimeMillis());
        for (Job job : jobs.values()) {
            JobDetail jobDetail = new JobDetail();
            JobWithDetails jobWithDetails = job.details();
            jobDetail.setPrjKey(jobWithDetails.getDisplayName());
            jobDetail.setPrjName(jobWithDetails.getDescription());
            Build build = jobWithDetails.getLastBuild();
            jobDetail.setBuildNo(String.valueOf(build.getNumber()));
            TestResult testResult;
            try {
                testResult = build.getTestResult();
            } catch (Exception e) {
                continue;
            }
            jobDetail.setDuration(String.valueOf(testResult.getDuration()));
            int passCount = testResult.getPassCount();
            int skipCount = testResult.getSkipCount();
            int failCount = testResult.getFailCount();
            int totalCount = passCount + skipCount + failCount;
            jobDetail.setTotalCount(String.valueOf(totalCount));
            jobDetail.setFailCount(String.valueOf(failCount));
            jobDetail.setSkipCount(String.valueOf(skipCount));
            jobDetail.setPassCount(String.valueOf(passCount));
            jobDetail.setClassCount(String.valueOf(testResult.getSuites().size()));
            jobDetail.setBatchNo(batchNo);
            jobDetailList.add(jobDetail);
        }
        unitTestDao.saveTestResult(jobDetailList);
    }
}
