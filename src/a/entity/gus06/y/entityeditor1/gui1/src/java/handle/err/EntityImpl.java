package a.entity.gus06.y.entityeditor1.gui1.src.java.handle.err;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.JTextArea;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251115";}
	
	public static final String COL_FILE_NAME = "file_name";
	public static final String COL_LINE_NB = "line_nb";
	public static final String COL_LINE_POS = "line_pos";
	
	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_MISSING_LINK = "missing_link";
	public static final String COL_LINK = "link";
	public static final String COL_POS = "pos";

	private Service convertXYtoLen;

	public EntityImpl() throws Exception
	{
		convertXYtoLen = Outside.service(this,"gus.x.string.coord.xytolen");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Invalid data number: "+o.length);
		
		Object data = o[0];
		String fileName = (String) o[1];
		JTextArea area = (JTextArea) o[2];
		
		Highlighter high = area.getHighlighter();
		String text = area.getText();
		
		high.removeAllHighlights();
		
		String entityName = (String) ((R) data).r("entityName");
		List compileErrList = (List) ((R) data).r("compileErrList");
		List missingLinkList = (List) ((R) data).r("missingLinkList");
		
		if(compileErrList!=null)
		for (int i = 0; i < compileErrList.size(); i++) {
			Map m = (Map) compileErrList.get(i);
			String errFileName = (String) m.get(COL_FILE_NAME);
			if (errFileName.equals(fileName)) {
				int lineNb = (int) m.get(COL_LINE_NB);
				int linePos = (int) m.get(COL_LINE_POS);
				Integer pos = convertXYtoLen(text, lineNb - 1, linePos);
				if (pos != null) high.addHighlight(pos, pos + 2, compileErrPainter);
			}
		}
		
		if(fileName.equals("EntityImpl.java"))
		{
			if(missingLinkList!=null)
			for (int i = 0; i < missingLinkList.size(); i++)
			{
				Map m = (Map) missingLinkList.get(i);
				String entityName0 = (String) m.get(COL_ENTITY_NAME);
				if (entityName.equals(entityName0)) {
					String missingLink = (String) m.get(COL_MISSING_LINK);
					int pos = (int) m.get(COL_POS);
					high.addHighlight(pos, pos + missingLink.length(), missingLinkPainter);
				}
			}
		}
	}

	// CONVERT XY TO LEN

	private Integer convertXYtoLen(String text, int x, int y) throws Exception
	{return (Integer) convertXYtoLen.t(new Object[] { text, new int[]{x,y} });}

	// COMPILE ERR PAINTER

	public static final Color COLOR_COMPILE_ERR = Color.RED;

	private CompileErrHighlightPainter compileErrPainter = new CompileErrHighlightPainter();

	private class CompileErrHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
	{public CompileErrHighlightPainter() { super(COLOR_COMPILE_ERR); }}

	// XYZ ERR PAINTER
	
	public static final Color COLOR_XYZ_ERR = new Color(204,0,255);

	private XyzErrHighlightPainter xyzErrPainter = new XyzErrHighlightPainter();

	private class XyzErrHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
	{public XyzErrHighlightPainter() { super(COLOR_XYZ_ERR); }}

	// MISSING LINK PAINTER
	
	public static final Color COLOR_MISSING_LINK = Color.ORANGE;

	private MissingLinkHighlightPainter missingLinkPainter = new MissingLinkHighlightPainter();

	private class MissingLinkHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
	{public MissingLinkHighlightPainter() { super(COLOR_MISSING_LINK); }}
}
