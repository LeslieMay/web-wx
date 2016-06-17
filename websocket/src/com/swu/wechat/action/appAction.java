package com.swu.wechat.action;
//好友添加申请
import com.opensymphony.xwork2.Action;
import com.swu.wechat.Impl.ApplicantDaoImpl;
import com.swu.wechat.dao.ApplicantDao;

import com.swu.wechat.entity.Applicant;

import com.swu.wechat.entity.User;

public class appAction implements Action{
	private Applicant  applicant;

	
	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public String execute() throws Exception {
		  boolean flag=new ApplicantDaoImpl().addApplicant(applicant);
		   
    	  if(flag  )
    		  return SUCCESS;
    	  
    	  return null;
	}
  
}
