package a.entity.gus06.dir.runtask.corpus.jar.report.csv.classversions;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.io.PrintStream;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150702";}
	
	public static final String[] COL = new String[] {
		"45","46","47","48","49","50","51","52","53"
	};


	private Service listing;
	private Service formatter;
	private Service extractor;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.jar");
		formatter = Outside.service(this,"gus06.io.printstream.formatter.csv2");
		extractor = Outside.service(this,"gus06.file.jar.classversion.countmap");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File csvFile = new File(dir.getAbsolutePath()+"_versions.csv");
		if(csvFile.exists()) csvFile.delete();
		PrintStream p = null;
		
		try
		{
			p = (PrintStream) formatter.t(csvFile);
		
			p.print("JAR name");
			for(int i=0;i<COL.length;i++) p.print(COL[i]);
			p.println();
		
			File[] ff = (File[]) listing.t(dir);
			if(progress!=null) ((V)progress).v("size",""+ff.length);
		
			for(File f:ff)
			{
				p.print(f.getName());
				printLine(f,p);
			
				if(progress!=null) ((E)progress).e();
				if(interrupt!=null && !interrupt.isEmpty()) break;
			}
		}
		finally
		{
			if(p!=null) p.close();
		}
	}
	
	
	
	
	
	
	private void printLine(File file, PrintStream p)
	{
		Map props = extractVersions(file);
		
		if(props!=null)
		{
			for(int i=0;i<COL.length;i++)
			p.print(info(props,COL[i]));
		}
		else
		{
			for(int i=0;i<COL.length;i++)
			p.print("###");
		}
		p.println();
	}
	
	
	
	private Map extractVersions(File file)
	{
		try{return (Map) extractor.t(file);}
		catch(Exception e){Outside.err(this,"extractVersions(File)",e);}
		return null;
	}
	
	
	
	private String info(Map m, String key)
	{
		if(!m.containsKey(key)) return "0";
		return ""+m.get(key);
	}
}
