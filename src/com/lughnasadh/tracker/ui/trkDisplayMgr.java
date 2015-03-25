package com.lughnasadh.tracker.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import com.lughnasadh.tracker.config.configMgr;
import com.lughnasadh.tracker.config.trkActivity;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;

public class trkDisplayMgr extends JFrame {

	private static final long serialVersionUID = 5342148741873936222L;
	private static trkDisplayMgr instance;
	private static List<trkActivity> activities;
	private static configMgr configs;
	private static int wMax=0;
	private static int hMax=30;

	protected trkDisplayMgr() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFileMenu.add(mntmExit);
		
		JMenu mnConfigMenu = new JMenu("Config");
		menuBar.add(mnConfigMenu);
		
		JMenuItem mntmGlobals = new JMenuItem("Globals");
		mnConfigMenu.add(mntmGlobals);
		
		JMenuItem mntmActivities = new JMenuItem("Activities");
		mntmActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showConfigPage();
			}
		});
		mnConfigMenu.add(mntmActivities);
		
		JSeparator separator = new JSeparator();
		mnConfigMenu.add(separator);
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mnConfigMenu.add(mntmRefresh);
		

		//Exists only to defeat instantiation
	}	
	
	public static trkDisplayMgr getInstance() {
	
		if(instance == null) {
			instance = new trkDisplayMgr();

			//Configuration data
		    configs = configMgr.getInstance();

			instance.setTitle("Lughnasadh Tracker");
			

			
			JPanel pActList = new JPanel();
			instance.getContentPane().add(pActList, BorderLayout.CENTER);
			pActList.setLayout(new BoxLayout(pActList, BoxLayout.Y_AXIS));
			
			activities = configMgr.getActivities();
			//This is where new activities will be added
			
			ButtonGroup group = new ButtonGroup();
			
			for (int i=0; i<activities.size(); i++) {
				trkActivity act = activities.get(i);
				if (act.isEnabled()) {
				final trkActivityButton btn = new trkActivityButton(act);
				hMax += btn.getHeight();
				if (btn.getWidth() > wMax)
					{wMax = btn.getWidth();}
				group.add(btn);
				pActList.add(btn);
				
				btn.addItemListener(new ItemListener() {
					   public void itemStateChanged(ItemEvent ev) {
					      if(ev.getStateChange()==ItemEvent.SELECTED){
					    	  //Item turned on
					    	  btn.setStartTimeStamp(configs.getTimeStamp());
					    	  
					      } else if(ev.getStateChange()==ItemEvent.DESELECTED){
					        //Item turned off
					    	  
					    	  String oString = btn.getStartTimeStamp()
					    			  + configs.getTimeStamp()
					    			  + btn.getDescription() + ","
					    			  + btn.getTaskID1() + ","
					    			  + btn.getTaskID2() + ","
					    			  + btn.getTaskID3();
					    	  configs.writeValues(oString);

					      }
					   }
					});		
				}
			}


			instance.setSize(wMax, hMax);
			
			instance.pack();
		
		}
		return instance;
	}
	

	public void showConfigPage() {
		trkConfigFrame cFrame = new trkConfigFrame();
		cFrame.setVisible(true);
	}
	
	
}
