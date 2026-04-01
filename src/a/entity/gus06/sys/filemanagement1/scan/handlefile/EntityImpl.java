package a.entity.gus06.sys.filemanagement1.scan.handlefile;

import a.framework.*;
import java.io.File;
import java.util.Map;
import javax.swing.JLabel;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201001";}
	

	private Service storeProps;
	private Service storePreview;
	private Service scannable;
	private Service paintLabel;
	private Service eachSecond;
	private Service select;

	public EntityImpl() throws Exception
	{
		storeProps = Outside.service(this,"gus06.sys.filemanagement1.scan.store.properties");
		storePreview = Outside.service(this,"gus06.sys.filemanagement1.scan.store.preview");
		scannable = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.scannable.file");
		paintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		eachSecond = Outside.service(this,"gus06.time.timer.s1");
		select = Outside.service(this,"gus06.dirfile.op.select");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		File file = (File) o[1];
		String[] row = (String[]) o[2];
		JLabel label = (JLabel) o[3];
		
		if(!scannable.f(new Object[]{engine,file})) return false;
		
		Painter painterProp = new Painter(label,"PROP_next",file);
		boolean propCreated = storeProps.f(new Object[]{engine,file,row});
		painterProp.e();
		
		Painter painterPreview = new Painter(label,"IMG_next",file);
		boolean previewCreated = storePreview.f(new Object[]{engine,file,row});
		painterPreview.e();
		
		return propCreated || previewCreated;
	}
	
	
	
	private class Painter extends MouseAdapter implements ActionListener, E
	{
		JLabel label;
		private String iconKey;
		private File file;
		private long startTime;
		private int nbMin = 0;
		
		public Painter(JLabel label, String iconKey, File file) throws Exception
		{
			this.label = label;
			this.iconKey = iconKey;
			this.file = file;
			startTime = System.currentTimeMillis();
			
			label.setFocusable(true);
			label.addMouseListener(this);
			eachSecond.addActionListener(this);
			
			paintLabel.v(iconKey+"#"+file,label);
		}
		
		public void e() throws Exception
		{
			label.removeMouseListener(this);
			eachSecond.removeActionListener(this);
			
			paintLabel(label,null);
		}
		
		public void mousePressed(MouseEvent e)
		{select(file);}
		
		
		public void actionPerformed(ActionEvent e)
		{
			int nbMin1 = findNbMin();
			if(nbMin1>nbMin)
			{
				nbMin = nbMin1;
				paintLabel(label,iconKey+"#"+file+" ("+nbMin+" min)");
			}
		}
		
		private int findNbMin()
		{
			long dt = System.currentTimeMillis()-startTime;
			return (int) (dt/60000L);
		}
	}
	
	
	
	private void paintLabel(JLabel label, String display)
	{
		try{paintLabel.v(display,label);}
		catch(Exception e)
		{Outside.err(this,"paintLabel(JLabel,String)",e);}
	}
	
	private void select(File file)
	{
		try{select.p(file);}
		catch(Exception e)
		{Outside.err(this,"select(File)",e);}
	}

}