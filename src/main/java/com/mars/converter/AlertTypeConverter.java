package com.mars.converter;

import com.google.common.base.Optional;
import com.mars.repo.UserManager;
import com.mars.vo.Alert;
import com.mars.vo.HrUser;
import com.mars.vo.LeaveRequestMail;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.TypeConversionException;
import org.apache.camel.support.TypeConverterSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;

/**
 * Created by mars on 2015/8/25.
 */
@Slf4j
@Component
public class AlertTypeConverter extends TypeConverterSupport {

    private static final String DEFAULT_MAIL_SUBJECT = "假單處理完成通知!!";

    @Autowired
    private UserManager manager;

    public AlertTypeConverter() {

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T convertTo(Class<T> type, Exchange exchange, Object value) throws TypeConversionException {
        // converter from value to the MyOrder bean
        LeaveRequestMail body = exchange.getIn().getBody(LeaveRequestMail.class);
        String mailSubject = getMailSubject(exchange);
        Alert.AlertBuilder builder = Alert.builder();
        Optional<HrUser> userBox = manager.getHrUserByEmpId(body.getEmpId());
        if (!userBox.isPresent()) {
            // has exception
            log.error("get hrUser  is not exists , print body :" + body);
            throw new RuntimeCamelException("Could not find valueOf method on LeaveRequestMail type: " + type.getName() + "User is not exists , need check");
        }
        HrUser hrUser = userBox.get();
        Optional<Integer> sidBox = manager.getFusionUserSid(hrUser.getLoginId());
        if (!sidBox.isPresent()) {
            log.error("get userSid is not exists , print hrUser.loginId :" + hrUser.getLoginId());
            throw new RuntimeCamelException("Could not find valueOf method on LeaveRequestMail type: " + type.getName() + "User is not exists , need check");
        }
        Integer userSid = sidBox.get();
        return (T) builder.userContact(userSid).description(body.getMemo()).subject(mailSubject).build();
    }

    public String getMailSubject(Exchange exchange) {
        try {
            return MimeUtility.decodeText(exchange.getIn().getHeader("Subject", String.class));
        } catch (UnsupportedEncodingException e) {
            log.error("get head with encode fail", e);
            return DEFAULT_MAIL_SUBJECT;
        }
    }

    public String getDefaultMailSubject() {
        return DEFAULT_MAIL_SUBJECT;
    }
}
