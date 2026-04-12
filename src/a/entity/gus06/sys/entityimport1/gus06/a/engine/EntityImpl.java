package a.entity.gus06.sys.entityimport1.gus06.a.engine;

import a.framework.*;
import java.io.File;
import java.nio.file.Files;
import java.io.PrintStream;

public class EntityImpl implements Entity, F {
	public String creationDate() {return "20260329";}
	
	public static final String FILENAME = "EntityImpl.java";
	
	public EntityImpl() throws Exception
	{
		
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File inputRoot = (File) o[0];
		File outputRoot = (File) o[1];
		String inputName = (String) o[2];
		
		if(!inputName.startsWith("gus.")) return false;
		String outputName = "gus06."+inputName.substring(4);
		
		String inputPackage = "gus06.entity." + inputName;
		String outputPackage = "a.entity." + outputName;
		
		File inputDir = new File(inputRoot, inputPackage.replace(".", File.separator));
		File outputDir = new File(outputRoot, outputPackage.replace(".", File.separator));
		
		File inputFile = new File(inputDir, FILENAME);
		File outputFile = new File(outputDir, FILENAME);

		if (outputFile.exists()) return false;
		if (!inputFile.isFile()) throw new Exception("Invalid input file: " + inputFile);
		
		String inputSrc = new String(Files.readAllBytes(inputFile.toPath()), "UTF-8");
		
		inputSrc = inputSrc.replace("package "+inputPackage, "package "+outputPackage);
		inputSrc = inputSrc.replace("import a.framework.", "import a.framework.");
		
		inputSrc = inputSrc.replace("Outside.service(this,\"gus.", "Outside.service(this,\"gus06.");
		inputSrc = inputSrc.replace("Outside.service(this,\"*gus.", "Outside.service(this,\"*gus06.");
		inputSrc = inputSrc.replace("Outside.service(this,\"+gus.", "Outside.service(this,\"+gus06.");
		
		outputDir.mkdirs();
		Files.write(outputFile.toPath(), inputSrc.getBytes("UTF-8"));
		
		File[] files = inputDir.listFiles();
		for(File file : files)
		{
			if(!file.isFile()) continue;
			
			String fileName = file.getName();
			if(!fileName.endsWith(".java")) continue;
			if(fileName.equals(FILENAME)) continue;
			
			File file1 = new File(outputDir, fileName);
			if(file1.exists()) continue;
			
			String content = new String(Files.readAllBytes(file.toPath()), "UTF-8");
			content = content.replace("package " + inputPackage, "package " + outputPackage);
			Files.write(file1.toPath(), content.getBytes("UTF-8"));
		}
		return true;
	}
}
