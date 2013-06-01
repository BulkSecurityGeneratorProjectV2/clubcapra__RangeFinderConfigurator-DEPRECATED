package ca.etsmtl.capra.tool.range.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class SocketIO extends InterfaceIO{
	
	private Socket socketRF;
	private PrintWriter out;
	private InputThread inputThread;
	private String reponse;
	private long lastUpdate;
	
	public SocketIO(){
		
		
		
	}
	public boolean connect(String adresse){
		try {	
			try{
				System.out.println("Trying to connect to rangefinder...");
				socketRF=new Socket(adresse,2111);
			}
			catch(ConnectException e){
				System.out.println("socket connection refusé!!");
			}
			if(socketRF!=null){
				inputThread=new InputThread(socketRF.getInputStream());
				out=new PrintWriter(socketRF.getOutputStream());
				System.out.println(socketRF.toString());
	
				// use Executors instead of threads
				Executors.newSingleThreadExecutor().execute(inputThread);
				return true;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	/*************
	 * Classe interne pour gerer le thread d'entrée du socket
	 * @author novae
	 *
	 */
	
	private class InputThread implements Runnable{
		private final AtomicBoolean run;
		private final BufferedReader in;
		private StringBuilder sb=new StringBuilder();
		private long timestamp;
		
		public InputThread(InputStream in){
			
			this.in=new BufferedReader(new InputStreamReader(in));
			run=new AtomicBoolean(true);
		}
		
		@Override
		public void run() {
			timestamp = System.currentTimeMillis();
			
			// reading loop
			while(run.get()){
				try { 
					char buffer=(char)in.read();
					sb.append(buffer);
					if(buffer==((char)(0x03))){
						setReponse(sb.toString(), timestamp);
						timestamp = System.currentTimeMillis();
						sb=new StringBuilder();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}			
			}
			
			//close socket
			try {
				socketRF.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void setRun(boolean run){
			this.run.set(run);
		}
	}
	
	
	
	public void close(){
		if(socketRF!=null)
			inputThread.setRun(false);
	}
	
	
	public synchronized String getReponse(){
		return reponse;
	}

	public synchronized long getLastUpdate()
	{
		return lastUpdate;
	}
	
	private synchronized void setReponse(String reponse, long timestamp){
			//System.out.println(reponse);
			this.reponse=reponse;
			this.lastUpdate=timestamp;
			setChanged();
			notifyObservers();		
	}	
	
	public void send(String cmd){
			System.out.println(cmd);
			if(socketRF!=null){
				out.print(cmd);
				out.flush();	
			}
	}	

}
