package com.example.frameworkmvc.mvc.modelobservers;

import com.example.frameworkmvc.mvc.Model;

public abstract class ModelDeleted extends ModelObserver {

	@Override
	public void updateModel(Model model) {
		onModelDeleted(model);
	}
	
	public abstract void onModelDeleted(Model model);

}
