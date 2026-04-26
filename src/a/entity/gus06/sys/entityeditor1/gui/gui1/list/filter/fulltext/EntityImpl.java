package a.entity.gus06.sys.entityeditor1.gui.gui1.list.filter.fulltext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251112";}

	private Service findPackageDir;
	private Service findJavaFiles;
	private Service read;

	public EntityImpl() throws Exception
	{
		findPackageDir = Outside.service(this, "gus.x.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this, "gus.x.dir.listing0.files.java");
		read = Outside.service(this,"gus.x.file.string.read.n");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 6) throw new Exception("Wrong data number: " + o.length);

		R engine = (R) o[0];
		List list = (List) o[1];
		String search = (String) o[2];
		String devId = (String) o[3];
		Set lockSet = (Set) o[4];
		Set errSet = (Set) o[5];

		if (list == null) return null;

		Filter filter = new Filter(engine, search);

		List list1 = new ArrayList();
		List list2 = new ArrayList();
		List list3 = new ArrayList();

		for (int i = 0; i < list.size(); i++)
		{
			String[] data = (String[]) list.get(i);
			String name = data[0];

			if (errSet != null && errSet.contains(name))
				list1.add(data);
			else if (lockSet != null && lockSet.contains(name))
				list2.add(data);
			else if (filter.f(data))
				list3.add(data);
		}

		List results = new ArrayList();
		results.addAll(list1);
		results.addAll(list2);
		results.addAll(list3);
		return results;
	}

	private class Filter implements F
	{
		private R engine;
		private String search;
		private File srcDir;

		public Filter(R engine, String search) throws Exception
		{
			this.engine = engine;
			this.search = search;
			srcDir = (File) engine.r("srcDir");
		}

		public boolean f(Object obj) throws Exception
		{
			String[] data = (String[]) obj;
			String name = data[0];
			
			File packageDir = (File) findPackageDir.t(new Object[] {srcDir, name});
			File[] javaFiles = (File[]) findJavaFiles.t(packageDir);
			
			for(File javaFile : javaFiles)
			{
				//TODO on est pas obligé de lire le fichier en entier
				String s = (String) read.t(javaFile);
				if(s.contains(search)) return true;
			}
			return false;
		}
	}
}