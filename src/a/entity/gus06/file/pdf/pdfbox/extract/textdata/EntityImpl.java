package a.entity.gus06.file.pdf.pdfbox.extract.textdata;

import java.io.File;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
import a.framework.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251213";}
	
	public static final float EPS = 1.0f;
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return extractData((File) obj, 0);
		if(obj instanceof Object[]) return extractData((Object[]) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private List extractData(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		File file = (File) o[0];
		int index = ((Integer) o[1]).intValue();
		return extractData(file, index);
	}
	
	private List extractData(File file, int index) throws IOException
	{
		List list = new ArrayList();
		try(PDDocument doc = Loader.loadPDF(file))
		{
			PosStripper stripper = new PosStripper();
			stripper.setStartPage(index);
			stripper.setEndPage(index+1);
			stripper.getText(doc);
			
			StringBuilder sb = null;
			float x = -1;
			float y = -1;
			float w = -1;
			float h = -1;
			boolean bold = false;
			boolean italic = false;
			
			for(TextPosition t : stripper.list)
			{
				String _s = t.getUnicode();
				float _x = t.getXDirAdj();
				float _y = t.getYDirAdj();
				float _w = t.getWidthDirAdj();
				float _h = t.getFontSizeInPt();
				
				PDFont font = t.getFont();
				String name = font.getName().toLowerCase();
				PDFontDescriptor fd = font.getFontDescriptor();
				boolean _bold = (fd != null && fd.isForceBold()) || name.contains("bold");
				boolean _italic = (fd != null && fd.isItalic()) || name.contains("italic") || name.contains("oblique");
				
				if(sb==null)
				{
					sb = new StringBuilder(_s);
					x = _x;
					y = _y;
					w = _w;
					h = _h;
					bold = _bold;
					italic = _italic;
					continue;
				}
				
				if(Math.abs(_h-h) > EPS 
				|| Math.abs(_y-y) > EPS 
				|| Math.abs(_x-x-w) > EPS
				|| _bold!=bold
				|| _italic!=italic)
				{
					Map m = new HashMap();
					m.put("x", x);
					m.put("y", y);
					m.put("w", w);
					m.put("h", h);
					m.put("s", sb.toString());
					m.put("bold", bold);
					m.put("italic", italic);
					list.add(m);
					
					sb = new StringBuilder(_s);
					x = _x;
					y = _y;
					w = _w;
					h = _h;
					bold = _bold;
					italic = _italic;
				} 
				else
				{
					w = _x+_w-x;
					h = Math.max(h, _h);
					sb.append(_s);
				}
			}
		
			if(sb!=null)
			{
				Map m = new HashMap();
				m.put("x", x);
				m.put("y", y);
				m.put("w", w);
				m.put("h", h);
				m.put("s", sb.toString());
				m.put("bold", bold);
				m.put("italic", italic);
				list.add(m);
			}
		}
		return list;
	}
	
	private class PosStripper extends PDFTextStripper
	{
		public List<TextPosition> list = new ArrayList<>();
		public PosStripper() throws IOException {}
	
		protected void processTextPosition(TextPosition text)
		{list.add(text);}
	}
}
