package a.entity.gus06.sys.filemanagement1.analyze.export.allocine.data;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.io.File;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210131";}


	private Service chooseFile;
	private Service readProp;
	private Service listing;
	private Service buildPrinter;

	public EntityImpl() throws Exception
	{
		chooseFile = Outside.service(this,"gus06.file.choose.save.file.ext.csv.en");
		readProp = Outside.service(this,"gus.x.file.prop.read");
		listing = Outside.service(this,"gus06.dir.listing0.ext.properties");
		buildPrinter = Outside.service(this,"gus06.io.printstream.formatter.csv1");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		File outputFile = (File) chooseFile.g();
		if(outputFile==null) return;
		
		File dir = (File) ((R)engine).r("dirAllocine");
		File propDir = new File(dir,"code_prop");
		
		File[] ff = (File[]) listing.t(propDir);
		
		p.println("starting allocine data export");
		p.println("output file: "+outputFile);
		p.println("movie nb: "+ff.length);
		
		PrintStream printer = (PrintStream) buildPrinter.t(outputFile);
		printer.println(getHeader());
		for(int i=0;i<ff.length;i++)
		{
			Map prop = (Map) readProp.t(ff[i]);
			String[] row = propToRow(prop);
			printer.println(row);
			
			if(i%100==0) p.println();
			p.print(".");
		}
		
		printer.close();
		
		p.println();
		p.println("export complete");
	}
	
	
	
	private String[] getHeader()
	{
		return new String[]{
			"Code",
			"Title",
			"Genre",
			"Viewer rating",
			"Press rating",
			"Nationality",
			"Production year",
			"Actors",
			"Directors"};
	}
	
	private String[] propToRow(Map prop)
	{
		String code = get(prop,"code");
		String title = get(prop,"title");
		String originaltitle = get(prop,"originaltitle");
		String genre = get(prop,"genre");
		String userrating = get(prop,"userrating");
		String pressrating = get(prop,"pressrating");
		String nationality = get(prop,"nationality");
		String actors = get(prop,"actors");
		String directors = get(prop,"directors");
		String productionyear = get(prop,"productionyear");
		
		if(title==null) title= originaltitle;
		
		return new String[]{
			code,
			title,
			genre,
			userrating,
			pressrating,
			nationality,
			productionyear,
			actors,
			directors};
	}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return "";
		return (String) map.get(key);
	}
}