package com.example.frameworkmvc.ui;

import android.util.Log;
import android.view.ViewGroup;

public abstract class Component {
	protected View view = null;
	private Boolean isRendered = false;
	private Boolean isDecorated = false;
	
	protected Component(View v) {
		view = v;
	}
	
	public View getView() {return view;}
	
	public void render(ViewGroup p, Boolean addToParent) {
		if (isRendered || isDecorated) {
			Log.e("Component", "already displayed !");
			return;
		}
		if (p != null) {
			view.render(p, addToParent);
			isRendered = true;
			enterView();
		}
		
	}
	
	public void decorate(android.view.View v) {
		if (isDecorated || isRendered) {
			Log.e("Component", "already displayed !");
			return;
		}
		view.decorate(v);
		isDecorated = true;
		enterView();
	}
	
	public abstract void enterView();
	
	public void dispose(Boolean removeView) {
		if (isRendered || isDecorated) {
			view.dispose(removeView);
			isRendered = false;
			isDecorated = false;
		}
	}
}
