package ca.etsmtl.capra.tool.range.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBar extends JMenuBar implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Properties files
	private Properties props;
	private String adresse;
	
	//Menu Fichier avec ses sous-menus
	private JMenu fichier;
		private JMenuItem ouvrir;
		private JMenuItem fermer;
		private JMenuItem log;
	
	//Menu Port avec ses sous-menus
	private JMenu socket;
		private JMenuItem infoSocket;
		private JMenuItem connectSocket;
		private JMenuItem confSocket;
		
	//Menu Vue avec ses sous-menus
	private JMenu vue;
		private JMenuItem ouvrirData;
		
	public MenuBar(){
		//properties files
		props=new Properties();
		//initilisation menu
		fichier=new JMenu("Fichier");
		socket=new JMenu("Socket");
		vue=new JMenu("Vue data");
		this.add(fichier);
		this.add(socket);
		this.add(vue);
		
		//initialisation menu fichier
		ouvrir=new JMenuItem("Ouvrir mesure");
		ouvrir.setActionCommand("ouvrir");
		log=new JMenuItem("Enregistre log");
		fermer=new JMenuItem("Fermer");
		fermer.setActionCommand("fermer");
		fichier.add(ouvrir);
		fichier.add(log);
		fichier.add(fermer);
		
		//initialisation menu port
		infoSocket=new JMenuItem("Info socket");
		connectSocket=new JMenuItem("Connect Socket");
		confSocket=new JMenuItem("Editer adresse");
		
		infoSocket.setActionCommand("infoSocket");
		connectSocket.setActionCommand("connectSocket");
		confSocket.setActionCommand("confScket");
		
		infoSocket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "IP:"+adresse+"\nPort:2111", 
					      "Info Adresse IP", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		confSocket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setConfig();
			}
		});
		
		
		
		socket.add(infoSocket);
		socket.add(connectSocket);
		socket.add(confSocket);
		
		//initialisation vue
		ouvrirData=new JMenuItem("ouvrir le data");
		ouvrirData.setActionCommand("ouvrirData");
		vue.add(ouvrirData);	
		 loadConfig();
		
		
	}
	
	public void loadConfig(){
		try {
			props.load(new FileInputStream("socket.properties"));
			adresse=props.getProperty("adresse");
		} catch (FileNotFoundException e) {
			setConfig();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void setConfig(){
		do{
			adresse=(String)JOptionPane.showInputDialog(this,"Entrez la nouvelle adresse IP","Changement d'adresse IP",JOptionPane.QUESTION_MESSAGE,null,null,adresse);
		}while(adresse==null);
		
		props = new Properties();
		 
    	try {
    		//set the properties value
    		props.setProperty("adresse", adresse);
    		    		//save properties to project root folder
    		props.store(new FileOutputStream("socket.properties"), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}
	
	public String getAdresse(){
		return adresse;
	}
	
	public void addActionListener(ActionListener l){
		ouvrir.addActionListener(l);
		fermer.addActionListener(l);
		ouvrirData.addActionListener(l);
		connectSocket.addActionListener(l);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	

}
