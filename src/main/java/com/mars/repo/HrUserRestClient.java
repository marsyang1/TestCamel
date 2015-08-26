package com.mars.repo;

import com.google.common.base.Optional;
import com.mars.resource.HrEipProperties;
import com.mars.vo.HrUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * Created by mars on 2015/8/26.
 */
@Slf4j
@Component
public class HrUserRestClient {

    @Autowired
    private HrEipProperties properties;

    private String uri;
    private String queryUri;

    @PostConstruct
    void init() {
        uri = properties.getHrRestUrl() + "webresources/HrUser/";
        queryUri = properties.getHrRestUrl() + "webresources/query/HrUser/";
        if (properties.getHrRestUrl().isEmpty()) {
            log.warn("Hr Rest Url is Empty , Portal Todo will not include Hr message");
        }
    }

    public Optional<HrUser> getUser(String userId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String queryUrl = uri + userId;
            HrUser result = restTemplate.getForObject(queryUrl, HrUser.class);
            log.debug("Query s-HR , userId = " + userId);
            return Optional.of(result);
        } catch (IllegalArgumentException ie) {
            return Optional.absent();
        } catch (HttpClientErrorException ce) {
            return Optional.absent();
        }
    }

    public Optional<HrUser> getUserByEmpId(String empId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String queryUrl = queryUri + "/empId/" + empId;
            HrUser result = restTemplate.getForObject(queryUrl, HrUser.class);
            log.debug("Query s-HR , empId = " + empId);
            return Optional.of(result);
        } catch (IllegalArgumentException ie) {
            return Optional.absent();
        } catch (HttpClientErrorException ce) {
            return Optional.absent();
        }
    }

}
