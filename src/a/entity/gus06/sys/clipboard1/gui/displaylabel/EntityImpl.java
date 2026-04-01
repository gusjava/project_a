package a.entity.gus06.sys.clipboard1.gui.displaylabel;

import a.framework.*;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Icon;
import java.util.List;
import java.awt.Image;
import java.util.Date;
import java.io.File;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20151017";}
	
	public static final long LAPSE = 500;


	private Service access;

	private Service getTimer;
	private Timer timer;
	private TimerTask task;
	
	private Icon icon_text;
	private Icon icon_image;
	private Icon icon_file;
	private Icon icon_files;
	
	

	private JLabel label;

	public EntityImpl() throws Exception
	{
		access = Outside.service(this,"gus06.clipboard.access");
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		timer = (Timer) getTimer.g();
		
		icon_text = (Icon) Outside.resource(this,"icon#CLIPBOARD_text");
		icon_image = (Icon) Outside.resource(this,"icon#CLIPBOARD_image");
		icon_file = (Icon) Outside.resource(this,"icon#CLIPBOARD_file");
		icon_files = (Icon) Outside.resource(this,"icon#CLIPBOARD_files");
		
		label = new JLabel("  ");
		//label.setBorder(BorderFactory.createEtchedBorder());
		
		task = new TimerTask(){
			public void run() {updateGui();}
		};
		
		timer.schedule(task,new Date(),LAPSE);
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	
	private void updateGui()
	{
		try
		{
			Object content = access.g();
			
			if(content==null) resetLabel();
			else if(content instanceof String) handleString((String) content);
			else if(content instanceof Image) handleImage((Image) content);
			else if(content instanceof List) handleList((List) content);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	
	
	
	private void resetLabel()
	{
		label.setIcon(null);
		label.setText("  ");
		label.setToolTipText(null);
	}
	
	private void handleString(String text)
	{
		label.setIcon(icon_text);
		label.setText("");
		label.setToolTipText(truncate(text));
	}
	
	private void handleImage(Image image)
	{
		label.setIcon(icon_image);
		label.setText("");
		label.setToolTipText(imageInfos(image));
	}
	
	private void handleList(List list)
	{
		int nb = list.size();
		if(nb==0) {resetLabel();return;}
		if(nb==1) {handleFile((File) list.get(0));return;}
		
		label.setIcon(icon_files);
		label.setText("");
		label.setToolTipText(nb+" elements");
	}
	
	private void handleFile(File file)
	{
		label.setIcon(icon_file);
		label.setText("");
		label.setToolTipText(file.getAbsolutePath());
	}
	
	
	
	
	private String truncate(String s)
	{
		if(s.length()<=20) return s;
		return s.substring(0,20)+"...";
	}
	
	private String imageInfos(Image image)
	{
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		return "["+w+","+h+"]";
	}
}
