package com.swu.wechat.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.opensymphony.xwork2.ActionContext;
import com.swu.wechat.entity.Applicant;


public interface ApplicantDao {
	public List<Applicant> list(String beapplicant);
		

	 public boolean addApplicant2(Applicant applicant) ;
	
			
	 public boolean delUser(String username) ;
		
	
		
}
