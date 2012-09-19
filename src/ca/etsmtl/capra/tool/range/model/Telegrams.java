package ca.etsmtl.capra.tool.range.model;

/**
 * @author novae
 *
 */
public class Telegrams {
	private static Telegrams telegrams;
	
	private final char START=0x02;
	private final char END=0x03;
	private final String SPC=" ";
	private final String[] TYPE={"sMN","sAN","sRN","sRA","sEN","sRA","sSN","sEA"};
	
	
	//Les commands
	private final String START_MESURE="LMCstartmeas";
	private final String STOP_MESURE="LMCstopmeas";
	private final String STATUS="STlms";
	private final String GET_DATA="LMDscandata";
	//private final String GET_CONF="LMPscancfg";
	
	private Telegrams(){}
	
	public static Telegrams getInstance(){
		if(telegrams==null)
			telegrams=new Telegrams();
		return telegrams;
	}
	//Structure generale des commands et des response
	
	//Structure des commands
	private String getTelegram(String cmd,String type){
		return START+type+SPC+cmd+END;		
	}
	
	private String getTelegram(String cmd,String type, int boolValue){
		return START+type+SPC+cmd+SPC+boolValue+END;
	}
	
//	//Structure des reponses
//	private String getResponse(String resp,String type,String[] data){
//		String response=START+type+SPC+resp;
//		for(String elem:data)
//			response+=SPC+elem;
//		response+=END;
//		return response;
//	}
//	
//	private String getResponse(String resp,String type){
//		String response=START+type+SPC+resp+SPC+0+END;
//		return response;
//	}
//	
//	private String getResponse(String resp,String type,int boolValue){
//		String response=START+type+SPC+resp+SPC+boolValue+END;
//		return response;
//	}
//	
//	//Construction des chaines des commandes et de reponses
//	private String[] getTelegram(String cmd,String typeCmd,String typeRep,String[] data){
//		String[] telegram={getCommand(cmd,typeCmd),getResponse(cmd,typeRep,data)};
//		return telegram;
//		
//	}
//	
//	private String[] getTelegram(String cmd,String typeCmd,String typeRep){
//		String[] telegram={getCommand(cmd,typeCmd),getResponse(cmd,typeRep)};
//		return telegram;		
//	}
//	
//	private String[] getTelegram(String cmd,String typeCmd,String typeRep,int boolValue){
//		String[] telegram={getCommand(cmd,typeCmd,boolValue),getResponse(cmd,typeRep,boolValue)};
//		return telegram;		
//	}
	
	
	//Les commandes
	//Start Mesure
	public String startMesure(){
		return getTelegram(START_MESURE, TYPE[0]);
	}	
	
	//Stop Mesure
	public String stopMesure(){
		return getTelegram(STOP_MESURE,TYPE[0]);		
	}	
	
	//get status
	public String getStatus(){
		//String[] data={"7","0","8","00:00:00","8","00.00.00","0","0","0"};
		return getTelegram(STATUS,TYPE[2]);
	}
	
	//get data
	public String getScanData(int boolValue){
		return getTelegram(GET_DATA,TYPE[4],boolValue); 		
	}
	
//	public String getDataEvent(){
//		String[] data={"0","0","0","0","0","0","0","0","0","0","0","0"};
//		
//		return getResponse(GET_DATA, TYPE[6], data);
//	}
	
	
	
}
