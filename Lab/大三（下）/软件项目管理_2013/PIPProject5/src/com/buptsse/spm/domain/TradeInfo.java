package com.buptsse.spm.domain;

import java.io.Serializable;

public class TradeInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idTradeInfo;
	private String title;
	private String content;
	
	
	public int getIdTradeInfo() {
		return idTradeInfo;
	}
	public void setIdTradeInfo(int idTradeInfo) {
		this.idTradeInfo = idTradeInfo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	

}
