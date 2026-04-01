package a.entity.gus06.sys.pdfgeneration2.engine;

import a.framework.*;
import java.io.File;
import com.lowagie.text.Font;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.List;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220915";}
	
	
	public static final String KEY_PDF_FILE = "pdf_file";
	public static final String KEY_PAGE_MARGIN = "page_margin";
	public static final String KEY_PAGE_MARGIN_N = "page_margin_n";
	public static final String KEY_PAGE_MARGIN_S = "page_margin_s";
	public static final String KEY_PAGE_MARGIN_W = "page_margin_w";
	public static final String KEY_PAGE_MARGIN_E = "page_margin_e";
	public static final String KEY_PAGE_SIZE = "page_size";
	public static final String KEY_DRAW = "draw";
	
	public static final int DEFAULT_PAGE_MARGIN = 6;
	public static final String DEFAULT_PAGE_SIZE = "A4";
	public static final Color DEFAULT_COLOR = Color.BLACK;
	public static final Font DEFAULT_FONT = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
	public static final int DEFAULT_FONTNAME = Font.TIMES_ROMAN;
	public static final int DEFAULT_STYLE = 0;
	public static final int DEFAULT_SIZE = 12;



	private Service findColor;

	public EntityImpl() throws Exception
	{
		findColor = Outside.service(this,"gus06.find.color");
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		File pdfFile = (File) get(map, KEY_PDF_FILE);
		if(pdfFile.exists()) throw new Exception("PDF file already exists: "+pdfFile);
		
		try(FileOutputStream fos = new FileOutputStream(pdfFile))
		{
			Integer pageMargin = (Integer) get(map, KEY_PAGE_MARGIN, DEFAULT_PAGE_MARGIN);
			Integer pageMarginN = (Integer) get(map, KEY_PAGE_MARGIN_N, pageMargin);
			Integer pageMarginS = (Integer) get(map, KEY_PAGE_MARGIN_S, pageMargin);
			Integer pageMarginW = (Integer) get(map, KEY_PAGE_MARGIN_W, pageMargin);
			Integer pageMarginE = (Integer) get(map, KEY_PAGE_MARGIN_E, pageMargin);
			
			Rectangle pageSize = stringToPage((String) get(map, KEY_PAGE_SIZE, DEFAULT_PAGE_SIZE));
			
			Document document = new Document(pageSize, pageMarginW, pageMarginE, pageMarginN, pageMarginS);
			PdfWriter writer = PdfWriter.getInstance(document, fos);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			
			List list = (List) get(map, KEY_DRAW);
			drawAll(map, list, cb);
			document.close();
			writer.close();
		}
	}
	
	
	private void drawAll(Map map, List list, PdfContentByte cb) throws Exception
	{
		for(int i=0;i<list.size();i++)
		draw(map, list.get(i), cb);
	}
	
	
	private void draw(Map map, Object input, PdfContentByte cb) throws Exception
	{
		List infos = findInfos(map, input);
		String type = (String) infos.get(0);
		
		if(type.startsWith("!")) return;
		
		if(type.equals("drawAll"))		op_drawAll(map, infos, cb);
		else if(type.equals("line")) 		op_drawLine(map, infos, cb);
		else if(type.equals("path")) 		op_drawPath(map, infos, cb);
		else if(type.equals("rect")) 		op_drawRect(map, infos, cb);
		else if(type.equals("frect"))		op_fillRect(map, infos, cb);
		else if(type.equals("text")) 		op_drawText(map, infos, cb);
		else if(type.equals("image")) 		op_drawImage(map, infos, cb);
		else if(type.equals("nextPage")) 	op_nextPage(map, infos, cb);
		else if(type.equals("e")) 		op_execute(map, infos);
		
		else throw new Exception("Invalid cmd type: "+type);
	}
	
	
	
	/*
	* OPERATIONS
	*/
	
	private void op_drawAll(Map map, List infos, PdfContentByte cb) throws Exception
	{
		List list = findList(map, part(infos,1));
		drawAll(map, list, cb);
	}
	
	private void op_drawLine(Map map, List infos, PdfContentByte cb) throws Exception
	{
		int[] start = findPoint(map, part(infos,1));
		int[] end = findPoint(map, part(infos,2));
		Color color = findColor(map, part(infos,3));
		
		cb.setColorStroke(color);
		cb.moveTo(start[0], start[1]);
		cb.lineTo(end[0], end[1]);
		cb.stroke();
	}
	
	
	private void op_drawPath(Map map, List infos, PdfContentByte cb) throws Exception
	{
		List path = findPath(map, part(infos,1));
		Color color = findColor(map, part(infos,2));
		
		cb.setColorStroke(color);
		for(int i=0;i<path.size();i++)
		{
			int[] point = (int[]) path.get(i);
			if(i==0) cb.moveTo(point[0], point[1]);
			else cb.lineTo(point[0], point[1]);
		}
		cb.stroke();
	}
	
	
	private void op_drawText(Map map, List infos, PdfContentByte cb) throws Exception
	{
		String text = findText(map, part(infos,1));
		int[] point = findPoint(map, part(infos,2));
		Font font = findFont(map, part(infos,3));
		
		int x = point[0];
		int y = point[1];
		
		int left = x;
		int bottom = 0;
		int right = x+1000;
		int top = y;
		
		int leading = 0;
		
		ColumnText ct = new ColumnText(cb);
		ct.setSimpleColumn(left, bottom, right, top, leading, Element.ALIGN_LEFT);

		ct.addText(new Phrase(text, font));
		ct.go();
	}
	
	
	private void op_drawRect(Map map, List infos, PdfContentByte cb) throws Exception
	{
		int[] rect = findRect(map, part(infos,1));
		Color color = findColor(map, part(infos,2));
		
		int x = rect[0];
		int y = rect[1];
		int w = rect[2];
		int h = rect[3];
		
		cb.setColorStroke(color);
		cb.moveTo(x,y);
		cb.lineTo(x+w,y);
		cb.lineTo(x+w,y+h);
		cb.lineTo(x,y+h);
		cb.lineTo(x,y);
		cb.stroke();
	}
	
	
	private void op_fillRect(Map map, List infos, PdfContentByte cb) throws Exception
	{
		int[] rect = findRect(map, part(infos,1));
		Color color = findColor(map, part(infos,2));
		
		int x = rect[0];
		int y = rect[1];
		int w = rect[2];
		int h = rect[3];
		
		cb.setColorStroke(color);
		cb.setColorFill(color);
		cb.moveTo(x,y);
		cb.lineTo(x+w,y);
		cb.lineTo(x+w,y+h);
		cb.lineTo(x,y+h);
		cb.lineTo(x,y);
		cb.fillStroke();
	}
	
	
	private void op_drawImage(Map map, List infos, PdfContentByte cb) throws Exception
	{
		int[] rect = findRect(map, part(infos,1));
		Image image = findImage(map, part(infos,2));
		String align = findImageAlign(map, part(infos,3));
		
		float x = (float) rect[0];
		float y = (float) rect[1];
		float w = (float) rect[2];
		float h = (float) rect[3];
		
		image.scaleToFit(w,h);

		float h0 = image.getScaledHeight();
		float w0 = image.getScaledWidth();
		
		boolean n = align.contains("n");
		boolean s = align.contains("s");
		boolean e = align.contains("e");
		boolean o = align.contains("w");
		
		float x1 = o ? x : (e ? x+w-w0 : x+(w-w0)/2);
		float y1 = s ? y : (n ? y+h-h0 : y+(h-h0)/2);

		image.setAbsolutePosition(x1,y1);
		cb.getPdfDocument().add(image);
	}
	
	
	private void op_nextPage(Map map, List infos, PdfContentByte cb)
	{
		cb.getPdfDocument().newPage();
	}
	
	private void op_execute(Map map, List infos) throws Exception
	{
		E exec = findExecute(map, part(infos,1));
		exec.e();
	}
	
	
	
	/*
	* FIND
	*/
	
	private List findInfos(Map map, Object input) throws Exception
	{
		if(input==null) return null;
		if(input instanceof List) return (List) input;
		if(input instanceof String) return Arrays.asList(((String) input).split(" "));
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	private List findList(Map map, Object info) throws Exception
	{
		if(info==null) return new ArrayList();
		if(info instanceof List) return (List) info;
		if(info instanceof String)
		{
			String s = (String) info;
			if(s.startsWith("@")) return findList(map, handleAlias(map, s));
			return Arrays.asList(((String) info).split(";"));
		}
		throw new Exception("Invalid data type: "+info.getClass().getName());
	}
	
	private List findPath(Map map, Object info) throws Exception
	{
		if(info==null) return new ArrayList();
		if(info instanceof List) return (List) info;
		if(info instanceof String)
		{
			String s = (String) info;
			if(s.startsWith("@")) return findPath(map, handleAlias(map, s));
		
			String[] nn = s.split(";");
			List path = new ArrayList();
			for(String n : nn) path.add(findPoint(map, n));
			return path;
		}
		throw new Exception("Invalid data type: "+info.getClass().getName());
	}
	
	private Color findColor(Map map, Object info) throws Exception
	{
		if(info==null) return DEFAULT_COLOR;
		if(info instanceof Color) return (Color) info;
		if(info instanceof String) 
		{
			String s = (String) info;
			if(s.startsWith("@")) return findColor(map, handleAlias(map, s));
			
			return (Color) findColor.t(info);
		}
		throw new Exception("Invalid data type: "+info.getClass().getName());
		
	}
	
	private Font findFont(Map map, Object info) throws Exception
	{
		if(info==null) return DEFAULT_FONT;
		if(info instanceof Font) return (Font) info;
		if(info instanceof String) 
		{
			String s = (String) info;
			if(s.startsWith("@")) return findFont(map, handleAlias(map, s));
		
			String[] n = s.split(" ");
			
			String nameInfo = (String) part(n,0);
			String styleInfo = (String) part(n,1);
			String sizeInfo = (String) part(n,2);
			String colorInfo = (String) part(n,3);
			
			int name = nameInfo!=null ? stringToFontName(nameInfo) : DEFAULT_FONTNAME;
			int size = sizeInfo!=null ? Integer.parseInt(sizeInfo) : DEFAULT_SIZE;
			int style = styleInfo!=null ? stringToFontStyle(styleInfo) : DEFAULT_STYLE;
			Color color = findColor(map, colorInfo);
	        
			return new Font(name, (float) size, style, color);
		}
		throw new Exception("Invalid data type: "+info.getClass().getName());
	}
	
	private int[] findPoint(Map map, Object info) throws Exception
	{
		if(info==null) return null;
		if(info instanceof int[]) return (int[]) info;
		if(info instanceof String) 
		{
			String s = (String) info;
			if(s.startsWith("@")) return findPoint(map, handleAlias(map, s));
			
			String[] n = s.split("\\-");
			return new int[]{toInt(n[0]), toInt(n[1])};
		}
		throw new Exception("Invalid data type: "+info.getClass().getName());
	}
	
	private int[] findRect(Map map, Object info) throws Exception
	{
		if(info==null) return null;
		if(info instanceof int[]) return (int[]) info;
		if(info instanceof String) 
		{
			String s = (String) info;
			if(s.startsWith("@")) return findRect(map, handleAlias(map, s));
			
			String[] n = s.split("\\-");
			return new int[]{toInt(n[0]), toInt(n[1]), toInt(n[2]), toInt(n[3])};
		}
		throw new Exception("Invalid data type: "+info.getClass().getName());
	}
	
	private String findText(Map map, Object info) throws Exception
	{
		if(info==null) return null;
		if(info instanceof Number) return ""+info; 
		if(info instanceof Boolean) return ""+info; 
		if(info instanceof String) 
		{
			String s = (String) info;
			if(s.startsWith("@")) return findText(map, handleAlias(map, s));
			return s;
		}
		throw new Exception("Invalid data type: "+info.getClass().getName());
	}
	
	private File findFile(Map map, Object info) throws Exception
	{
		if(info==null) return null;
		if(info instanceof File) return (File) info;
		if(info instanceof String) 
		{
			String s = (String) info;
			if(s.startsWith("@")) return findFile(map, handleAlias(map, s));
			return new File(s);
		}
		throw new Exception("Invalid data type: "+info.getClass().getName());
	}
	
	private E findExecute(Map map, Object info) throws Exception
	{
		if(info==null) return null;
		if(info instanceof E) return (E) info;
		if(info instanceof String) 
		{
			String s = (String) info;
			if(s.startsWith("@")) return findExecute(map, handleAlias(map, s));
			throw new Exception("Invalid value for Execute: "+s);
		}
		throw new Exception("Invalid data type: "+info.getClass().getName());
	}
	
	private Image findImage(Map map, Object info) throws Exception
	{
		File file = findFile(map, info);
		return fileToImage(file);
	}
	
	
	private String findImageAlign(Map map, Object info) throws Exception
	{
		if(info==null) return "";
		if(info instanceof String) 
		{
			String s = (String) info;
			if(s.startsWith("@")) return findImageAlign(map, handleAlias(map, s));
			return s.toLowerCase();
		}
		throw new Exception("Invalid data type: "+info.getClass().getName());
	}
	
	
	
	
	
	
	
	private int toInt(String info)
	{return Integer.parseInt(info);}
	
	
	
	private Object part(List list, int index)
	{return index<list.size() ? list.get(index): null;}
	
	private Object part(Object[] n, int index)
	{return index<n.length ? n[index]: null;}
	
	
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
	
	private Object get(Map map, String key, Object defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return map.get(key);
	}
	
	private Object handleAlias(Map map, String info) throws Exception
	{
		if(!info.startsWith("@")) return null;
		return get(map, info.substring(1));
	}
	
	private Image fileToImage(File file) throws Exception
	{
		if(file==null) return null;
		if(!file.exists()) return null;
		return Image.getInstance(file.getAbsolutePath());
	}
	
	private Rectangle stringToPage(String s) throws Exception
	{
		if(s.equals("_11X17")) return PageSize._11X17;
		if(s.equals("_11X17*")) return PageSize._11X17.rotate();
		
		if(s.equals("A0")) return PageSize.A0;
		if(s.equals("A0*")) return PageSize.A0.rotate();
		
		if(s.equals("A1")) return PageSize.A1;
		if(s.equals("A1*")) return PageSize.A1.rotate();
		
		if(s.equals("A2")) return PageSize.A2;
		if(s.equals("A2*")) return PageSize.A2.rotate();
		
		if(s.equals("A3")) return PageSize.A3;
		if(s.equals("A3*")) return PageSize.A3.rotate();
		
		if(s.equals("A4")) return PageSize.A4;
		if(s.equals("A4*")) return PageSize.A4.rotate();
		
		if(s.equals("A5")) return PageSize.A5;
		if(s.equals("A5*")) return PageSize.A5.rotate();
		
		if(s.equals("A6")) return PageSize.A6;
		if(s.equals("A6*")) return PageSize.A6.rotate();
		
		if(s.equals("A7")) return PageSize.A7;
		if(s.equals("A7*")) return PageSize.A7.rotate();
		
		if(s.equals("A8")) return PageSize.A8;
		if(s.equals("A8*")) return PageSize.A8.rotate();
		
		if(s.equals("A9")) return PageSize.A9;
		if(s.equals("A9*")) return PageSize.A9.rotate();
		
		if(s.equals("A10")) return PageSize.A10;
		if(s.equals("A10*")) return PageSize.A10.rotate();
		
		if(s.equals("ARCH_A")) return PageSize.ARCH_A;
		if(s.equals("ARCH_A*")) return PageSize.ARCH_A.rotate();
		
		if(s.equals("ARCH_B")) return PageSize.ARCH_B;
		if(s.equals("ARCH_B*")) return PageSize.ARCH_B.rotate();
		
		if(s.equals("ARCH_C")) return PageSize.ARCH_C;
		if(s.equals("ARCH_C*")) return PageSize.ARCH_C.rotate();
		
		if(s.equals("ARCH_D")) return PageSize.ARCH_D;
		if(s.equals("ARCH_D*")) return PageSize.ARCH_D.rotate();
		
		if(s.equals("ARCH_E")) return PageSize.ARCH_E;
		if(s.equals("ARCH_E*")) return PageSize.ARCH_E.rotate();
		
		if(s.equals("B0")) return PageSize.B0;
		if(s.equals("B0*")) return PageSize.B0.rotate();
		
		if(s.equals("B1")) return PageSize.B1;
		if(s.equals("B1*")) return PageSize.B1.rotate();
		
		if(s.equals("B2")) return PageSize.B2;
		if(s.equals("B2*")) return PageSize.B2.rotate();
		
		if(s.equals("B3")) return PageSize.B3;
		if(s.equals("B3*")) return PageSize.B3.rotate();
		
		if(s.equals("B4")) return PageSize.B4;
		if(s.equals("B4*")) return PageSize.B4.rotate();
		
		if(s.equals("B5")) return PageSize.B5;
		if(s.equals("B5*")) return PageSize.B5.rotate();
		
		if(s.equals("B6")) return PageSize.B6;
		if(s.equals("B6*")) return PageSize.B6.rotate();
		
		if(s.equals("B7")) return PageSize.B7;
		if(s.equals("B7*")) return PageSize.B7.rotate();
		
		if(s.equals("B8")) return PageSize.B8;
		if(s.equals("B8*")) return PageSize.B8.rotate();
		
		if(s.equals("B9")) return PageSize.B9;
		if(s.equals("B9*")) return PageSize.B9.rotate();
		
		if(s.equals("B10")) return PageSize.B10;
		if(s.equals("B10*")) return PageSize.B10.rotate();
		
		
		throw new Exception("Invalid page info: "+s);
	}
	
	
	
    
	private int stringToFontStyle(String info)
	{
		if(info.toLowerCase().equals("plain")) return 0;
		if(info.toLowerCase().equals("bold")) return 1;
		if(info.toLowerCase().equals("italic")) return 2;
		if(info.toLowerCase().equals("bold|italic")) return 3;
		if(info.toLowerCase().equals("italic|bold")) return 3;
		return Integer.parseInt(info);
	}
	
    
	private int stringToFontName(String info)
	{
		if(info.toLowerCase().equals("courier")) return Font.COURIER;
		if(info.toLowerCase().equals("symbol")) return Font.SYMBOL;
		if(info.toLowerCase().equals("times_roman")) return Font.TIMES_ROMAN;
		if(info.toLowerCase().equals("helvetica")) return Font.HELVETICA;
		return DEFAULT_FONTNAME;
	}
}