package com.example.frameworkmvc.ui;

import java.util.*;

import com.example.frameworkmvc.mvc.Controller;
import com.example.frameworkmvc.ui.Component;
import com.example.frameworkmvc.ui.View;

import android.content.Context;
import android.view.ViewGroup;

public class Container extends Component {
	private ViewGroup container;
	List<Component> childs = null;
	
	public Container(int res, Context c) {
		super(new View(c, res));
		childs = new ArrayList<Component>();
	}
	
	public void addChild(Component c) {
		c.render(container, true);
		childs.add(c);
	}
	
	public void removeChild(Component c) {
		c.dispose(true);
		childs.remove(c);
	}
	
	@Override
	public void dispose(Boolean removeView) {
		Iterator<Component> iter = childs.listIterator();
		while(iter.hasNext()) {
			iter.next().dispose(removeView);
		}
		super.dispose(removeView);
	}
	
	@Override
	public void render(ViewGroup vg, Boolean addToParent) {
		super.render(vg, addToParent);
		if (!(view.getAndroidView() instanceof android.view.ViewGroup)) {
			//throw....
		}
		container = (ViewGroup)view.getAndroidView();
	}
	
	@Override
	public void enterView(){
		
	}
	
}
