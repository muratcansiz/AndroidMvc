package com.example.frameworkmvc.mvc.test;

import com.example.frameworkmvc.mvc.Controller;

import android.content.*;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

public class TestController extends Controller {
	private Button button;
	private SimpleButtonView testView = null;
	private TestModel testModel = null;
	private TestController self = null;
	
	public TestController(TestModel model, Context c) {
		super(model, new SimpleButtonView(model, c));
		testView = (SimpleButtonView) view;
		testModel = (TestModel) model;
	}
	
	@Override
	public void enterView() {
		
		self = this;
		button = testView.getButton();
	}
	
	public void onClick() {
		testModel.increase();
		if (testModel.getCounter() == 10) {
			dispose(true);
		}
	}
	@Override
	public void dispose(Boolean removeView) {
		if (button != null) {
			button = null;
		}
		
		super.dispose(removeView);
	}

}