package a.entity.gus06.file.write.video.mp4.h264;

import a.framework.*;
import org.jcodec.api.awt.AWTSequenceEncoder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250825";}
	
	public static final String KEY_FRAMES = "frames";
	public static final String KEY_FRAME_NUMBER = "frame_number";
	public static final String KEY_FRAME_GENERATOR = "frame_generator";
	public static final String KEY_FPS = "fps";


	private Service fromMap;
	
	public EntityImpl() throws Exception
	{
		fromMap = Outside.service(this,"gus06.awt.bufferedimage.build.frommap2");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Map map = (Map) o[1];
		
		if(map.containsKey(KEY_FRAMES))
		{write1(file,map);return;}
		
		if(map.containsKey(KEY_FRAME_GENERATOR)) 
		{write2(file,map);return;}
		
		throw new Exception("Invalid map for video generation");
	}
	
	
	private void write1(File file, Map map) throws Exception
	{
		List frames = (List) map.get(KEY_FRAMES);
		int fps = toInt(map.get(KEY_FPS));
		
		AWTSequenceEncoder encoder = AWTSequenceEncoder.createSequenceEncoder(file, fps);
		int nb = frames.size();
		for(int i=0;i<nb;i++)
		{
			BufferedImage img = toImg(frames.get(i));
			encoder.encodeImage(img);
		}
		encoder.finish();
	}
	
	
	private void write2(File file, Map map) throws Exception
	{
		int number = toInt(map.get(KEY_FRAME_NUMBER));
		Object generator = map.get(KEY_FRAME_GENERATOR);
		int fps = toInt(map.get(KEY_FPS));
		
		AWTSequenceEncoder encoder = AWTSequenceEncoder.createSequenceEncoder(file, fps);
		for(int i=0;i<number;i++)
		{
			BufferedImage img = toImg(generator);
			encoder.encodeImage(img);
		}
		encoder.finish();
	}
	
	
	private int toInt(Object obj) throws Exception
	{return Integer.parseInt(""+obj);}
	
	
	private BufferedImage toImg(Object obj) throws Exception
	{
		if(obj instanceof BufferedImage) return (BufferedImage) obj;
		if(obj instanceof Map) return (BufferedImage) fromMap.t(obj);
		if(obj instanceof G) return toImg2(((G) obj).g());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private BufferedImage toImg2(Object obj) throws Exception
	{
		if(obj instanceof BufferedImage) return (BufferedImage) obj;
		if(obj instanceof Map) return (BufferedImage) fromMap.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}