package ca.etsmtl.capra.tool.range.tests;

import ca.etsmtl.capra.tool.range.model.Telegrams;
import junit.framework.TestCase;

public class TestTelegrams extends TestCase{
	Telegrams telegrams;
	char START=0x02;
	char END=0x03;
	public void setUp(){
		telegrams=Telegrams.getInstance();
		
	}
	
	
	
	//Start Mesure
	public void testStartMesure(){
		String[] start=telegrams.startMesure();
		assertTrue(start[0].equals(START+"sMN LMCstartmeas"+END));
		assertTrue(start[1].equals(START+"sAN LMCstartmeas 0"+END));
		
	}	
	
	//Stop Mesure
	public void testStopMesure(){
		String[] stop=telegrams.stopMesure();
		assertTrue(stop[0].equals(START+"sMN LMCstopmeas"+END));
		assertTrue(stop[1].equals(START+"sAN LMCstopmeas 0"+END));
				
	}	
	
	//get status
	public void testGetStatus(){
		String[] status=telegrams.getStatus();
		assertTrue(status[0].equals(START+"sRN STlms"+END));
		assertTrue(status[1].equals(START+"sRA STlms 7 0 8 00:00:00 8 00.00.00 0 0 0"+END));		
	}
	
	//get Data
	public void testGetScanData(){
		String[] data=telegrams.getScanData(1);
		assertTrue(data[0].equals(START+"sEN LMDscandata 1"+END));
		assertTrue(data[1].equals(START+"sEA LMDscandata 1"+END));	   
	}
	

}
