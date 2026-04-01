package a.entity.gus06.sys.filetool.ext.entityimporter1.engine;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250629";}
	
	public static final String FILENAME = "EntityImpl.java";


	private Service engine2;
	private Service listingDirs;
	
	public EntityImpl() throws Exception
	{
		engine2 = Outside.service(this,"gus06.sys.filetool.ext.entityimporter1.engine2");
		listingDirs = Outside.service(this, "gus.dir.listing0.dirs");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;

		File inputRoot = (File) o[0];
		String inputName = (String) o[1];
		File outputRoot = (File) o[2];
		String outputName = (String) o[3];
		
		System.out.println("Importing a entity " + inputName + " -> " + outputName);

		if (inputName.endsWith(".*"))
			handleMultiStrict(inputRoot, inputName, outputRoot, outputName);
		else if (inputName.endsWith("*"))
			handleMulti(inputRoot, inputName, outputRoot, outputName);
		else handleSingle(inputRoot, inputName, outputRoot, outputName);
	}
	
	private void handleSingle(File inputRoot, String inputName, File outputRoot, String outputName) throws Exception
	{
		String inputPackage = "a.entity." + inputName;
		String outputPackage = "gus06.entity." + outputName;
		
		File inputDir = new File(inputRoot, inputPackage.replace(".", File.separator));
		File outputDir = new File(outputRoot, outputPackage.replace(".", File.separator));

		File inputFile = new File(inputDir, FILENAME);
		File outputFile = new File(outputDir, FILENAME);

		if (!inputFile.isFile())
			throw new Exception("Invalid input file: " + inputFile);
		if (outputFile.exists())
			throw new Exception("Output file already found: " + outputFile);
		
		engine2.p(new Object[] {inputFile, inputPackage, outputFile, outputPackage});
	}
	
	private void handleMultiStrict(File inputRoot, String inputName, File outputRoot, String outputName) throws Exception
	{
		inputName = inputName.substring(0, inputName.length()-2);
		
		String inputPackage = "a.entity." + inputName;
		String outputPackage = "gus06.entity." + outputName;
		
		File inputDir = new File(inputRoot, inputPackage.replace(".", File.separator));
		File outputDir = new File(outputRoot, outputPackage.replace(".", File.separator));
		
		Map map = new HashMap();
		scanDir(map, inputDir, inputName, outputDir, outputName);
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String name1 = (String) it.next();
			String name2 = (String) map.get(name1);
			
			String package1 = "a.entity." + name1;
			String package2 = "gus06.entity." + name2;
			
			File dir1 = new File(inputRoot, package1.replace(".", File.separator));
			File dir2 = new File(outputRoot, package2.replace(".", File.separator));
		
			File file1 = new File(dir1, FILENAME);
			File file2 = new File(dir2, FILENAME);
			
			if (file2.exists()) throw new Exception("Output file already found: " + file2);
			engine2.p(new Object[] {file1, package1, file2, package2, map});
		}
	}
	
	private void handleMulti(File inputRoot, String inputName, File outputRoot, String outputName) throws Exception
	{
		inputName = inputName.substring(0, inputName.length()-1);
		
		String inputPackage = "gus06.entity." + inputName;
		String outputPackage = "a.entity." + outputName;
		
		File inputDir = new File(inputRoot, inputPackage.replace(".", File.separator));
		File outputDir = new File(outputRoot, outputPackage.replace(".", File.separator));
		
		Map map = new HashMap();
		map.put(inputName, outputName);
		scanDir(map, inputDir, inputName, outputDir, outputName);
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext()) {
			String name1 = (String) it.next();
			String name2 = (String) map.get(name1);
			
			String package1 = "gus06.entity." + name1;
			String package2 = "a.entity." + name2;
			
			File dir1 = new File(inputRoot, package1.replace(".", File.separator));
			File dir2 = new File(outputRoot, package2.replace(".", File.separator));
		
			File file1 = new File(dir1, FILENAME);
			File file2 = new File(dir2, FILENAME);
			
			if (!file2.exists())
			engine2.p(new Object[] {file1, package1, file2, package2, map});
		}
	}
	
	private void scanDir(Map map, File inputDir, String inputName, File outputDir, String outputName) throws Exception
	{
		File[] dd = (File[]) listingDirs.t(inputDir);
		for(File inputDir1 : dd) {
			String newPart = inputDir1.getName();
			File outputDir1 = new File(outputDir, newPart);
			
			String inputName1 = inputName+"."+newPart;
			String outputName1 = outputName+"."+newPart;
			
			File inputFile1 = new File(inputDir1, FILENAME);
			if(inputFile1.isFile())
				map.put(inputName1, outputName1);
			scanDir(map,inputDir1, inputName1, outputDir1, outputName1);
		}
	}
}