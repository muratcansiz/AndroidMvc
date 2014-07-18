package com.example.frameworkmvc.ui;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;

public class ComponentList extends BaseAdapter {
	private List<Component> components = null;
	
	public ComponentList(List<Component> cs) {
		components = cs;
	}
	
	public ComponentList() {
		components = new ArrayList<Component>();
	}
	
	public void addComponent(Component c) {
		if (c != null) components.add(c);
	}

	public void removeComponent(Component c) {
		if (c != null) components.remove(c);
	}
	
	public void decorate(AbsListView view) {
		view.setAdapter(this);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return components.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return components.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Component component = components.get(arg0);
		component.dispose(false);

		component.render(arg2, false);
		Log.d("getView", "render");
		return component.getView().getAndroidView();
	}

}
