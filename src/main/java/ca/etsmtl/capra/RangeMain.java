package ca.etsmtl.capra;

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
		gui=new RangeGUIMain();
		eventRange=new EventRange(25);
		eventRange.addObserver(gui);
		interfaceCommand=new CommandRange(gui, eventRange);
		
	}

}
