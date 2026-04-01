package a.entity.gus06.file.perform.display.infos1;

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
		File file = (File) obj;
		if(!file.isFile()) throw new Exception("Invalid file: "+file);
		
		String message = buildMessage(file);
		JOptionPane.showMessageDialog(null,message);
	}
	
	
	
	private String buildMessage(File file)
	{
		StringBuffer b = new StringBuffer();
		
		b.append("Location: "+file.getParent()+"\n");
		b.append("File name: "+file.getName()+"\n");
		b.append("File size: "+file.length()+"\n");
		
		return b.toString();
	}
	
}
