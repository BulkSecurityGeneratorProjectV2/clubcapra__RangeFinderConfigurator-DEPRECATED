package ca.etsmtl.capra.tool.range.io;

import java.util.Observable;

public abstract class InterfaceIO extends Observable{
	
	abstract public void connect(String adresse);
	abstract public String getReponse(); 
	abstract public long getLastUpdate();
	abstract public void send(String cmd);
	abstract public void close();
}
