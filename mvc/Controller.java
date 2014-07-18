package com.example.frameworkmvc.mvc;

import com.example.frameworkmvc.ui.Component;
import com.example.frameworkmvc.ui.View;

import android.view.ViewGroup;



public abstract class Controller extends Component {
	protected Model model = null;
	
	protected Controller(Model m, View v) {
		super(v);
		model = m;
	}
	
	public abstract void enterView();
}
