package com.lughnasadh.tracker;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.lughnasadh.tracker.config.configMgr;
import com.lughnasadh.tracker.ui.trkDisplayMgr;


public class tracker {

	static trkContainer container;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trkContainer container = trkContainer.getInstance();
					container.showFrame();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

