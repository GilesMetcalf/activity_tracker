package com.lughnasadh.tracker.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class configMgr {
	
	public static configMgr instance;
	public static String myDocs;
	public static String[] globals;
	public static List<trkActivity> activities = new ArrayList<trkActivity>();
	
	private static Document dom;
	private static String outFile;
	
	
	protected configMgr() {
		//Exists only to defeat instantiation
	}

	public static configMgr getInstance() {
		
		if(instance == null) {
			instance = new configMgr();

			//Find out where My Documents is
			
			JFileChooser fr = new JFileChooser();
			FileSystemView fw = fr.getFileSystemView();
			myDocs = fw.getDefaultDirectory().getPath();
			String configFile = myDocs + "\\Activities\\configs.cf";
			outFile = myDocs + "\\Activities\\activity.dat";
			
			parseXmlFile(configFile);
		}
		return instance;
		
	}
	
	private static void parseXmlFile(String configFile){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			dom = db.parse(configFile);

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		//get the root element
		Element docEle = dom.getDocumentElement();

		//get a nodelist of elements
		NodeList nl = docEle.getElementsByTagName("activity");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {

				//get the activity element
				Element el = (Element)nl.item(i);
				trkActivity activity = new trkActivity();
				activity.setDescription(getTextValue(el, "description"));
				activity.setTaskID1(getTextValue(el, "taskid1"));
				activity.setTaskID2(getTextValue(el, "taskid2"));
				activity.setTaskID3(getTextValue(el, "taskid3"));
				activity.setId(getTextValue(el, "id"));
				if (getTextValue(el, "enabled").equals("1")) {
					activity.setEnabled(true);
				} else {
					activity.setEnabled(false);
				}
				
				
				//Add it to the array
				activities.add(activity);
				
			}
		}
	}

	//Get text value of a node
	private static String getTextValue(Element el2, String tagName) {
		String textVal = null;
		NodeList nl = el2.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	public void writeValues(String values) {
		
			File file =new File(outFile);
			 
			//if file doesn't exists, then create it
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
			try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outFile, true)));
			    out.println(values);
			    out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public String getTimeStamp() {
		
		//Get the date part
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String timestamp = sdf.format(date);

		//Get the time part
		sdf = new SimpleDateFormat("HH:mm");
		timestamp = timestamp + "," + sdf.format(date) + ",";
		
		return timestamp;
		
	}
	
	
	public String getNewID() {
		//Generate and return a GUID
		return String.valueOf(UUID.randomUUID());
	}
	
	//Getters 
	public static String[] getGlobals() {
		return globals;
	}

	public static List<trkActivity> getActivities() {
		return activities;
	}



	
	
}
