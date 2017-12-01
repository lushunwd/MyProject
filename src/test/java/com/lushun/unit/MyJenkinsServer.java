package com.lushun.unit;

import com.lushun.reflection.ReflectionUtils;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.client.util.EncodingUtils;
import com.offbytwo.jenkins.client.util.UrlUtils;
import com.offbytwo.jenkins.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by lushun on 2017/12/1.
 */

public class MyJenkinsServer extends JenkinsServer {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public MyJenkinsServer(URI serverUri) {
        super(serverUri);
    }

    public MyJenkinsServer(URI serverUri, String username, String passwordOrToken) {
        super(serverUri, username, passwordOrToken);
    }

    public MyJenkinsServer(JenkinsHttpClient client) {
        super(client);
    }

    /**
     * 鲁顺新增内容
     * addJobToView
     * jobList
     */
    public String addJobToView(Object myJenkinsServer, String viewName, List<Job> jobList, Boolean crumbFlag) throws IOException, URISyntaxException {
        JenkinsHttpClient client = (JenkinsHttpClient) ReflectionUtils.getFieldValue(myJenkinsServer, "client");
        String result = "200";
        for (Job job : jobList) {
            try {
                String requestUri = UrlUtils.toBaseUrl(null) + "view/" + viewName + "/addJobToView?name=" + EncodingUtils.encodeParam(job.getName());
                client.post(requestUri, crumbFlag);
            } catch (Exception e) {
                result = "201";
                LOGGER.error("addJobToView error!" + job.getName() + "-----------" + viewName);
                e.printStackTrace();
            }
        }
        return result;
    }


    public String addJobToView(Object myJenkinsServer, List<String> jobNameList, String viewName, Boolean crumbFlag) throws IOException, URISyntaxException {
        JenkinsHttpClient client = (JenkinsHttpClient) ReflectionUtils.getFieldValue(myJenkinsServer, "client");
        String result = "200";
        for (String jobName : jobNameList) {
            try {
                String requestUri = UrlUtils.toBaseUrl(null) + "view/" + viewName + "/addJobToView?name=" + EncodingUtils.encodeParam(jobName);
                client.post(requestUri, crumbFlag);
            } catch (Exception e) {
                result = "201";
                LOGGER.error("addJobToView error!" + jobName + "-----------" + viewName);
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * single Job
     * @param myJenkinsServer
     * @param viewName
     * @param job
     * @param crumbFlag
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public String addJobToView(Object myJenkinsServer, String viewName, Job job, Boolean crumbFlag) throws IOException, URISyntaxException {
        List<Job> list =new ArrayList<Job>();
        list.add(job);
        addJobToView(myJenkinsServer,viewName,list,crumbFlag);
        return null;
    }

    public String addJobToView(Object myJenkinsServer, String viewName, String jobName, Boolean crumbFlag) throws IOException, URISyntaxException {
        List<String> list =new ArrayList<String>();
        list.add(jobName);
        addJobToView(myJenkinsServer,list,viewName,crumbFlag);
        return null;
    }
}
