package a.entity.gus06.swing.textcomp.autocomplete.entity.addimport;

import a.framework.*;
import java.util.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20140915";}
	
	
	private Service extractLastImportPos;
	private Service extractImports;
	private Service searchClass;
	private Service findCaretWord;
	private Service replaceCaretWord;
	
	
	public EntityImpl() throws Exception
	{
		extractLastImportPos = Outside.service(this,"gus06.java.srccode.extract.entity.lastimport.pos");
		extractImports = Outside.service(this,"gus06.java.srccode.extract.entity.imports1");
		searchClass = Outside.service(this,"gus06.java.searchclass.fromrt.preferred");
		findCaretWord = Outside.service(this,"gus06.swing.textcomp.caret.word.find");
		replaceCaretWord = Outside.service(this,"gus06.swing.textcomp.caret.word.replace");
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		String query = (String) findCaretWord.t(obj);
		if(query==null) return;
		
		Holder holder = new Holder((JTextComponent) obj);
		boolean done = holder.importPackage(query);
		
		if(done) replaceCaretWord.v(holder.className,obj);
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		Holder holder = new Holder((JTextComponent) obj);
		holder.importPackage(key);
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
			if(hasImport(text)) return false;
			
			int pos = findPosForNextImport(text);
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
		
		
		private boolean hasImport(String src) throws Exception
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
	
	
	
	private int findPosForNextImport(String src) throws Exception
	{
		return (Integer) extractLastImportPos.t(src);
	}
	
	
	private String findPackage(String fullName)
	{
		String[] n = fullName.split("\\.");
		String last = n[n.length-1];
		return fullName.substring(0,fullName.length()-last.length()-1);
	}
	
	
	private String findClassName(String fullName)
	{
		String[] n = fullName.split("\\.");
		return n[n.length-1];
	}
}