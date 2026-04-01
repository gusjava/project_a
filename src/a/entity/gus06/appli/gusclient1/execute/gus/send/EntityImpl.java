package a.entity.gus06.appli.gusclient1.execute.gus.send;

import a.framework.*;
import javax.swing.JOptionPane;
import java.io.PrintStream;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20141016";}
	

	private Service input;
	private Service sender;
	private Service putFile;
	private Service getFile;
	private Service fileChooser_open;
	private Service fileChooser_save;
	
	private PrintStream out;
	


	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
		sender = Outside.service(this,"gus06.appli.gusclient1.sender");
		putFile = Outside.service(this,"gus06.appli.gusclient1.sender.file.put");
		getFile = Outside.service(this,"gus06.appli.gusclient1.sender.file.get");
		fileChooser_open = Outside.service(this,"gus06.file.choose.open.file");
		fileChooser_save = Outside.service(this,"gus06.file.choose.save.file");
		
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	
	public void e() throws Exception
	{
		String cmd = (String) input.t("Type command:");
		if(cmd==null) return;
		
		if(cmd.startsWith("put:"))
		{putFile(cmd.substring(4));return;}
		
		if(cmd.startsWith("get:"))
		{getFile(cmd.substring(4));return;}
		
		if(cmd.contains(":"))
		{send(cmd);return;}
		
		JOptionPane.showMessageDialog(null,"Invalid command: "+cmd);
	}
	
	
	
	private void putFile(String path) throws Exception
	{
		File file = (File) fileChooser_open.g();
		if(file==null) return;
		if(path.equals("")) path = file.getName();
		putFile.v(path,file);
	}
	
	
	private void getFile(String path) throws Exception
	{
		File file = (File) fileChooser_save.g();
		if(file==null) return;
		if(path.equals("")) path = file.getName();
		getFile.v(path,file);
	}
	
	
	
	private void send(String cmd) throws Exception
	{
		String res = (String) sender.t(cmd);
		out.print(res);
	}
}
