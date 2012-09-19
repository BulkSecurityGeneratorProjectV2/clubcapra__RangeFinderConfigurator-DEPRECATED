package ca.etsmtl.capra.tool.range.command;

public interface InterfaceCommand {
	
	//Command pour configuration du port
	public void resetConfig();
	public void sendPassword();
	public void saveConfig();
	public void setBaudrate();
	public void openSocket(String adresse);
	public void close();
	//Command pour configurer la reception des données;
	public void requestData();
	
	
	

}
