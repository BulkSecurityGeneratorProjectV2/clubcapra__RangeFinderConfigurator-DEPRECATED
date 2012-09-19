package ca.etsmtl.capra.tool.range.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ca.etsmtl.capra.tool.range.gui.RangeGUIMain;
import ca.etsmtl.capra.tool.range.model.EventRange;



public class CommandRange implements InterfaceCommand{
	public EventRange eventRange;
	public RangeGUIMain gui;
	public CommandRange(RangeGUIMain gui,EventRange eventRange){
		this.eventRange=eventRange;
		
		this.gui=gui;	
		addListener();
	}
	
	public void addListener(){
		gui.addMenuBarListener(new MenuBarListener());
		
	}
	
	public class MenuBarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("ouvrir")){
				//openPort();	
				startMesure();
			}
			
			if(e.getActionCommand().equals("fermer")){
				stopRequestData();
				close();
				System.exit(0);
			}
			if(e.getActionCommand().equals("ouvrirData")){
				System.out.println("ok");
				requestData();
				
			}
			if(e.getActionCommand().equals("connectSocket")){
				if(gui.getAdresse()!=null){
					openSocket(gui.getAdresse());
					System.out.println("ok socket");
				}
				else
					JOptionPane.showMessageDialog(gui, "L'adresse IP n'est pas configuré", 
						      "Erreur adresse ip", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
	}
	
	@Override
	public void resetConfig() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPassword() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveConfig() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBaudrate() {
		// TODO Auto-generated method stub
	}

	@Override
	public void openSocket(String adresse) {
		// TODO Auto-generated method stub
		eventRange.connectRF(adresse);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		eventRange.close();
	}

	@Override
	public void requestData() {
		// TODO Auto-generated method stub
		eventRange.getScanData();		
	}
	
	public void stopRequestData(){
		eventRange.stopScanData();
	}
	
	public void startMesure(){
		eventRange.startMesure();
	}
	
	public void stopMesure(){
		eventRange.stopMesure();
	}

	

}
