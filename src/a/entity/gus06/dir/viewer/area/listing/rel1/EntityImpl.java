package a.entity.gus06.dir.viewer.area.listing.rel1;

import a.framework.*;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import javax.swing.JTextArea;
import java.awt.Insets;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class EntityImpl extends S1 implements Entity, I, P, R {

	public String creationDate() {return "20180427";}
	
	public static final long LAPSE = 200;


	private Service dirToPaths;
	private Service listingToString;
	private Service settext;
	
	private JLabel label;
	private JPanel panel;
	private JTextArea area;
	
	private File dir;
	private Timer timer;
	private TimerTask task;


	
	public EntityImpl() throws Exception
	{
		dirToPaths = Outside.service(this,"gus06.dir.listing.dirtopaths.relpath1");
		listingToString = Outside.service(this,"gus06.tostring.list.join.n");
		settext = Outside.service(this,"gus06.swing.comp.settext1");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(3,3,3,3));
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		
		timer = new Timer("TIMER_"+getClass().getName());
		task = new TimerTask() {public void run() {update();}};
		timer.schedule(task, new Date(), LAPSE);
	}



	public Object i() throws Exception
	{return panel;}
	
	
	
	public synchronized void p(Object obj) throws Exception
	{
		dir = (File) obj;
		label.setText(pathDisplay());
	}
	
	
	private synchronized void update()
	{
		try
		{
			if(dir==null || !dir.isDirectory())
			{setText("");return;}
			
			List listing = (List) dirToPaths.t(dir);
			String s = (String) listingToString.t(listing);
			setText(s);
		}
		catch(Exception e)
		{Outside.err(this,"update()",e);}
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("area")) return area;
		if(key.equals("keys")) return new String[]{"area"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	private String pathDisplay()
	{
		if(dir==null) return " ";
		if(dir.isDirectory()) return dir.getAbsolutePath()+" [dir]";
		if(dir.isFile()) return dir.getAbsolutePath()+" [file]";
		return dir.getAbsolutePath()+" [not found]";
	}
	
	
	private void setText(String s) throws Exception
	{
		settext.p(new Object[]{area,s});
	}
}
