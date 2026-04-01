package a.entity.gus06.image.metadata.jpeg.extraction1.readsegments;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180218";}

	private static final byte SEGMENT_SOS = (byte)0xDA;
	private static final byte MARKER_EOI = (byte)0xD9;


	private Map map;
	

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		map = new HashMap();
		
		FileInputStream is = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(is);
		
		if (!isValidJpegHeaderBytes(bis))
			throw new Exception("Invalid JPEG file: "+file.getAbsolutePath());
		
		int offset = 2;
		boolean hasMore = true;
		
		do
		{
			byte identifier = read(bis);
			if ((identifier & 0xFF) != 0xFF)
				throw new Exception("Expected jpeg segment start identifier 0xFF at offset "+offset+", not 0x"+hexa(identifier));
			
			offset++;
			// next byte is <segment-marker>
			byte marker = read(bis);
			offset++;
			// next 2-bytes are <segment-size>: [high-byte] [low-byte]
			byte[] lengthBytes = read(bis,2);
			offset += 2;
			int length = ((lengthBytes[0] << 8) & 0xFF00) | (lengthBytes[1] & 0xFF);
			// segment length includes size bytes, so subtract 2
			length -= 2;
			if(length > bis.available()) throw new Exception("Segment size would extend beyond file stream length");
			if(length < 0) throw new Exception("Segment size would be less than zero");
			
			byte[] segmentBytes = read(bis,length);
			offset += length;
			
			if((marker & 0xFF) == (SEGMENT_SOS & 0xFF))
			{
				// The 'Start-Of-Scan' segment's length doesn't include the image data, instead would
				// have to search for the two bytes: 0xFF 0xD9 (EOI).
				// It comes last so simply return at this point
				hasMore = false;
			}
			else if((marker & 0xFF) == (MARKER_EOI & 0xFF))
			{
				// the 'End-Of-Image' segment -- this should never be found in this fashion
				hasMore = false;
			}
			else addSegment(marker, segmentBytes);
		}
		while(hasMore);
		
		bis.close();
		return map;
	}
	
	
	private void addSegment(byte marker, byte[] data)
	{
		Byte key = Byte.valueOf(marker);
		if(!map.containsKey(key))
			map.put(key,new ArrayList());
		ArrayList list = (ArrayList) map.get(key);
		list.add(data);
	}
	
	private boolean isValidJpegHeaderBytes(InputStream is) throws IOException
	{
		byte[] header = new byte[2];
		is.read(header,0,2);
		return (header[0] & 0xFF) == 0xFF && (header[1] & 0xFF) == 0xD8;
	}
	
	
	private byte read(InputStream is) throws IOException
	{return (byte)(is.read() & 0xFF);}
	
	
	private byte[] read(InputStream is, int length) throws IOException
	{
		byte[] b = new byte[length];
		is.read(b,0,length);
		return b;
	}
	
	
	private String hexa(byte b)
	{return Integer.toHexString(b & 0xFF);}
}
