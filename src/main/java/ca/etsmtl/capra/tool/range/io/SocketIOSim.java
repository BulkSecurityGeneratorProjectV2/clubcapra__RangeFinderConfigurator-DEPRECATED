package ca.etsmtl.capra.tool.range.io;

public class SocketIOSim extends InterfaceIO{

	@Override
	public void close() {
		
	}

	@Override
	public String getReponse() {
		return null;
	}

	@Override
	public void send(String cmd) {
		
	}

	@Override
	public boolean connect(String adresse) {
		return true;
	}
	
	public synchronized long getLastUpdate()
	{
		return System.currentTimeMillis();
	}

}
