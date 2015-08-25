package com.mars.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by mars on 2015/8/24.
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LeaveRequestMail {

    private String empId;
    private String username;
    private String depName;
    private String url;
    private String documentNo;
    private String type;
    private String subType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date startDate;
    private String startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date endDate;
    private String endTime;
    private String sum;
    private String sumUnit;
    private String memo;

}
