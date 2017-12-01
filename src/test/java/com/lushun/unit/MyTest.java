package com.lushun.unit;

import com.lushun.common.ImportExecl;
import com.lushun.dao.UnitTestDao;
import com.lushun.entity.JobDetail;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.TestResult;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
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
    private MyJenkinsServer jenkins;

    @Resource
    private UnitTestDao unitTestDao;

    @Before
    public void login() {
        try {
            jenkins = new MyJenkinsServer(new URI(jenkinsUrl), userName, password);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取测试结果并写回数据库
     *
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test
    public void getTestResults() throws URISyntaxException, IOException {
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

    /**
     * 从excel中创建job
     */
    @Test
    public void addJob() {
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read("unit.xlsx");
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                List<String> cellList = list.get(i);
                SAXReader reader = new SAXReader();
                try {
                    Document document = reader.read(new File("talents.xml"));
                    Element unintJob = document.getRootElement();
                    Node description = unintJob.selectSingleNode("description");
                    Node branch = unintJob.selectSingleNode("scm/branches/hudson.plugins.git.BranchSpec/name");
                    Node url = unintJob.selectSingleNode("scm/userRemoteConfigs/hudson.plugins.git.UserRemoteConfig/url");
                    description.setText(cellList.get(1));
                    if (cellList.get(8).contains("pull")){
                        url.setText(cellList.get(8).replace("pull",""));
                    }
                    XMLWriter output = new XMLWriter(new FileWriter(new File("template.xml")));
                    output.write(document);
                    output.close();
                    String jobName="unit_" + cellList.get(0) + "_dev";
                    jenkins.createJob(jobName, document.asXML(), true);
                    jenkins.addJobToView(jenkins, "unit testing", jobName, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取jobxml
     */
    @Test
    public void getConfigXml() {
        try {
            String s = jenkins.getJobXml("unit_jd_talents");
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws IOException
     * @throws JAXBException
     * @throws DocumentException
     * @throws URISyntaxException
     */
    @Test
    public void addJobToView() throws IOException, JAXBException, DocumentException, URISyntaxException {
        List<Job> list = new ArrayList();
        Map<String, Job> jobMap = jenkins.getJobs();
        for (String key : jobMap.keySet()) {
            if (key != null && key.startsWith("unit_")) {
                list.add(jobMap.get(key));
            }
        }
        String result = jenkins.addJobToView(jenkins, "unit testing", list, true);
        Assert.assertEquals("200", result);
    }

    /**
     * 删除job
     * @throws IOException
     */

    @Test
    public void deleteJob() throws IOException {
        Map<String, Job> jobMap = jenkins.getJobs("unit testing");
        for (String key : jobMap.keySet()) {
            if (key != null && key.startsWith("unit_fmSupplierPortal")) {
                jenkins.deleteJob(key,true);
            }
        }
    }

    @Test
    public void updateJob() throws IOException, DocumentException {
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read("unit.xlsx");
        if (list != null) {
            for (int i = 1; i < list.size(); i++) {
                List<String> cellList = list.get(i);
                SAXReader reader = new SAXReader();
                try {
                    Document document = reader.read(new File("talents.xml"));
                    Element unintJob = document.getRootElement();
                    Node description = unintJob.selectSingleNode("description");
                    Node branch = unintJob.selectSingleNode("scm/branches/hudson.plugins.git.BranchSpec/name");
                    Node url = unintJob.selectSingleNode("scm/userRemoteConfigs/hudson.plugins.git.UserRemoteConfig/url");
                    description.setText(cellList.get(1));
                    if (cellList.get(8).contains("pull")){
                        url.setText(cellList.get(8).replace("pull",""));
                    }
                    XMLWriter output = new XMLWriter(new FileWriter(new File("template.xml")));
                    output.write(document);
                    output.close();
                    String jobName="unit_" + cellList.get(0) + "_dev";
                    jenkins.updateJob(jobName, document.asXML(), true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
