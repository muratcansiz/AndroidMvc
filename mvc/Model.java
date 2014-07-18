package com.example.frameworkmvc.mvc;

import java.lang.String;

import com.example.frameworkmvc.mvc.modelobservers.*;

import java.util.*;


public abstract  class Model {
	public static final String MODEL_CHANGED_OBSERVER_KEY = "ModelChanged";
	public static final String MODEL_DELETED_OBSERVER_KEY = "ModelDeleted";
	
	/**
	 * Map of registered model observers
	 */
	private HashMap<String, ArrayList<ModelObserver> > modelObservers;
	/**
	 * Information about model's disposition
	 */
	private Boolean isDisposed = false;
	
	public Model() {
		modelObservers = new HashMap<String, ArrayList<ModelObserver> >();
	}

	/**
	 * Whether the model has been disposed
	 * @return
	 */
	public Boolean isDisposed() {
		return isDisposed;
	}
	
	/**
	 * Registers a model observer
	 * @param observer The observer
	 */
	public void registerModelObserver(ModelObserver observer, String key) {
		if (observer != null) {
			ArrayList<ModelObserver> list = modelObservers.get(key);
			if (list == null) {
				list = new ArrayList<ModelObserver>();
				modelObservers.put(key, list);
			}
			list.add(observer);
		}
	}
	/**
	 * Unregisters a model observer
	 * @param observer The observer
	 */
	public void unRegisterModelObserver(String key) {
		if (key != null) {
			ArrayList<ModelObserver> list = modelObservers.get(key);
			if (list == null) return;
			ListIterator<ModelObserver> liter = list.listIterator();
			while (liter.hasNext()) {
				ModelObserver ob = liter.next();
				ob = null;
			}
			modelObservers.remove(key);
		}
	}
	
	/**
	 * Disposes the model.
	 * Mainly used to unsubscribe observers
	 * and free data.
	 * 
	 * This function leads the model to an
	 * useless state of its life cycle.
	 */
	public final void dispose() {
		disposeData();
		//trigger observer
		modelDisposed();
		isDisposed = true;
		//unsubscribes all the observers
		Iterator ite = modelObservers.entrySet().iterator();
		while(ite.hasNext()) {
			Map.Entry<String, ArrayList<ModelObserver> > pair = (Map.Entry<String, ArrayList<ModelObserver> >) ite.next();
			pair.getValue().clear();
		}
		modelObservers.clear();
	}
	
	/**
	 * Triggers model observers
	 * 
	 * Note: the call to this function is delegated
	 * to sub classes (which ensures better control).
	 */
	protected void modelChanged() {
		ArrayList<ModelObserver> modelChangedObservers = modelObservers.get(MODEL_CHANGED_OBSERVER_KEY);
		if (modelChangedObservers == null) return;
		ListIterator<ModelObserver> liter = modelChangedObservers.listIterator();
		while (liter.hasNext()) {
			ModelObserver ob = liter.next();
			ob.updateModel(this);
		}
	}
	/**
	 * Triggers model deleted observers
	 */
	private void modelDisposed() {
		// trigger all model changed
	}
	
	//data handling
	public abstract String serialize();
	public abstract void deSerialize(String src);
	
	/**
	 * Frees model's data.
	 * Should be override in subclasses.
	 */
	public void disposeData() {
		
	}
}
