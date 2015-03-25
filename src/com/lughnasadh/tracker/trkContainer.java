package com.lughnasadh.tracker;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.lughnasadh.tracker.config.configMgr;
import com.lughnasadh.tracker.ui.trkDisplayMgr;

public class trkContainer {

	private static trkContainer instance;
	private static configMgr configs;
	private static trkDisplayMgr frame;
	
	protected trkContainer() {
		//just to defeat instantiation
	}
	
	public static trkContainer getInstance() {
		if(instance == null) {
			instance = new trkContainer();
		
			//Basic instantiation stuff
			
			//Configuration data
		    configs = configMgr.getInstance();
	
			
			
		    try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		     frame = trkDisplayMgr.getInstance();
		    
		}
		return instance;
	}
	
	public void showFrame() {
	    
	    frame.setVisible(true);

	}
}
