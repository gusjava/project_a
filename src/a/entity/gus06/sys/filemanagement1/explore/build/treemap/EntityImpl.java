package a.entity.gus06.sys.filemanagement1.explore.build.treemap;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191213";}

	public static final String CHARSET = "UTF-8";
	
	public static final String KEY_TYPE = "type";
	public static final String KEY_NAME = "name";
	public static final String KEY_PARENT = "parent";
	public static final String KEY_CHILDREN = "children";
	public static final String KEY_CHILDREN_MAP = "childrenMap";
	public static final String KEY_FILENB = "fileNb";
	public static final String KEY_DIRNB = "dirNb";
	public static final String KEY_FILENB0 = "fileNb0";
	public static final String KEY_DIRNB0 = "dirNb0";
	public static final String KEY_SIZE = "size";
	public static final String KEY_MD5 = "md5";
	public static final String KEY_MIME = "mime";
	public static final String KEY_MODIFIED = "modified";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_ROOTNAME = "rootName";
	
	public static final String TYPE_FILE = "file";
	public static final String TYPE_DIR = "dir";
	public static final String TYPE_ROOT = "root";
	
	public static final String FAILED_MD5 = "###";



	private Service splitPath;
	private Service newMap;
	private Service newList;
	private Service buildListMd5;

	public EntityImpl() throws Exception
	{
		splitPath = Outside.service(this,"gus06.file.filepath.split.all");
		newMap = Outside.service(this,"gus06.map.factory.silentmap");
		newList = Outside.service(this,"gus06.list.factory.silentlist");
		buildListMd5 = Outside.service(this,"gus06.list.string.build.md5");
	}
	
	
	private Map newMap() throws Exception
	{return (Map) newMap.g();}
	
	private List newList() throws Exception
	{return (List) newList.g();}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String rootName = (String) o[1];
		
		if(file==null || !file.isFile()) return null;
		
		Map rootMap = newMap();
		
		rootMap.put(KEY_TYPE,TYPE_ROOT);
		rootMap.put(KEY_NAME,rootName);
		rootMap.put(KEY_LOCATION,"");
		rootMap.put(KEY_ROOTNAME,rootName);
		rootMap.put(KEY_CHILDREN,newList());
		rootMap.put(KEY_CHILDREN_MAP,newMap());
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis,CHARSET);
		BufferedReader br = new BufferedReader(isr);
		
		try
		{
			String line = null;
			while((line = br.readLine())!=null)
			if(!line.equals(""))
			{
				String[] row = line.split("\t",-1);
				if(row.length!=6) throw new Exception("Invalid line: "+line);
				
				String location = row[0];
				String name = row[1];
				String size = row[2];
				String modified = row[3];
				String md5 = row[4];
				String mime = row[5];
				
				Map parent = rootMap;
				
				if(!location.equals(""))
				{
					String[] nn = (String[]) splitPath.t(location);
					for(String n : nn)
					{
						Map childrenMap = (Map) parent.get(KEY_CHILDREN_MAP);
						if(!childrenMap.containsKey(n))
						{
							Map child = newMap();
							
							childrenMap.put(n,child);
							((List) parent.get(KEY_CHILDREN)).add(child);
							
							String l = parent.get(KEY_LOCATION)+"/"+n;
							
							child.put(KEY_TYPE,TYPE_DIR);
							child.put(KEY_PARENT,parent);
							child.put(KEY_NAME,n);
							child.put(KEY_LOCATION,l);
							child.put(KEY_ROOTNAME,rootName);
							child.put(KEY_CHILDREN,newList());
							child.put(KEY_CHILDREN_MAP,newMap());
							
							parent = child;
						}
						else parent = (Map) childrenMap.get(n);
					}
				}
				
				if(!size.equals(""))
				{
					// file
					
					Map childrenMap = (Map) parent.get(KEY_CHILDREN_MAP);
					List children = (List) parent.get(KEY_CHILDREN);
					
					Map child = newMap();
					childrenMap.put(name,child);
					children.add(child);
					
					child.put(KEY_TYPE,TYPE_FILE);
					child.put(KEY_PARENT,parent);
					child.put(KEY_NAME,name);
					
					child.put(KEY_MD5,md5);
					child.put(KEY_MIME,mime);
					child.put(KEY_MODIFIED,modified);
					child.put(KEY_SIZE,Long.parseLong(size));
					child.put(KEY_LOCATION,location);
					child.put(KEY_ROOTNAME,rootName);
				}
				else
				{
					// empty dir
					
					Map childrenMap = (Map) parent.get(KEY_CHILDREN_MAP);
					List children = (List) parent.get(KEY_CHILDREN);
					
					Map child = newMap();
					childrenMap.put(name,child);
					children.add(child);
					
					child.put(KEY_TYPE,TYPE_DIR);
					child.put(KEY_NAME,name);
					
					child.put(KEY_MODIFIED,modified);
					child.put(KEY_LOCATION,location);
					child.put(KEY_ROOTNAME,rootName);
					
					child.put(KEY_CHILDREN,newList());
					child.put(KEY_CHILDREN_MAP,newMap());
				}
			}
		}
		finally {fis.close();}
		
		updateNode(rootMap);
		return rootMap;
	}
	
	
	
	
	
	private void updateNode(Map node) throws Exception
	{
		String nodeType = (String) node.get(KEY_TYPE);
		if(nodeType.equals(TYPE_FILE)) return;
		
		node.remove(KEY_CHILDREN_MAP);
		List children = (List) node.get(KEY_CHILDREN);
		
		long totalSize = 0;
		long totalFileNb = 0;
		long totalDirNb = 0;
		long fileNb0 = 0;
		long dirNb0 = 0;
		
		List md5List = new ArrayList();
		boolean hasFailedMd5 = false;
		
		int nb = children.size();
		for(int i=0;i<nb;i++)
		{
			Map child = (Map) children.get(i);
			updateNode(child);
			
			long size = (long) child.get(KEY_SIZE);
			String md5 = (String) child.get(KEY_MD5);
			String type = (String) child.get(KEY_TYPE);
			
			totalSize += size;
			
			if(md5.equals(FAILED_MD5)) hasFailedMd5 = true;
			
			if(type.equals(TYPE_FILE))
			{
				md5List.add("f:"+md5);
				totalFileNb++;
				fileNb0++;
			}
			else
			{
				md5List.add("d:"+md5);
				totalFileNb += (long) child.get(KEY_FILENB);
				totalDirNb += 1+(long) child.get(KEY_DIRNB);
				dirNb0++;
			}
		}
		
		String dirMd5 = hasFailedMd5 ? FAILED_MD5 : (String) buildListMd5.t(md5List);
		
		node.put(KEY_SIZE,totalSize);
		node.put(KEY_MD5,dirMd5);
		node.put(KEY_FILENB,totalFileNb);
		node.put(KEY_DIRNB,totalDirNb);
		node.put(KEY_FILENB0,fileNb0);
		node.put(KEY_DIRNB0,dirNb0);
	}
}