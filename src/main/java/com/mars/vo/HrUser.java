/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mars.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mars
 */
@Data
@SuppressWarnings("PMD.UnusedPrivateField")
public class HrUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cpnyid;
    private String empid;
    private String deptNo;
    private String possie;
    private String state;
    private String hecname;
    private String heename;
    private String uidentid;
    private String birthday;
    private String birthplace;
    private String sex;
    private String marry;
    private String pass;
    private String worktype;
    private String utype;
    private String family;
    private String given;
    private String middle;
    private String arcno;
    private String email;
    private String photo;
    private String inadate;
    private String quitdate;
    private String note;
    private String suc;
    private String token;
    private String loginId;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empid != null ? empid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HrUser)) {
            return false;
        }
        HrUser other = (HrUser) object;
        if ((this.empid == null && other.empid != null) || (this.empid != null && !this.empid.equals(other.empid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " com.cy.portal.portlet.view.bo.HrUser[ empid=" + empid + " ]";
    }

}
