package ca.etsmtl.capra.tool.range.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TelegramsTest {
   Telegrams telegrams;
   char START=0x02;
   char END=0x03;


   @Before
   public void setUp() throws Exception {
      telegrams=Telegrams.getInstance();
   }

   @Test
   public void testStartMesure(){

      String[] start=telegrams.startMesure();
      assertTrue(start[0].equals(START + "sMN LMCstartmeas" + END));
      assertTrue(start[1].equals(START+"sAN LMCstartmeas 0"+END));

   }

   //Stop Mesure
   @Test
   public void testStopMesure(){
      String[] stop=telegrams.stopMesure();
      assertTrue(stop[0].equals(START+"sMN LMCstopmeas"+END));
      assertTrue(stop[1].equals(START+"sAN LMCstopmeas 0"+END));

   }

   //get status
   @Test
   public void testGetStatus(){
      String[] status=telegrams.getStatus();
      assertTrue(status[0].equals(START+"sRN STlms"+END));
      assertTrue(status[1].equals(START+"sRA STlms 7 0 8 00:00:00 8 00.00.00 0 0 0"+END));
   }

   //get Data
   @Test
   public void testGetScanData(){
      String[] data=telegrams.getScanData(1);
      assertTrue(data[0].equals(START+"sEN LMDscandata 1"+END));
      assertTrue(data[1].equals(START+"sEA LMDscandata 1"+END));
   }

}
