package a.entity.gus06.sys.pdfgeneration1.engine;

import a.framework.*;
import java.io.File;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20200401";}
	
	public static final int NUMBER = 10;
	public static final int PAGE_MARGIN = 6;
	public static final int FOOTER = 12;
	public static final int IMAGE_MARGIN = 2;
	public static final int LEADING = 10;
	
	public static final String KEY_IMAGE_DATA = "image_data";
	
	
	private File pdfFile;
	private List list;
	
	private Document document;
	private PdfContentByte cb;

	private float W;
	private float H;
	
	private float w;
	private float h;
	
	private float w_;
	private float h_;
	
	private float x0;
	private float y0;
	
	private float x1;
	private float y1;
	
	private float dx;
	private float dy;
	
	private float x;
	private float y;
	
	private Rectangle pageSize;



	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		list = (List) obj;
		
		if(pdfFile.exists()) pdfFile.delete();
		FileOutputStream fos = new FileOutputStream(pdfFile);
		
		document = new Document(PageSize.A4,PAGE_MARGIN,PAGE_MARGIN,PAGE_MARGIN,PAGE_MARGIN);
		PdfWriter writer = PdfWriter.getInstance(document,fos);
		document.open();
		cb = writer.getDirectContent();
		
		W = PageSize.A4.getWidth()-2*PAGE_MARGIN;
		H = PageSize.A4.getHeight()-2*PAGE_MARGIN-FOOTER;
		
		w = W/NUMBER;
		h = H/NUMBER;
		
		w_ = w-2*IMAGE_MARGIN;
		h_ = h-2*IMAGE_MARGIN;
		
		x0 = PAGE_MARGIN;
		y0 = PAGE_MARGIN+FOOTER+H-h;
		
		x1 = x0+W;
		y1 = y0-H;
		
		dx = w;
		dy = -h;
		
		x = x0;
		y = y0;

		for(int i=0;i<list.size();i++)
		{
			Map map = (Map) list.get(i);
			byte[] b = findData(map);
			
			addImage(b);
			updatePosition();
		}
		
		document.close();
		writer.close();
		fos.close();
	}
	
	
	private byte[] findData(Map map) throws Exception
	{
		Object obj = map.get(KEY_IMAGE_DATA);
		if(obj instanceof byte[]) return (byte[]) obj;
		if(obj instanceof G) return (byte[]) ((G)obj).g();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("pdfFile")) {pdfFile = (File) obj;return;}
	}
	
	
	private void addImage(byte[] b) throws Exception
	{
		Image image = Image.getInstance(b);
		image.scaleToFit(w_,h_);
		
		float x_ = x+IMAGE_MARGIN;
		float y_ = y+IMAGE_MARGIN;

		image.setAbsolutePosition(x_,y_);
		document.add(image);
	}
	
	
	
	private void updatePosition() throws Exception
	{
		x += dx;
		if(!equals(x,x1)) return;

		x = x0;
		y += dy;
		if(!equals(y,y1)) return;

		y = y0;
		document.newPage();
	}
	
	
	private boolean equals(float f1, float f2)
	{return Math.abs(f1-f2)<0.01;}
}
