package com.hpmont.bean;
/*
 * 短信验证码验证模块
 * */
public class CodeMSG {
	private String phoneNumberLog;	//电话号码
	private long timeStampLog;		//时间戳记录
	private String codeLog;			//验证码
	private static long TIMEGAP = 120000;//2分钟
	
	public boolean getValid(String tempPhone, String tempCode)
	{
		boolean tempRst = false;
		long tempStamp = System.currentTimeMillis();//调用时的时间戳
		if(tempPhone.equals(phoneNumberLog))
		{
			long tempGap = tempStamp-timeStampLog;
			if(tempGap <= TIMEGAP)
			{
				if(tempCode.equals(codeLog))
				{
					tempRst = true;
				}
			}
		}
		return tempRst;
	}
	public void setTimeStamp()
	{//设置时间戳
		setTimeStampLog(System.currentTimeMillis());
	}
	public String getCodeLog() {
		return codeLog;
	}
	public void setCodeLog(String codeLog) {
		this.codeLog = codeLog;
	}
	public String getPhoneNumberLog() {
		return phoneNumberLog;
	}
	public void setPhoneNumberLog(String phoneNumberLog) {
		this.phoneNumberLog = phoneNumberLog;
	}
	public long getTimeStampLog() {
		return timeStampLog;
	}
	public void setTimeStampLog(long timeStampLog) {
		this.timeStampLog = timeStampLog;
	}
}
