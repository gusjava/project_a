package a.entity.gus06.appli.vindinium.map.combobox;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComboBox;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170917";}

	public static final long LAPSE = 500;
	public static final String EXT = "txt";
	

	private Service whiteCombo;
	private File storeDir;
	private int fileNumber;
	private JComboBox combo;
	private Timer timer;
	private TimerTask task;
	
	
	public EntityImpl() throws Exception
	{
		whiteCombo = Outside.service(this,"gus06.swing.combobox.cust.white");
		storeDir = (File) Outside.resource(this,"defaultdir");
		
		if(storeDir==null) throw new Exception("StoreDir is null");
		
		combo = new JComboBox();
		whiteCombo.p(combo);
		
		refresh();
		
		task = new TimerTask(){public void run() {check();}};

		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
	}


	public Object g() throws Exception
	{return combo;}

	
	
	private void check()
	{
		if(storeDir.list().length!=fileNumber)
			refresh();
	}
	
	
	private void refresh()
	{
		File[] f = storeDir.listFiles();
		fileNumber = f!=null ? f.length : 0;
		
		String selected = (String) combo.getSelectedItem();
		
		combo.removeAllItems();
		for(int i=0;i<fileNumber;i++)
		{
			String name = findName(f[i]);
			combo.addItem(name);
		}
		
		if(selected!=null) combo.setSelectedItem(selected);
	}
	
	
	private String findName(File file)
	{
		String n = file.getName();
		return n.substring(0,n.length()-EXT.length()-1);
	}
}
