package a.entity.gus06.sys.filemanagement1.analyze.export.pdf.data;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.io.File;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210201";}


	private Service chooseFile;
	private Service retrieveMd5Set;
	private Service buildPrinter;

	public EntityImpl() throws Exception
	{
		chooseFile = Outside.service(this,"gus06.file.choose.save.file.ext.csv.en");
		retrieveMd5Set = Outside.service(this,"gus06.sys.filemanagement1.tool.pdf.retrieve.md5set");
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
		
		Set md5Set = (Set) retrieveMd5Set.t(engine);
		List md5List = new ArrayList(md5Set);
		Collections.sort(md5List);
		int nb = md5List.size();
		
		p.println("starting PDF data export");
		p.println("output file: "+outputFile);
		p.println("PDF nb: "+nb);
		
		PrintStream printer = (PrintStream) buildPrinter.t(outputFile);
		printer.println(getHeader());
		
		for(int i=0;i<nb;i++)
		{
			String md5 = (String) md5List.get(i);
			Map prop = (Map) ((R)engine).r("prop:"+md5);
			if(prop!=null)
			{
				String[] row = propToRow(prop,md5);
				printer.println(row);
			}
			
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
			"Ext",
			"Name0",
			"Title",
			"Author",
			"Page nb",
			"Producer",
			"Subject",
			"ISBN",
			"Creator",
			"MD5"};
	}
	
	private String[] propToRow(Map prop, String md5)
	{
		String ext = get(prop,"ext");
		String name0 = get(prop,"name0");
		String title = get(prop,"pdf.title");
		String author = get(prop,"pdf.author");
		String pagenb = get(prop,"pdf.pagenb");
		String producer = get(prop,"pdf.producer");
		String subject = get(prop,"pdf.subject");
		String isbn = get(prop,"pdf.isbn");
		String creator = get(prop,"pdf.creator");
		
		return new String[]{
			ext,
			name0,
			title,
			author,
			pagenb,
			producer,
			subject,
			isbn,
			creator,
			md5};
	}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return "";
		return (String) map.get(key);
	}
}