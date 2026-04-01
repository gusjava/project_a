package a.entity.gus06.sys.filesrenamer1.name0.op.renameall;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250211";}


	private Service hasDoubloon;
	private Service moveFile;

	public EntityImpl() throws Exception
	{
		hasDoubloon = Outside.service(this,"gus06.data.list.string.filter.hasdoubloon_i");
		moveFile = Outside.service(this,"gus06.file.op.move");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List files = (List) o[0];
		List newNames = (List) o[1];
		
		if(files.isEmpty())
			throw new Exception("Invalid empty file list");
		if(files.size()!=newNames.size())
			throw new Exception("Size difference found for files and new names");
		if(hasDoubloon.f(newNames))
			throw new Exception("Doubloons found inside new file names");
		
		File f0 = (File) files.get(0);
		File parent = f0.getParentFile();
		
		for(int i=0;i<files.size();i++)
		{
			File file = (File) files.get(i);
			if(!file.isFile())
				throw new Exception("Invalid file: "+file);
			if(!file.getParentFile().equals(parent)) 
				throw new Exception("Invalid file parent: "+file.getParentFile());
		}
		
		String tempDirName = "_temp";
		File tempDir = new File(parent,tempDirName);
		while(tempDir.exists())
		{
			tempDirName = "_"+tempDirName;
			tempDir = new File(parent,tempDirName);
		}
		
		tempDir.mkdir();
		
		List tempFiles = new ArrayList();
		for(int i=0;i<files.size();i++)
		{
			File file = (File) files.get(i);
			File tempFile = new File(tempDir, file.getName());
			
			moveFile.p(new File[]{file, tempFile});
			tempFiles.add(tempFile);
		}
		
		for(int i=0;i<tempFiles.size();i++)
		{
			File tempFile = (File) tempFiles.get(i);
			String newName = (String) newNames.get(i);
			File outputFile = new File(parent, newName);
			
			moveFile.p(new File[]{tempFile, outputFile});
		}
		tempDir.delete();
	}
}
