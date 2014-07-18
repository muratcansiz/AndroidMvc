package com.example.frameworkmvc.mvc.test;

import com.example.frameworkmvc.mvc.Model;
import java.lang.String;;

public class TestModel extends Model {
	private static final String TEXT_CHANGED_OBSERVER = "TextChanged";
	private Integer counter = 0;
	
	public TestModel(int t) {
		super();
		counter = t;
	}
	
	public Integer getCounter() {
		return counter;
	}
	
	public void setCounter(Integer i) {
		counter = i;
		modelChanged();
	}
	
	public void increase() {
		this.counter++;
		modelChanged();
	}
	
	@Override
	public String serialize() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void deSerialize(String src) {
		// TODO Auto-generated method stub
		this.counter = Integer.parseInt(src);

	}
}
