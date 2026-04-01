package a.entity.gus06.thread.handle.showstacktrace;

import a.framework.*;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140730";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		Thread t = (Thread) obj;
    	
		JTextArea area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
    	
		printInfos(area,t);
    	
		JFrame frame = new JFrame("Stacktrace for thread ["+t.getName()+"]");
		frame.setContentPane(new JScrollPane(area));
		frame.setSize(600,300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
	private void printInfos(JTextArea area, Thread t)
	{
		StackTraceElement[] ste = t.getStackTrace();
		for(int i=0;i<ste.length;i++)
		print(area,toString(ste[i]));
	}
    
    
	private void print(JTextArea area, String line)
	{area.append(line+"\n");}
    
    
    
	private String toString(StackTraceElement ste)
	{return ste.getClassName()+"@"+ste.getMethodName()+" ("+ste.getFileName()+":"+ste.getLineNumber()+")";}
}
