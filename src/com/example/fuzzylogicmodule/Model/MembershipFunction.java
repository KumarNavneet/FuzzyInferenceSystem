package com.example.fuzzylogicmodule.Model;

public class MembershipFunction {
	private int paramCount;
	private String memFuncName;
	private String params[];
	
	public MembershipFunction(String memName, int pc, String params ){
		this.paramCount = pc;
		this.memFuncName = memName;
		this.params = params.split(",");
	}
	
	public int getParamCount(){
		return this.paramCount;
	}
	public void setParamCount(int pc){
		this.paramCount = pc;
	}
	
	public String getMemfuncName(){
		return this.memFuncName;
	}
	public void setMemfuncName(String name){
		this.memFuncName = name;
	}
	
	public String[] getMemfuncParams(){
		return this.params;
	}
	public void setMemfuncParams(String params){
		this.params = params.split(",");
	}

}
