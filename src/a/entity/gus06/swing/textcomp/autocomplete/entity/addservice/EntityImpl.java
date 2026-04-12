package a.entity.gus06.swing.textcomp.autocomplete.entity.addservice;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140818";}
	
	public static final String SELECTION = "<rule>";
	
	private Service extractStructure;
	private Service selectText;
	private Service addCons;


	public EntityImpl() throws Exception
	{
		extractStructure = Outside.service(this,"gus06.java.srccode.extract.entity.structure1");
		selectText = Outside.service(this,"gus06.swing.textcomp.perform2.select");
		addCons = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addcons");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		addCons.p(comp);
		
		String text = comp.getText();
		Map struct = (Map) extractStructure.t(text);
		
		String insert1 = "\tprivate Service "+key+";";
		int pos1 = -1;
		
		String insert2 = "\t\t"+key+" = Outside.service(this,\""+SELECTION+"\");";
		int pos2 = -1;
		
		if(struct.containsKey("var_service"))
			pos1 = indexFor(struct,"var_service");
		else
		{
			pos1 = indexFor(struct,"creationDate");
			insert1 = "\n\n"+insert1;
		}
		
		if(struct.containsKey("init_service"))
			pos2 = indexFor(struct,"init_service");
		else pos2 = indexFor(struct,"constructor")+1;
		
		
		String[] lines = text.split("\n");
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i];
			b.append(line+"\n");
			
			if(i==pos1) b.append(insert1+"\n");
			else if(i==pos2) b.append(insert2+"\n");
		}
		
		comp.setText(b.toString());
		selectText.v(SELECTION,comp);
	}
	
	
	private int indexFor(Map struct, String key) throws Exception
	{
		if(!struct.containsKey(key)) throw new Exception("Structure not found: "+key);
		int[] n = (int[]) struct.get(key);
		return n[1];
	}
}
