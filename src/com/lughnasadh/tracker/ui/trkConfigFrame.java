package com.lughnasadh.tracker.ui;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.lughnasadh.tracker.config.configMgr;
import com.lughnasadh.tracker.config.trkActivity;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class trkConfigFrame extends javax.swing.JFrame {
	private JTable tbConfigs;
	private configMgr configs;
	private trkActivity[] acts;
	
	public trkConfigFrame() {
		setTitle("Configurations");
		//Configuration data
	    configs = configMgr.getInstance();
		

		Object[] headers = {"Description", "Task ID 1", "Task ID 2", "Task ID 3", "Enabled", "Delete", "ID"};

		List<trkActivity> activities = configs.getActivities();
		int aLen = activities.size();
		
		acts = new trkActivity[activities.size()];
		acts= activities.toArray(acts);
		
        Object[][] object = new Object[aLen][7];
        
        int i = 0;
        if (activities.size() != 0) {
            for (trkActivity activity : activities) {
                object[i][0] = new String(activity.getDescription());
                object[i][1] = new String(activity.getTaskID1());
                object[i][2] = new String(activity.getTaskID2());
                object[i][3] = new String(activity.getTaskID3());
                object[i][4] = new Boolean(activity.isEnabled());
                object[i][5] = new Boolean(false);
                object[i][6] = new String(activity.getId());
                i++;
            }
        }
		
        DefaultTableModel model = new DefaultTableModel(object, headers);
        
		tbConfigs = new JTable(model)
		{
            private static final long serialVersionUID = 1L;

            @Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }
		};

		
		tbConfigs.getTableHeader().setBackground(Color.LIGHT_GRAY);
		tbConfigs.getTableHeader().setForeground(Color.BLACK);
        Font Tablefont = new Font("Arial", Font.BOLD, 12);
		tbConfigs.getTableHeader().setFont(Tablefont);

		//Hide the ID column
		tbConfigs.removeColumn(tbConfigs.getColumnModel().getColumn(6));
		
		//Here's how to read it later...
		//tbConfigs.getModel().getValueAt(tbConfigs.getSelectedRow(),6);
		
		tbConfigs.getColumnModel().getColumn(0).setPreferredWidth(238);
		tbConfigs.getColumnModel().getColumn(0).setMinWidth(18);
		tbConfigs.getColumnModel().getColumn(4).setPreferredWidth(53);
		tbConfigs.getColumnModel().getColumn(5).setPreferredWidth(45);
		
		tbConfigs.getModel().addTableModelListener(new TableModelListener() {

		      public void tableChanged(TableModelEvent e) {
		    	  TableModel tm = tbConfigs.getModel();
		    	  int iRow = e.getFirstRow();
		    	  String row = (String) tm.getValueAt(iRow, 6);
		    	  System.out.println(row);
		    	  
		    	  trkActivity rAct = findActivityByID(row);
		    	  System.out.println(rAct.getDescription());
		      	}
		    });

		
		tbConfigs.setPreferredScrollableViewportSize(tbConfigs.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(tbConfigs);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

	
        //Bung the buttons on the bottom..
        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        JButton btnCncl = new JButton("Cancel");
        btnCncl.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        JPanel mgPn = new JPanel();
        mgPn.setLayout(new FlowLayout());
        mgPn.add(btnOK);
        mgPn.add(btnCncl);
        getContentPane().add(mgPn, BorderLayout.SOUTH);
      
		this.pack();
		
	}

	private trkActivity findActivityByID (String id) {
		//Find an activity by ID
		trkActivity thisAct = null;
		for (int i=0; i<acts.length; i++) {
			if (acts[i].getId().equals(id)){
				thisAct = acts[i];
			}
		}
		return thisAct;
	}
	
}
