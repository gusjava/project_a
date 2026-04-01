package a.entity.gus06.file.runtask.image.display.infos;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250301";}
	
	public static final String TITLE = "Image info";


	private Service freqMap;
	private Service showData;
	private Service readImage;

	public EntityImpl() throws Exception
	{
		freqMap = Outside.service(this,"gus06.awt.bufferedimage.color.freqmap.rgba");
		showData = Outside.service(this,"gus06.swing.frame.show.data");
		readImage = Outside.service(this,"gus06.file.read.image.generic");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		
		BufferedImage image = (BufferedImage) readImage.t(file);
		
		ColorModel colorModel = image.getColorModel();
		
		Map infos = new HashMap();
		infos.put("colorModel class", colorModel.getClass());
		infos.put("colorModel hasAlpha", colorModel.hasAlpha());
		
		Map freq = (Map) freqMap.t(image);
		
		Map data = new HashMap();
		data.put("infos", infos);
		data.put("freq", freq);
		
		if(progress!=null) ((E)progress).e();
		showData.v(TITLE, data);
	}
}
