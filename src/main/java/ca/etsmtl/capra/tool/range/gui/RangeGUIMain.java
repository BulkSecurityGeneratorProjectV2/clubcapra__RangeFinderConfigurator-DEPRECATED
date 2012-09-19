package ca.etsmtl.capra.tool.range.gui;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import ca.etsmtl.capra.tool.range.model.EventRange;

public class RangeGUIMain extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataView dataView;
	private MenuBar menuBar;
	public RangeGUIMain(){
		
		this.setTitle("Range Finder Configurator");
		this.setSize(600, 600);
		dataView=new DataView(550,550);
		menuBar=new MenuBar();
		this.add(dataView);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
		
		
	}
	
	public void addMenuBarListener(ActionListener l){
		menuBar.addActionListener(l);
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		dataView.setPositionXY(((EventRange)arg0).getPositionXY());
	}
	
	public String getAdresse(){
		return menuBar.getAdresse();
	}

	
}
