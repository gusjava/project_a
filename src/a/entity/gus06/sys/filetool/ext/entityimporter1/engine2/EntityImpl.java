package a.entity.gus06.sys.filetool.ext.entityimporter1.engine2;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250629";}

	private Service readFile;

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
	}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	private String today()
	{return sdf.format(new Date());}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length==4) handle((File) o[0], (String) o[1], (File) o[2], (String) o[3]);
		else if(o.length==5) handle((File) o[0], (String) o[1], (File) o[2], (String) o[3], (Map) o[4]);
		else throw new Exception("Wrong data number: "+o.length);
	}
	
	private void handle(File inputFile, String inputPackage, File outputFile, String outputPackage) throws Exception
	{
		if (!inputFile.isFile())
			throw new Exception("Invalid input file: " + inputFile);
		if (outputFile.exists())
			throw new Exception("Output file already found: " + outputFile);

		String inputSrc = (String) readFile.t(inputFile);
		
		System.out.println("Input file: "+inputFile);
		System.out.println("Output file: "+outputFile);

		inputSrc = inputSrc.replace(inputPackage, outputPackage);
		inputSrc = inputSrc.replace("a.framework", "gus06.framework");
		inputSrc = inputSrc.replaceAll("(?s)public String creationDate\\(\\)[^\"]+\"[^\"]+\"",
				"public String creationDate() {return \"" + today() + "\"");

		outputFile.getParentFile().mkdirs();
		PrintStream p = new PrintStream(outputFile);
		p.print(inputSrc);
		p.close();
	}
	
	private void handle(File inputFile, String inputPackage, File outputFile, String outputPackage, Map map) throws Exception
	{
		if (!inputFile.isFile())
			throw new Exception("Invalid input file: " + inputFile);
		if (outputFile.exists())
			throw new Exception("Output file already found: " + outputFile);

		String inputSrc = (String) readFile.t(inputFile);

		inputSrc = inputSrc.replace(inputPackage, outputPackage);
		inputSrc = inputSrc.replace("a.framework", "gus06.framework");
		inputSrc = inputSrc.replaceAll("(?s)public String creationDate\\(\\)[^\"]+\"[^\"]+\"",
				"public String creationDate() {return \"" + today() + "\"");
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			String value = (String) map.get(key);
			inputSrc = inputSrc.replace("\""+key+"\"", "\""+value+"\"");
		}

		outputFile.getParentFile().mkdirs();
		PrintStream p = new PrintStream(outputFile);
		p.print(inputSrc);
		p.close();
	}
}