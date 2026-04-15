package a.entity.gus.y.entitysrcedit1.multi;

import java.io.File;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20260415";}
	
	public static final String CLASS_NAME = "EntityImpl";

	private Service read;
	private Service write;

	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus.x.entity.src.read1");
		write = Outside.service(this, "gus.x.entity.src.write1");
	}

	public void p(Object obj) throws Exception {
		f(obj);
	}

	public boolean f(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);

		File rootDir = (File) o[0];
		String entityName = (String) o[1];
		List operations = (List) o[2];

		String entityPackage = "a.entity." + entityName;
		File packageDir = new File(rootDir, entityPackage.replace(".", File.separator));

		File javaFile = new File(packageDir, CLASS_NAME + ".java");
		if (!javaFile.exists()) return false;
		
		String src = (String) read.t(javaFile);
		
		Collections.sort(operations, new Comparator1());
		List segments = new ArrayList();
		int pos = src.length();
		
		for(int i=0;i<operations.size();i++)
		{
			Object[] op = (Object[]) operations.get(i);
			if (op.length != 3) throw new Exception("Invalid operation size at index " + i + ": " + op.length);

			int start = ((Integer) op[0]).intValue();
			int end = ((Integer) op[1]).intValue();
			String replacement = (String) op[2];
			
			if (start < 0 || end < 0) throw new Exception("Negative index at operation " + i);
			if (start > end) throw new Exception("start > end at operation " + i + " (" + start + " > " + end + ")");
			if (end > src.length()) throw new Exception("end out of bounds at operation " + i + " (" + end + " > " + src.length() + ")");
			if (pos < end) throw new Exception("Operations overlap or are not ordered at index " + i + " (pos=" + pos + ", end=" + end + ")");
			
			segments.add(src.substring(end, pos));
			segments.add(replacement);
			pos = start;
		}
		segments.add(src.substring(0, pos));
		
		StringBuilder sb = new StringBuilder();
		for (int i = segments.size() - 1; i >= 0; i--) 
		{
			String segment = (String) segments.get(i);
			sb.append(segment);
		}
		write.p(new Object[]{javaFile, sb.toString()});
		return true;
	}
	
	
	private class Comparator1 implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			Object[] op1 = (Object[]) o1;
			Object[] op2 = (Object[]) o2;
			
			int start1 = ((Integer) op1[0]).intValue();
			int start2 = ((Integer) op2[0]).intValue();
			
			if(start1==start2) return 0;
			return start1<start2 ? 1 : -1;
		}
	}
}
