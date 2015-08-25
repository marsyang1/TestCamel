package com.mars.vo;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mars on 2015/8/24.
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TestVo {

    private String aa;
    private String bb;
}
