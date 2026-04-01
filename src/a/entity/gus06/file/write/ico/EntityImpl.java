package a.entity.gus06.file.write.ico;

import java.awt.image.BufferedImage;
import java.io.File;
import a.framework.*;
import java.io.FileOutputStream;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150607";}

	private Service toBufferedImage;

	public EntityImpl() throws Exception
	{toBufferedImage = Outside.service(this,"gus06.find.bufferedimage");}


	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);

		File file = (File) t[0];
		Object data = t[1];
		
		if(data instanceof Object[])	writeArray(file,(Object[]) data);
		else if(data instanceof List)	writeList(file,(List) data);
		else writeImage(file,data);
	}
	
	
	
	
	
	private void writeArray(File file, Object[] array) throws Exception
	{
		List<BufferedImage> l = new ArrayList<>();
		for(Object image : array) l.add((BufferedImage) toBufferedImage.t(image));
		write(file,l);
	}
	
	private void writeList(File file, List list) throws Exception
	{
		List<BufferedImage> l = new ArrayList<>();
		for(Object elem : list) l.add((BufferedImage) toBufferedImage.t(elem));
		write(file,l);
	}
	
	private void writeImage(File file, Object data) throws Exception
	{
		List<BufferedImage> l = new ArrayList<>();
		l.add((BufferedImage) toBufferedImage.t(data));
		write(file,l);
	}
	
	
	
	
	
	private void write(File file, List<BufferedImage> list) throws Exception
	{
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		FileOutputStream fos = new FileOutputStream(file);
		ICOEncoder.write(list,fos);
		fos.close();
	}
}
