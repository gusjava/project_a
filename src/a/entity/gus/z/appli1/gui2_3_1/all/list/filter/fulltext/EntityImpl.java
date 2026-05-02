package a.entity.gus.z.appli1.gui2_3_1.all.list.filter.fulltext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240715";}

	private Service findPackageDir;
	private Service findJavaFiles;
	private Service read;

	public EntityImpl() throws Exception {
		findPackageDir = Outside.service(this, "gus.x.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this, "gus.x.dir.listing0.files.java");
		read = Outside.service(this, "gus.x.file.string.read.n");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);

		R engine = (R) o[0];
		List list = (List) o[1];
		String search = (String) o[2];

		if (list == null) return null;

		Set lockSet = lockSet(engine);
		Set errSet = errSet(engine);

		Filter filter = new Filter(engine, search);

		List list1 = new ArrayList();
		List list2 = new ArrayList();
		List list3 = new ArrayList();

		for (int i = 0; i < list.size(); i++) {
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

	private Map compileErrMap(R engine) throws Exception
	{return (Map) engine.r("compileErrMap");}

	private Set errSet(R engine) throws Exception
	{
		Map m = compileErrMap(engine);
		return m != null ? m.keySet() : null;
	}

	private Set lockSet(R engine) throws Exception
	{return (Set) engine.r("lockSet");}

	private class Filter implements F {
		private String search;
		private File rootDir;

		public Filter(R engine, String search) throws Exception {
			this.search = search;
			rootDir = (File) engine.r("rootDir");
		}

		public boolean f(Object obj) throws Exception {
			String[] data = (String[]) obj;
			String name = data[0];

			File packageDir = (File) findPackageDir.t(new Object[]{rootDir, name});
			File[] javaFiles = (File[]) findJavaFiles.t(packageDir);

			for (File javaFile : javaFiles) {
				//TODO on est pas oblige de lire le fichier en entier
				String s = (String) read.t(javaFile);
				if (s.contains(search)) return true;
			}
			return false;
		}
	}
}
