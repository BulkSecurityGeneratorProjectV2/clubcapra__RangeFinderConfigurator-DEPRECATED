package ca.etsmtl.capra.tool.range.model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import ca.etsmtl.capra.tool.range.io.InterfaceIO;
import ca.etsmtl.capra.tool.range.io.SocketIO;

public class EventRange extends Observable implements Observer{
	private InterfaceIO io;
	private Telegrams telegrams;
	private int[] positionRad;
	private Vector<Point2D> positionXY;
	private final double ANGLE_DEPART=-0.785398163;
	private String data;
	//private int Status;
	
	
	public EventRange(){
		telegrams=Telegrams.getInstance();
		positionRad=new int[541];
		io=new SocketIO();
		io.addObserver(this);
	}
	
	public void connectRF(String adresse){
		io.connect(adresse);
	}	
	
	public synchronized void checkResponse(){
		String[] datas=data.split(" ");
		if(datas[1].equals("LMDscandata"))
				analyseDatagram(datas);
		/*
		for(String resp:reponseEtEvent){
			String[] data=resp.split(" ");
			
			else{
				for(String conf:confirmation){
					String[] confs=conf.split(" ");
					if(confs[1].equals(data[1])){
						System.out.println(conf+":confirmer");
						confirmation.remove(conf);
					}
				}
			}
		}*/
	}
	
	private synchronized void analyseDatagram(String[] data){
		//System.out.println(data.length);
		if(data.length>541)
			for(int i=150;i<200;i++){
				positionRad[i]=Integer.parseInt(data[26+i],16);
			}
		setChanged();
		notifyObservers();
	}
	
	public void getStatus(){
		telegrams.getStatus();		
	}
	
	public void close(){
		io.close();
	}
	
	public void startMesure(){
		telegrams.startMesure();
		
	}
	
	public void getScanData(){
		telegrams.getScanData(1);
	}
	
	public void stopScanData(){
		telegrams.getScanData(0);
	}
	
	public void stopMesure(){
		String telegram=telegrams.stopMesure();
		io.send(telegram);
		//confirmation.add(telegram[1]);
	}
	
	public  void setPositionXY(int[] positionRad){
		Vector<Point2D> points = new Vector<Point2D>();
		for(int i=150;i<200;i++){
			Point2D point=new Point();
			point.setLocation(positionRad[i]*Math.cos(i*0.00872664626+ANGLE_DEPART),positionRad[i]*Math.sin(i*0.00872664626+ANGLE_DEPART));
			points.add(point);
		}
		positionXY=points;		
		System.out.println("allo");
	}
	
	
	
	public List<Point2D> getPositionXY(){
		return positionXY;
	}
	
	public int[] getPositonRad(){
		return positionRad;
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		data=io.getReponse();
		checkResponse();
	}

}
