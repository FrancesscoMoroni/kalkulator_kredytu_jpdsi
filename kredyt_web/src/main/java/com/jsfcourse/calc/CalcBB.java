package com.jsfcourse.calc;

import java.io.Serializable;
import java.sql.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jsfcourse.entities.User;
import com.jsfcourse.resulthistory.ResultHistoryBB;

import kredyt.dao.ResultHistoryDAO;
import kredyt.entities.ResultHistory;

@Named
@ViewScoped
public class CalcBB implements Serializable {
	
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private Float loan;			// wartość kredytu
	private Float installment;	// ilość rat na rok
	private Float interest;		// oprocentowanie kredytu
	private Float inAmount;		// ilość wszystkich rat
	private Float result;
	
	@EJB
	ResultHistoryDAO resultHistoryDAO;

	public String calc() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Double result = (loan * interest) / (installment * (1 - Math.pow(installment / (installment + interest), inAmount)));
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wartość raty kredytu: " + result.floatValue(), null));
		setResult(result.floatValue());
		saveResult();
		return PAGE_STAY_AT_THE_SAME; 
	}
	
	public void saveResult() {
		ResultHistory result = new ResultHistory();

		User user = getClientFromSesion();
		
		String nazwa = user.getName() + " " + user.getSurname();
		
		result.setWysokoscRaty(this.result);
		result.setData(new Date(System.currentTimeMillis()));
		result.setNazwaUzytkownika(nazwa);
		
		resultHistoryDAO.create(result);
	}
	
	public User getClientFromSesion() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession request = (HttpSession) ctx.getExternalContext().getSession(true);
		RemoteClient client = new RemoteClient().load(request);
		
		return (User) client.getDetails();
	}

	public Float getLoan() {
		return loan;
	}


	public void setLoan(Float loan) {
		this.loan = loan;
	}


	public Float getInstallment() {
		return installment;
	}


	public void setInstallment(Float installment) {
		this.installment = installment;
	}


	public Float getInterest() {
		return interest;
	}


	public void setInterest(Float interest) {
		this.interest = interest;
	}


	public Float getInAmount() {
		return inAmount;
	}


	public void setInAmount(Float inAmount) {
		this.inAmount = inAmount;
	}


	public Float getResult() {
		return result;
	}


	public void setResult(Float result) {
		this.result = result;
	}
}
