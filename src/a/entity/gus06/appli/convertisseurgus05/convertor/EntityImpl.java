package a.entity.gus06.appli.convertisseurgus05.convertor;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150525";}
	
	public static final String ENTITYIMPL = "EntityImpl";
	public static final String GUS06IMPORTLINE = "import a.framework.*;";
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private String today() {return sdf.format(new Date());}

	public static final FileFilter FILTER = new FileFilter() {
		public boolean accept(File f)
		{return f.isFile() && f.getName().endsWith(".java");}
	};
	
	
	
	
	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File inputDir = (File) o[0];
		File outputDir = (File) o[1];
		String newEntityName = (String) o[2];
		
		File[] files = inputDir.listFiles(FILTER);
		for(File file:files)
		handleFile(file,outputDir,newEntityName);
	}
	
	
	
	
	private void handleFile(File file, File outputDir, String newEntityName) throws Exception
	{
		String content = (String) readFile.t(file);
		content = content.replace("\r","");
		
		String[] lines = content.split("\n");
		String name = file.getName();
		String cName = name.substring(0,name.length()-5);
		
		boolean isMain = content.contains("public String getCreationDate()");
		
		String package1 = "gus06.entity."+newEntityName;
		String cName1 = isMain?ENTITYIMPL:convertTypes(cName);
		
		File file1 = new File(outputDir,cName1+".java");
		outputDir.mkdirs();
		PrintStream p1 = new PrintStream(file1);
		
		
		boolean gus06Imported = false;
		
		for(String line:lines)
		{
			if(line.startsWith("import gus05."))
			{
				if(!gus06Imported)
				{
					p1.println(GUS06IMPORTLINE);
					gus06Imported = true;
				}
				continue;
			}
			
			if(isMain)
			{
				line = changeMethod_getName(line);
				line = changeMethod_getCreationDate(line);
			}
			
			line = changeCName(line,cName,cName1);
			line = changePackage(line,package1);
			line = convertTypes(line);
			line = convertMethods(line);
			
			
			if(line!=null)
			{
				line = line.replace("    ","\t");
				p1.println(line);
			}
		}
		
		p1.close();
	}
	
	
	
	
	
	private String changePackage(String m, String package1)
	{
		if(m==null) return null;
		if(m.startsWith("package "))
			return "package "+package1+";";
		return m;
	}
	
	
	
	private String changeCName(String m, String cName, String cName1)
	{
		if(m==null) return null;
		m = m.replace(" "+cName," "+cName1);
		m = m.replace(","+cName,","+cName1);
		return m;
	}
	
	
	
	private String changeMethod_getName(String m)
	{
		if(m==null) return null;
		if(m.contains("public String getName()")) return null;
		return m;
	}
	
	private String changeMethod_getCreationDate(String m)
	{
		if(m==null) return null;
		if(m.contains("public String getCreationDate()"))
			return "\tpublic String creationDate() {return \""+today()+"\";}";
		return m;
	}
	
	
	
	
	private String convertTypes(String m)
	{
		if(m==null) return null;
		
		m = m.replaceAll("(?s)([^a-zA-Z0-9_])Execute([^a-zA-Z0-9_])","$1E$2");
		m = m.replaceAll("(?s)([^a-zA-Z0-9_])Give([^a-zA-Z0-9_])","$1P$2");
		m = m.replaceAll("(?s)([^a-zA-Z0-9_])Provide([^a-zA-Z0-9_])","$1G$2");
		m = m.replaceAll("(?s)([^a-zA-Z0-9_])Register([^a-zA-Z0-9_])","$1V$2");
		m = m.replaceAll("(?s)([^a-zA-Z0-9_])Retrieve([^a-zA-Z0-9_])","$1R$2");
		m = m.replaceAll("(?s)([^a-zA-Z0-9_])Transform([^a-zA-Z0-9_])","$1T$2");
		m = m.replaceAll("(?s)([^a-zA-Z0-9_])Filter([^a-zA-Z0-9_])","$1F$2");
		m = m.replaceAll("(?s)([^a-zA-Z0-9_])Function([^a-zA-Z0-9_])","$1H$2");
		m = m.replaceAll("(?s)([^a-zA-Z0-9_])Graphic([^a-zA-Z0-9_])","$1I$2");
		m = m.replaceAll("(?s)([^a-zA-Z0-9_])Support([^a-zA-Z0-9_])","$1S$2");
		m = m.replaceAll("(?s)([^a-zA-Z0-9_])DefaultSupport([^a-zA-Z0-9_])","$1S1$2");
		
		return m;
	}
	
	
	
	
	private String convertMethods(String m)
	{
		if(m==null) return null;
		
		m = m.replace(".execute()",".e()");
		m = m.replace(" execute()"," e()");
		
		m = m.replace(".give(",".p(");
		m = m.replace(" give("," p(");
		
		m = m.replace(".provide()",".g()");
		m = m.replace(" provide()"," g()");
		
		m = m.replace(".register(",".v(");
		m = m.replace(" register("," v(");
		
		m = m.replace(".retrieve(",".r(");
		m = m.replace(" retrieve("," r(");
		
		m = m.replace(".transform(",".t(");
		m = m.replace(" transform("," t(");
		
		m = m.replace(".filter(",".f(");
		m = m.replace(" filter("," f(");
		
		m = m.replace(".function(",".h(");
		m = m.replace(" function("," h(");
		
		m = m.replace(".gui()",".i()");
		m = m.replace(" gui()"," i()");
		
		return m;
	}
}
