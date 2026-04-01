package a.entity.gus06.swing.textcomp.autocomplete.entity.addcons;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140825";}
	
	
	private Service extractStructure;

	public EntityImpl() throws Exception
	{
		extractStructure = Outside.service(this,"gus06.java.srccode.extract.entity.structure1");
	}

	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String text = comp.getText();
		
		Map struct = (Map) extractStructure.t(text);
		if(struct.containsKey("constructor")) return;
		
		int pos = indexFor(struct,"creationDate");
		
		String[] lines = text.split("\n");
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i];
			b.append(line+"\n");
			
			if(i==pos)
			{
				b.append("\n\n\tpublic EntityImpl() throws Exception\n");
				b.append("\t{\n");
				b.append("\t\t\n");
				b.append("\t}\n");
			}
		}
		
		comp.setText(b.toString());
	}
	
	
	private int indexFor(Map struct, String key) throws Exception
	{
		if(!struct.containsKey(key)) throw new Exception("Structure not found: "+key);
		int[] n = (int[]) struct.get(key);
		return n[1];
	}
}
