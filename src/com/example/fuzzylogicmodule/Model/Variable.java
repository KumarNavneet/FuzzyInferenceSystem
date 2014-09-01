package com.example.fuzzylogicmodule.Model;

public class Variable {
	private String name;
	private int type;
	private int lowerLimit;
	private int upperLimit;
	
	public Variable(String name, int type, int ll, int ul){
		this.name = name;
		this.type = type;
		this.lowerLimit = ll;
		this.upperLimit = ul;
	}
	
	String getVariableName(){
		return this.name;
	}
	void setVariableName(String name){
		this.name = name;
	}
	
	int getVariableType(){
		return this.type;
	}
	void setVariableType(int t){
		this.type = t;
	}
	
	int getLowerLimit(){
		return this.lowerLimit;
	}
	void setLowerLimit(int l){
		this.lowerLimit = l;
 	}
	
	int getUpperLimit(){
		return this.upperLimit;
	}
	void setUpperLimit(int u){
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
