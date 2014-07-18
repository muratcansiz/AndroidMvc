package com.example.frameworkmvc.mvc.modelobservers;

import com.example.frameworkmvc.mvc.Model;


public abstract class ModelChanged extends ModelObserver {
	
	@Override
	public void updateModel(Model model) {
		onModelChanged(model);
	}
	
	public abstract void onModelChanged(Model model);
	
}
