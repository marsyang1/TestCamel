package com.mars.repo;

import com.google.common.base.Optional;
import com.mars.vo.HrUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mars on 2015/8/26.
 */
@Slf4j
@Component
public class UserManager {

    @Autowired
    private HrUserRestClient restClient;
    @Autowired
    private FusionUserRepo fusionUserRepo;


    public Optional<HrUser> getHrUser(String userId) {
        return restClient.getUser(userId);
    }

    public Optional<HrUser> getHrUserByEmpId(String empId) {
        return restClient.getUserByEmpId(empId);
    }

    public Optional<Integer> getFusionUserSid(String userId) {
        try {
            return Optional.of(fusionUserRepo.getUserSid(userId));
        } catch (Exception e) {
            log.error("query fusion userSid has exception ", e);
            return Optional.absent();
        }
    }


}
