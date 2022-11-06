package com.jsfcourse.resulthistory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import kredyt.dao.ResultHistoryDAO;
import kredyt.entities.ResultHistory;

@Named
@RequestScoped
public class ResultHistoryBB {
	
	
	@EJB
	ResultHistoryDAO resultHistoryDAO;
		
	public List<ResultHistory> getFullList(){
		return resultHistoryDAO.getFullList();
	}

	public List<ResultHistory> getList(){
		List<ResultHistory> list = null;
			
		list = resultHistoryDAO.getList();
		
		return list;
	}
	
}
