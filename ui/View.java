package com.example.frameworkmvc.ui;

import android.view.ViewGroup;
import android.content.Context;


public class View {
	protected android.view.View content = null;
	protected ViewGroup parent = null;
	protected Context context = null;
	protected int resource = -1;
	
	
	/**
	 * Default constructor.
	 * Create a view associated to a context and a resource.
	 * @param c
	 * @param r
	 * @param m
	 */
	public View (Context c, int r) {
		context = c;
		resource = r;
	}
	
	/**
	 * Retrieves the android view of the
	 * framework view.
	 * @return
	 */
	public android.view.View getAndroidView() {
		return content;
	}
	
	/**
	 * Displays the view defined by the resource
	 * id within the parent.
	 * @param p
	 */
	public void render(ViewGroup p, Boolean addToParent) {
		parent = p;
		inflateContent();
		if(addToParent) parent.addView(content);
		enterView();
	}
	
	/**
	 * Set the view by an existing
	 * android view.
	 * @param v
	 */
	public void decorate(android.view.View v) {
		if (v == null || !(canDecorate(v))) return;
		content = v;
		parent = (ViewGroup) v.getParent();
		enterView();
	}
	
	/**
	 * Whether the View can decorate
	 * an android view.
	 * @param v
	 * @return
	 */
	protected Boolean canDecorate(android.view.View v) {
		return true;
	}
	
	protected void enterView() {
		
	}
	
	/**
	 * Disposes the view.
	 */
	public void dispose(Boolean removeView) {
		if (removeView) {
				parent.removeView(content);
		}
		content = null;
	}
	
	/**
	 * Inflate the resource as a view.
	 */
	protected void inflateContent() {
		content = android.view.View.inflate(context, resource, null);
	}
}
