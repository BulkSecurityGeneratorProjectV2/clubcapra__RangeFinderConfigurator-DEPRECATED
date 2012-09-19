package ca.etsmtl.capra.tool.range;

import ca.etsmtl.capra.tool.range.command.CommandRange;
import ca.etsmtl.capra.tool.range.command.InterfaceCommand;
import ca.etsmtl.capra.tool.range.gui.RangeGUIMain;
import ca.etsmtl.capra.tool.range.model.EventRange;

public class RangeMain {
	private static RangeGUIMain gui;
	@SuppressWarnings("unused")
	private static InterfaceCommand interfaceCommand;
	private static EventRange eventRange;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gui=new RangeGUIMain();
		eventRange=new EventRange();
		eventRange.addObserver(gui);
		interfaceCommand=new CommandRange(gui, eventRange);
		
	}

}
