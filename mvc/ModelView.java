package com.example.frameworkmvc.mvc;

import android.content.Context;
import android.view.ViewGroup;

import com.example.frameworkmvc.mvc.modelobservers.ModelChanged;
import com.example.frameworkmvc.mvc.modelobservers.ModelDeleted;
import com.example.frameworkmvc.ui.View;

public abstract class ModelView extends View {
	protected Model model = null;
	private ModelChanged modelChangedObserver = null;
	private ModelDeleted modelDeletedObserver = null;
	
	
	/**
	 * Default constructor.
	 * Create a view associated to a context and a resource.
	 * @param c
	 * @param r
	 * @param m
	 */
	public ModelView (Context c, int r, Model m) {
		super(c, r);
		model = m;
	}
	
	/**
	 * Displays the view defined by the resource
	 * id within the parent.
	 * @param p
	 */
	public void render(ViewGroup p, Boolean addToParent) {
		super.render(p, addToParent);
		updateView();
	}
	
	/**
	 * Displays the view as an existing android view.
	 * @param p
	 */
	public void decorate(android.view.View v) {
		super.decorate(v);
		updateView();
	}
	

	
	
	/**
	 * Disposes the view.
	 */
	public void dispose(Boolean removeView) {
		unRegisterObservers();
		super.dispose(removeView);
	}
	
	/**
	 * Unsubscribes model observers.
	 * Must be override in sub classes if additional
	 * observers are used.
	 */
	protected void unRegisterObservers() {
		modelChangedObserver = null;
		modelDeletedObserver = null;
		model.unRegisterModelObserver(Model.MODEL_CHANGED_OBSERVER_KEY);
		model.unRegisterModelObserver(Model.MODEL_DELETED_OBSERVER_KEY);
	}


	/**
	 * Intialize the default model changed
	 * observer.
	 * This involves the call of modelChanged function
	 * every time the model is updated.
	 */
	protected void initModelChangedObserver() {
		modelChangedObserver = new ModelChanged() {
			
			@Override
			public void onModelChanged(Model model) {
				modelChanged();
			}
		};
		model.registerModelObserver(modelChangedObserver, Model.MODEL_CHANGED_OBSERVER_KEY);
	}
	
	/**
	 * Initialize the default model deleted
	 * observer.
	 * modelDeleted function is called when
	 * the model is disposed.
	 */
	protected void initModelDeletedObserver() {
		modelDeletedObserver = new ModelDeleted() {
			
			@Override
			public void onModelDeleted(Model model) {
				modelDeleted();
			}
		};
		model.registerModelObserver(modelDeletedObserver, Model.MODEL_DELETED_OBSERVER_KEY);
	}
	
	/**
	 * Call back function on model changed.
	 */
	protected void modelChanged() {
		updateView();
	}
	
	/**
	 * Call back function on model disposed.
	 */
	protected void modelDeleted() {
		
	}
	
	/**
	 * Abstract function which pull the data
	 * from the model and inserts it in the
	 * displayed content.
	 */
	public abstract void updateView();
}
