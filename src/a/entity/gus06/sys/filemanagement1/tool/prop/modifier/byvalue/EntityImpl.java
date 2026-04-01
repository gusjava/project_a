package a.entity.gus06.sys.filemanagement1.tool.prop.modifier.byvalue;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.util.Collections;
import java.io.PrintStream;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201121";}


	private Service input;
	private Service readCounts;
	private Service readList;
	private Service showErr;
	private Service listToSet;
	private Service writeText;
	private Service putField;

	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog.change");
		readCounts = Outside.service(this,"gus06.file.read.string.map.tn.count.utf8");
		readList = Outside.service(this,"gus06.file.read.string.list.autodetect");
		showErr = Outside.service(this,"gus06.swing.optionpane.showmessage.error");
		listToSet = Outside.service(this,"gus06.tostring.set");
		writeText = Outside.service(this,"gus06.file.write.string");
		putField = Outside.service(this,"gus06.file.properties.perform.field.put");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String dirName1 = (String) o[1];
		String dirName2 = (String) o[2];
		String propName = (String) o[3];
		String valueOld = (String) o[4];
		
		String valueNew = (String) input.t(new String[]{"Enter new value:",valueOld});
		if(valueNew==null) return false;
		
		
		
		File dirGen = (File) ((R) engine).r(dirName1);
		File md5Dir = new File(dirGen,dirName2);
		File countDir = new File(dirGen,"counts");
		
		File countFile = new File(countDir,dirName2+".txt");
		if(!countFile.isFile())
		{
			showErr.p("File not found: "+countFile);
			return false;
		}
		
		Map map = (Map) readCounts.t(countFile);
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		
		int indexOld = keys.indexOf(valueOld);
		if(indexOld==-1)
		{
			showErr.p("Value not found inside countFile: "+valueOld);
			return false;
		}
		
		File md5FileOld = new File(md5Dir,indexOld+".txt");
		if(!md5FileOld.isFile())
		{
			showErr.p("File not found: "+md5FileOld);
			return false;
		}
		List md5ListOld = (List) readList.t(md5FileOld);
		
		for(int i=0;i<md5ListOld.size();i++)
		{
			String md5 = (String) md5ListOld.get(i);
			File infoFile = (File) ((R)engine).r("infoFile:"+md5);
			putField.p(new Object[]{infoFile,propName,valueNew});
		}
		
		int indexNew = keys.indexOf(valueNew);
		if(indexNew!=-1)
		{
			File md5FileNew = new File(md5Dir,indexNew+".txt");
			if(!md5FileNew.isFile())
			{
				showErr.p("File not found: "+md5FileNew);
				return false;
			}
			List md5ListNew = (List) readList.t(md5FileNew);
			Set md5SetNew = new HashSet(md5ListNew);
			md5SetNew.addAll(md5ListOld);
			
			keys.remove(valueOld);
			
			//COUNT FILE
			
			map.remove(valueOld);
			map.put(valueNew,md5SetNew.size());
			writeCount(countFile,keys,map);
			
			// MD5 FILE NEW
			
			writeSet(md5FileNew,md5SetNew);
			
			md5FileOld.delete();
			
			for(int i=indexOld;i<keys.size();i++) rename(md5Dir,i+1,i);
		}
		else
		{
			map.remove(valueOld);
			map.put(valueNew,md5ListOld.size());
			
			keys = new ArrayList(map.keySet());
			Collections.sort(keys);
			writeCount(countFile,keys,map);
			
			indexNew = keys.indexOf(valueNew);
			if(indexNew < indexOld)
			{
				rename(md5Dir,indexOld,99999999);
				for(int i=indexOld-1;i>=indexNew;i--) rename(md5Dir,i+1,i);
				rename(md5Dir,99999999,indexNew);
			}
			else if(indexOld < indexNew)
			{
				rename(md5Dir,indexOld,99999999);
				for(int i=indexOld+1;i<=indexNew;i++) rename(md5Dir,i,i-1);
				rename(md5Dir,99999999,indexNew);
			}
		}
		
		return true;
	}
	
	
	
	private void rename(File dir, int index1, int index2)
	{
		File f1 = new File(dir,index1+".txt");
		File f2 = new File(dir,index2+".txt");
		f1.renameTo(f2);
	}
	
	
	private void writeSet(File f, Set set)
	{
		try{
			String s = (String) listToSet.t(set);
			writeText.p(new Object[]{f,s});
		}
		catch(Exception e)
		{
			Exception e1 = new Exception("Failed to write set inside file: "+f,e);
			Outside.err(this,"writeSet(File,Set)",e1);
		}
	}
	
	private void writeCount(File file, List keys, Map map) throws Exception
	{
		PrintStream p = new PrintStream(file,"UTF-8");
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			Integer count = (Integer) map.get(key);
			
			p.println(key+"\t"+count);
		}
		p.close();
	}
}
