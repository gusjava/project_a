package a.entity.gus06.file.video.dsj.infomap;

import a.framework.*;
import java.io.File;
import de.humatic.dsj.DSMovie;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191201";}
	
	public static final String API = "dsj";
	
	public static final String KEY_API = "api";
	public static final String KEY_DURATION = "duration";


	private Service arrayToString;
	private Service buildDSMovie;

	public EntityImpl() throws Exception
	{
		arrayToString = Outside.service(this,"gus06.tostring.array.join.semicolon");
		buildDSMovie = Outside.service(this,"gus06.file.video.dsj.build.dsmovie");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		DSMovie movie = (DSMovie) buildDSMovie.t(file);
		
		String streamType = movie.getStreamType();
		String audioCodec = movie.getAudioCodec();
		String videoCodec = movie.getVideoCodec();
		
		String timeFormat = ""+movie.getTimeFormat();
		String duration = ""+movie.getDuration();
		String frameRate = ""+movie.getFrameRate();
		String bitDepth = ""+movie.getBitDepth();
		String audioProps = (String) arrayToString.t(movie.getAudioProperties());
		String aspectRatio = (String) arrayToString.t(movie.getAspectRatio());
		String mediaDim = (String) arrayToString.t(movie.getMediaDimension());
		
		movie.stop();
		movie.dispose();
		
		Map map = new HashMap();
		map.put(KEY_API,API);
		
		map.put("streamType",streamType);
		map.put("audioCodec",audioCodec);
		map.put("videoCodec",videoCodec);
		
		map.put("timeFormat",timeFormat);
		map.put(KEY_DURATION,duration);
		map.put("frameRate",frameRate);
		map.put("bitDepth",bitDepth);
		map.put("audioProps",audioProps);
		map.put("aspectRatio",aspectRatio);
		map.put("mediaDim",mediaDim);
		
//		System.out.println(map);
		return map;
	}
}