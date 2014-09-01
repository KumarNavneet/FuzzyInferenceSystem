package com.example.fuzzylogicmodule.Model;

import java.util.HashMap;

public class FIS_System {
	private String name;
	private String type;
	private HashMap<String, String> methods;
	
	public FIS_System(String name, String type, HashMap<String, String> hm){
		this.name = name;
		this.type = type;
		this.methods = hm;
	}
	
	String getFISName(){
		return this.name;
	}
	void setFISName(String name){
		this.name = name;
	}
	
	String getFISType(){
		return this.type;
	}
	void setFISType(String t){
		this.type = t;
	}
	
	HashMap<String, String> getMethodsHashMap(){
		return this.methods;
	}
	void setMethodsHashMap(HashMap<String, String> hm){
		this.methods = hm;
	}
}