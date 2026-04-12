package a.entity.gus06.swing.textcomp.autocomplete.entity.addtable;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20251127";}
	
	public static final String NEW1 = "new JTable(model)";
	public static final String TYPE = "JTable";
	
	public static final String IMPORT1 = "javax.swing.JTable";
	public static final String IMPORT2 = "javax.swing.table.AbstractTableModel";
	public static final String IMPORT3 = "javax.swing.JScrollPane";
	public static final String DEFAULT_VARNAME = "table";
	

	private Service extractStructure;
	private Service addImport;
	private Service addCons;
	private Service addFeature;
	
	public EntityImpl() throws Exception
	{
		extractStructure = Outside.service(this,"gus06.java.srccode.extract.entity.structure1");
		addImport = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addimport");
		addCons = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addcons");
		addFeature = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addfeature");
	}
	
	
	public void p(Object obj) throws Exception
	{v(DEFAULT_VARNAME,obj);}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		if(key.toLowerCase().equals("i"))
		{handle(comp, DEFAULT_VARNAME, true);return;}
		
		String[] n = key.split(" +", 2);
		String varName = n[0];
		String options = n.length==2 ? n[1] : "";
		boolean featureI = options.toLowerCase().equals("i");
		
		handle(comp, varName, featureI);
	}
	
	
	private void handle(JTextComponent comp, String varName, boolean featureI) throws Exception
	{
		addCons.p(comp);
		addImport.v(IMPORT1,comp);
		addImport.v(IMPORT2,comp);
		addImport.v(IMPORT3,comp);
		
		if(featureI) addFeature.v("i", comp);
		
		String text = comp.getText();
		Map struct = (Map) extractStructure.t(text);
		
		int pos1 = -1;
		int pos2 = -1;
		int pos3 = -1;
		
		String insert1 = "\tprivate "+TYPE+" "+varName+";";
		String insert2 = "\t\t"+varName+" = "+NEW1+";";
		
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
			
			if(i==pos3) b.append("\t{return "+varName+";}\n");
			else b.append(line+"\n");
			
			if(i==pos1) b.append(insert1+"\n");
			else if(i==pos2) b.append(insert2+"\n");
			
			if(featureI && line.trim().equals("public Object i() throws Exception"))
			pos3 = i+1;
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
