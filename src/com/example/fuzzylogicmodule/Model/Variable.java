package com.example.fuzzylogicmodule.Model;

import java.util.ArrayList;

public class Variable {
	private String name;
	private int type;
	private int lowerLimit;
	private int upperLimit;
	private ArrayList<MembershipFunction> mMembershipFuncs;
	private int mfCount;
	public Variable(String name, int type, int ll, int ul, ArrayList<MembershipFunction> memFunc, int mfCount){
		this.name = name;
		this.type = type;
		this.lowerLimit = ll;
		this.upperLimit = ul;
		this.mMembershipFuncs = memFunc;
		this.mfCount = mfCount;
	}
	
	public int getMFCount(){
		return this.mfCount;
	}
	public void setMFCount(int mfcount){
		this.mfCount = mfcount;
	}
	
	public ArrayList<MembershipFunction> getMembershipFuncs(){
		return this.mMembershipFuncs;
	}
	public void setMembershipFunc(ArrayList<MembershipFunction> mf){
		this.mMembershipFuncs = mf;
	}
	
	public String getVariableName(){
		return this.name;
	}
	public void setVariableName(String name){
		this.name = name;
	}
	
	public int getVariableType(){
		return this.type;
	}
	public void setVariableType(int t){
		this.type = t;
	}
	
	public int getLowerLimit(){
		return this.lowerLimit;
	}
	public void setLowerLimit(int l){
		this.lowerLimit = l;
 	}
	
	public int getUpperLimit(){
		return this.upperLimit;
	}
	public void setUpperLimit(int u){
		this.upperLimit = u;
	}
//	public boolean equals(Object obj) {
//	    if (obj == null) return false;
//	    if (obj == this) return true;
//	    if (!(obj instance of Vari)) return false;
//	    ArrayTest o = (ArrayTest) obj;
//	    return o.i == this.i;
//	}

}
