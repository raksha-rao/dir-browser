package filesys.client;

import java.io.Serializable;

public class Files implements Serializable {
	
	String parent;
	String[] child;
	
	public Files()
	{
		
	}
	
	public Files(String par, String[] ch)
	{
		this.parent=par;
	
		this.child=ch;
	}
	
	public String getParent()
	{
		
		return this.parent;
	}
	
	public String[] getChild()
	{
		return this.child;
		
	}
	
	public void setString(String par,String[] ch)
	{
		this.parent=par;
		this.child=ch;
	}
}
