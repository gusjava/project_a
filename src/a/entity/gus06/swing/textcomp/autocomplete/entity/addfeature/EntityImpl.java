package a.entity.gus06.swing.textcomp.autocomplete.entity.addfeature;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140825";}
	
	
	
	private Service extractStructure;
	

	public EntityImpl() throws Exception
	{
		extractStructure = Outside.service(this,"gus06.java.srccode.extract.entity.structure1");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String text = comp.getText();
		
		Map struct = (Map) extractStructure.t(text);
		if(struct.containsKey("feature_"+key)) return;
		
		String insert = insertForFeature(key);
		
		int indexHeader = indexFor(struct,"classHeader");
		int indexEnd = indexFor(struct,"end");
		
		
		String[] lines = text.split("\n");
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i];
			
			if(i==indexHeader)
				b.append(formatHeader(line,key)+"\n");
			else if(i==indexEnd && insert!=null)
				b.append(insert+"\n}\n");
			else b.append(line+"\n");
		}
		
		comp.setText(b.toString());
	}
	
	
	
	
	private int indexFor(Map struct, String key) throws Exception
	{
		if(!struct.containsKey(key))
			throw new Exception("Structure not found: "+key);
		int[] n = (int[]) struct.get(key);
		return n[1];
	}
	
	
	
	private String formatHeader(String header, String feature)
	{
		feature = feature.toUpperCase().replace("X","Runnable");
		if(!header.contains(", "+feature))
		header = header.replace("implements Entity","implements Entity, "+feature);
		return header;
	}
	
	
	
	private String insertForFeature(String feature) throws Exception
	{
		feature = feature.toLowerCase();
		
		if(feature.equals("e"))
			return "\tpublic void e() throws Exception\n\t{\n\t\t\n}";
		if(feature.equals("p"))
			return "\tpublic void p(Object obj) throws Exception\n\t{\n\t\t\n}";
		if(feature.equals("g"))
			return "\tpublic Object g() throws Exception\n\t{return null;}";
		if(feature.equals("v"))
			return "\tpublic void v(String key, Object obj) throws Exception\n\t{\n\t\t\n}";
		if(feature.equals("r"))
			return "\tpublic Object r(String key) throws Exception\n\t{return null;}";
		if(feature.equals("t"))
			return "\tpublic Object t(Object obj) throws Exception\n\t{return null;}";
		if(feature.equals("f"))
			return "\tpublic boolean f(Object obj) throws Exception\n\t{return true;}";
		if(feature.equals("h"))
			return "\tpublic double h(double value) throws Exception\n\t{return 0;}";
		if(feature.equals("x"))
			return "\tpublic void run()\n\t{\n\t\t\n}";
		if(feature.equals("i"))
			return "\tpublic Object i() throws Exception\n\t{return null;}";
		if(feature.equals("s"))
			return null;
		
		throw new Exception("Unknown feature: "+feature);
	}

}
