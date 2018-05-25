package com.hpmont.domain.page;

public class PageSearch {
	
	private int currpage;
	private String begindatetime;
	private String enddatetime;

	public int getCurrpage() {
		if (currpage > 0) {
			return currpage;
		}
		return 1;
	}

	public void setCurrpage(int currpage) {
		this.currpage = currpage;
	}

	public String getBegindatetime() {
		return begindatetime;
	}

	public void setBegindatetime(String begindatetime) {
		this.begindatetime = begindatetime;
	}

	public String getEnddatetime() {
		return enddatetime;
	}

	public void setEnddatetime(String enddatetime) {
		this.enddatetime = enddatetime;
	}
}
