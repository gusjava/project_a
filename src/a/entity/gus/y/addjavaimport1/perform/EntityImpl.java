package a.entity.gus.y.addjavaimport1.perform;

import a.framework.*;
import java.util.Set;
import java.util.Iterator;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240713";}
	
	private Service toArray;
	private Service extractLastImportPos;
	private Service extractImports;
	private Service searchClass;
	
	public EntityImpl() throws Exception
	{
		toArray = Outside.service(this,"gus.x.javasrc.toarray");
		extractLastImportPos = Outside.service(this,"gus.y.addjavaimport1.lastimport.lineindex");
		extractImports = Outside.service(this,"gus.y.addjavaimport1.extract.imports1");
		searchClass = Outside.service(this,"gus.y.addjavaimport1.searchclass.preferred");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		
		JTextComponent comp = (JTextComponent) o[0];
		String query = (String) o[1];
		
		Holder holder = new Holder(comp);
		boolean done = holder.importPackage(query);
		return done ? holder.className : null;
	}
	
	
	
	private class Holder
	{
		private JTextComponent comp;
		
		private String fullName = null;
		private String className = null;
		private String packageName = null;
		
		public Holder(JTextComponent comp)
		{this.comp = comp;}
		
		
		public boolean importPackage(String query) throws Exception
		{
			fullName = query.contains(".") ? query : (String) searchClass.t(query);
			if(fullName==null) return false;
			
			String[] n = fullName.split("\\.");
			className = n[n.length-1];
			packageName = fullName.substring(0,fullName.length()-className.length()-1);
			
			String text = comp.getText();
			Object src = toArray.t(text);
			
			if(hasImport(src)) return false;
			
			int pos = findPosForNextImport(src);
			String insert = "import "+fullName+";";
			
			int caretPos = comp.getCaretPosition();
			if(caretPos>pos) caretPos += insert.length()+1;
			
			String[] lines = text.split("\n");
			StringBuffer b = new StringBuffer();
			
			for(int i=0;i<lines.length;i++)
			{
				String line = lines[i];
				b.append(line+"\n");
				if(i==pos) b.append(insert+"\n");
			}
			
			comp.setText(b.toString());
			comp.setCaretPosition(caretPos);
			
			return true;
		}
		
		
		private boolean hasImport(Object src) throws Exception
		{
			if(packageName.equals("java.lang")) return true;
			
			Set imports = (Set) extractImports.t(src);
			Iterator it = imports.iterator();
			while(it.hasNext())
			{
				String import_ = (String) it.next();
				if(import_.endsWith("."+className)) return true;
				if(import_.equals(packageName+".*")) return true;
			}
			return false;
		}
	}
	
	
	private int findPosForNextImport(Object src) throws Exception
	{
		return (Integer) extractLastImportPos.t(src);
	}
	
}
