package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_b.copy.perform2;

import a.framework.*;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240412";}


	private Service toClipboard;

	public EntityImpl() throws Exception
	{
		toClipboard = Outside.service(this,"gus.y.clipboard1.files");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextArea comp = (JTextArea) o[0];
		String text = (String) o[1];
		
		String[] lines = text.split("\n");
		List files = linesToFiles(lines);
		if(!files.isEmpty()) toClipboard.p(files);
	}
	
	private List linesToFiles(String[] lines) throws Exception
	{
		List files = new ArrayList();
		for(String line:lines) 
		{
			File file = lineToFile(line.trim());
			if(file!=null) files.add(file);
		}
		return files;
	}
	
	private File lineToFile(String line)
	{
		if(line.equals("")) return null;
		if(line.equals(".")) return null;
		
		File f = new File(line);
		if(f.exists()) return f;
		return null;
	}
}
