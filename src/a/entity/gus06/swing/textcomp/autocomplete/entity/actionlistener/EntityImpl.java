package a.entity.gus06.swing.textcomp.autocomplete.entity.actionlistener;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140915";}
	
	public static final String IMPORT1 = "java.awt.event.ActionListener";
	public static final String IMPORT2 = "java.awt.event.ActionEvent";
	
	public static final String SELECTION = "/*<code>*/";
	
	
	private Service extractStructure;
	private Service addImport;
	private Service selectText;
	
	private boolean afterDot;
	private boolean hasMethod;
	private int indexHeader;
	private int indexEnd;
	

	public EntityImpl() throws Exception
	{
		extractStructure = Outside.service(this,"gus06.java.srccode.extract.entity.structure1");
		addImport = Outside.service(this,"gus06.swing.textcomp.autocomplete.entity.addimport");
		selectText = Outside.service(this,"gus06.swing.textcomp.perform2.select");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		addImport.v(IMPORT1,comp);
		addImport.v(IMPORT2,comp);
		
		String text = comp.getText();
		int pos = comp.getCaretPosition();
		
		afterDot = pos>0 && text.charAt(pos-1)=='.';
		hasMethod = text.contains("public void actionPerformed(ActionEvent e)");
		
		if(afterDot) text = text.substring(0,pos) + insertForAddActionListener() + text.substring(pos);
		
		Map struct = (Map) extractStructure.t(text);
		
		indexHeader = indexFor(struct,"classHeader");
		indexEnd = indexFor(struct,"end");
		
		String[] lines = text.split("\n");
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<lines.length;i++)
		b.append(formatLine(lines[i],i)+"\n");
		
		comp.setText(b.toString());
		selectText.v(SELECTION,comp);
	}
	
	
	
	
	private String formatLine(String line, int i)
	{
		if(i==indexHeader) return formatHeader(line);
		if(i==indexEnd && !hasMethod) return insertForActionPerformed()+"\n}";
		return line;
	}
	
	
	
	private int indexFor(Map struct, String key) throws Exception
	{
		if(!struct.containsKey(key))
			throw new Exception("Structure not found: "+key);
		int[] n = (int[]) struct.get(key);
		return n[1];
	}
	
	
	
	private String formatHeader(String line)
	{
		if(!line.contains(", ActionListener"))
		line = line.replace("implements Entity","implements Entity, ActionListener");
		return line;
	}
	
	
	
	private String insertForActionPerformed()
	{
		return "\n\n\tpublic void actionPerformed(ActionEvent e)\n\t{"+SELECTION+"}";
	}
	
	private String insertForAddActionListener()
	{
		return "addActionListener(this);";
	}
}