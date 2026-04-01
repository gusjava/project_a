package a.entity.gus06.file.wav.extract.prop;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import javax.sound.sampled.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250527";}
	
	public static final String KEY_TYPE = "type";
	public static final String KEY_CHANNELS = "channels";
	public static final String KEY_ENCODING = "encoding";
	public static final String KEY_FRAME_RATE = "frame_rate";
	public static final String KEY_SAMPLE_RATE = "sample_rate";
	public static final String KEY_FRAME_SIZE = "frame_size";
	public static final String KEY_SAMPLE_SIZE = "sample_size";
	public static final String KEY_DURATION = "duration";
	public static final String KEY_BIG_ENDIAN = "big_endian";
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Map map = new HashMap();
		if(file==null || !file.isFile()) return map;
		
		AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
		AudioFormat format = fileFormat.getFormat();
		
		long length = file.length();
		
		boolean isBigEndian = format.isBigEndian();
		float sampleRate = format.getSampleRate();
		float frameRate = format.getFrameRate();
		int channels = format.getChannels();
		int frameSize = format.getFrameSize();
		int sampleSize = format.getSampleSizeInBits();
		AudioFileFormat.Type type = fileFormat.getType();
		AudioFormat.Encoding encoding = format.getEncoding();
		double duration = (double) length / (sampleRate * frameSize);
		
		map.put(KEY_BIG_ENDIAN, ""+isBigEndian);
		map.put(KEY_CHANNELS, ""+channels);
		map.put(KEY_FRAME_RATE, ""+frameRate);
		map.put(KEY_SAMPLE_RATE, ""+sampleRate);
		map.put(KEY_FRAME_SIZE, ""+frameSize);
		map.put(KEY_SAMPLE_SIZE, ""+sampleSize);
		map.put(KEY_TYPE, ""+type);
		map.put(KEY_ENCODING, ""+encoding);
		map.put(KEY_DURATION, ""+duration);
		
		return map;
	}
}