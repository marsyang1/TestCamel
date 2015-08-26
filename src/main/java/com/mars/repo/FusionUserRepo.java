package com.mars.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by mars on 2015/8/26.
 */
@Repository
class FusionUserRepo {

    private static final String QUERY_BY_ID = "select userSid from user where dx_userId = ?";

    @Autowired
    @Qualifier("fusionJdbcTemplate")
    private JdbcTemplate template;

    public int getUserSid(String userId) {
        return template.queryForObject(QUERY_BY_ID, new Object[]{userId}, Integer.class);
    }


}
