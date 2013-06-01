package ca.etsmtl.capra.tool.range.model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;
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
	//private Vector<String> reponseEtEvent;
	private Vector<String> confirmation;
	private String data;
	//private int Status;
	private final double ANGLE_DEPART=-2.35619449;
	private int degreesToHide = 35;
	private long lastUpdate = System.currentTimeMillis();
	
	
	public EventRange(int degreesToHide){
		telegrams=Telegrams.getInstance();
		//reponseEtEvent=new Vector<String>();
		confirmation=new Vector<String>();
		positionRad=new int[541];
		io=new SocketIO();
		io.addObserver(this);
		this.degreesToHide = degreesToHide;
	}
	
	public boolean connectRF(String adresse){
		return io.connect(adresse);
	}
	
	private void addTelegrams(String[] telegram){
		io.send(telegram[0]);
		confirmation.add(telegram[1]);
		
	}
	
	public synchronized void checkConfirmation(){
		String[] datas=data.split(" ");
		if(datas[1].equals("LMDscandata"))
				analysePosition(datas);
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
	
	private synchronized void analysePosition(String[] data){
		//System.out.println(data.length);
		if(data.length>541)
			for(int i=0;i<541;i++){
				positionRad[i]=Integer.parseInt(data[26+i],16);
			}
		setPositionXY(positionRad);
		
		setChanged();
		notifyObservers();
	}
	
	public void getStatus(){
		addTelegrams(telegrams.getStatus());		
	}
	
	public void close(){
		io.close();
	}
	
	public void startMesure(){
		addTelegrams(telegrams.startMesure());
		
	}
	
	
	public void getScanData(){
		addTelegrams(telegrams.getScanData(1));
	}
	
	public void stopScanData(){
		addTelegrams(telegrams.getScanData(0));
	}
	
	public void stopMesure(){
		String[] telegram=telegrams.stopMesure();
		io.send(telegram[0]);
		confirmation.add(telegram[1]);
	}
	
	public void setPositionXY(int[] positionRad){
		Vector<Point2D> points = new Vector<Point2D>();
		for(int laser= (0 + degreesToHide);laser <= (539 - degreesToHide);laser++){
			Point2D point=new Point();
			int invertedLaser = 541 - 2 - laser;
			point.setLocation(positionRad[invertedLaser]*Math.cos(invertedLaser*0.00872664626+ANGLE_DEPART),
						positionRad[invertedLaser]*Math.sin(invertedLaser*0.00872664626+ANGLE_DEPART));
			if(point.getX() == 0 && point.getY() == 0){
				point.setLocation(10000, 10000);
				
			}
			points.add(point);
		}
		positionXY=points;		
	}
	
	
	public List<Point2D> getPositionXY(){
		return positionXY;
	}
	
	public int[] getPositonRad(){
		return positionRad;
	}
	
	public long getLastUpdate()
	{
		return lastUpdate;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
	
		data=io.getReponse();
		lastUpdate=io.getLastUpdate();
		checkConfirmation();
	}

}
