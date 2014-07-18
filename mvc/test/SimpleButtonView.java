package com.example.frameworkmvc.mvc.test;

import android.content.*;
import android.util.Log;
import android.widget.Button;

import com.example.frameworkmvc.mvc.ModelView;

public class SimpleButtonView extends ModelView {
	private Button button = null;
	private static Integer viewId = 1000;
	private static Integer buttonId = 0;
	
	SimpleButtonView (TestModel model, Context c) {
		super(c, com.example.frameworkmvc.R.layout.buttonview, model);
	}
	
	public Button getButton() {
		return button;
	}

	@Override
	public void updateView() {
		TestModel testModel = (TestModel) model;
		button.setText(testModel.getCounter() + "");
	}

	protected Boolean canDecorate(android.view.View v) {
		if (v instanceof android.widget.Button) {
			return true;
		}
		Log.e("CanDecorate", "Unexpected view.");
		return false;
	}
	
	@Override
	protected void enterView() {
		button = (Button) content;
		initModelChangedObserver();
	}
	
	@Override
	public void dispose(Boolean removeView) {
		super.dispose(removeView);
	}
}
