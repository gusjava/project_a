package a.entity.gus06.dir.perform.display.infos1;

import a.framework.*;
import java.io.File;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151004";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(!dir.isDirectory()) throw new Exception("Invalid directory: "+dir);
		
		String message = buildMessage(dir);
		JOptionPane.showMessageDialog(null,message);
	}
	
	
	
	private String buildMessage(File dir)
	{
		StringBuffer b = new StringBuffer();
		
		b.append("Location: "+dir.getParent()+"\n");
		b.append("Dir name: "+dir.getName()+"\n");
		b.append("Dir children number: "+dir.listFiles().length+"\n");
		
		return b.toString();
	}
	
}
