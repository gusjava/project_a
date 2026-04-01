package a.entity.gus06.appli.gusclient1.project.release.chooseversion;

import a.framework.*;
import java.io.File;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141014";}

	public static final String VERSION0 = "1.0";
	public static final String TITLE = "Choose next version:";


	private Service input;
	
	
	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		File[] f = dir.listFiles();
		if(f==null || f.length==0) return VERSION0;
		
		double latest = findLatest(f);
		double next = latest;
		
		String r = (String) input.t(new String[]{TITLE,""+latest});
		if(r==null) return null;
		next = d_(r);
		
		while(next<=latest)
		{
			r = (String) input.t(new String[]{TITLE+" (>"+latest+")",""+latest});
			if(r==null) return null;
			next = d_(r);
		}
		String s = ""+next;
		
		if(!s.contains(".")) s = s+".0";
		return s;
	}
	
	
	
	private double findLatest(File[] ff)
	{
		double v1 = 1.0;
		for(File f:ff)
		{
			double v = d_(f.getName());
			if(v>v1) v1 = v;
		}
		return v1;
	}
	
	
	private double d_(String s)
	{
		try{return Double.parseDouble(s);}
		catch(NumberFormatException e)
		{return -1;}
	}
}
