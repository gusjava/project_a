package a.entity.gus06.file.video.jcodec.infomap;

import a.framework.*;
import java.io.File;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.FileChannelWrapper;
import org.jcodec.common.Demuxer;
import org.jcodec.common.DemuxerTrack;
import org.jcodec.common.DemuxerTrackMeta;
import org.jcodec.common.VideoCodecMeta;
import org.jcodec.common.Codec;
import org.jcodec.common.model.Size;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200114";}
	
	public static final String API = "jcodec";
	
	public static final String KEY_API = "api";
	public static final String KEY_DURATION = "duration";


	private Service buildDemuxer;

	public EntityImpl() throws Exception
	{
		buildDemuxer = Outside.service(this,"gus06.file.video.jcodec.build.demuxer");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		try(FileChannelWrapper ch = NIOUtils.readableChannel(file))
		{
			Demuxer dm = (Demuxer) buildDemuxer.t(new Object[]{file,ch});
			if(dm==null) throw new Exception("Null demuxer found for file: "+file);
			
			Map map = new HashMap();
			map.put(KEY_API,API);
			
			if(!dm.getVideoTracks().isEmpty())
			{
				DemuxerTrack vt = dm.getVideoTracks().get(0);
				DemuxerTrackMeta vtMeta = vt.getMeta();
				Codec vCodec = vtMeta!=null ? vtMeta.getCodec() : null;
				VideoCodecMeta vcm = vtMeta!=null ? vtMeta.getVideoCodecMeta() : null;
				Size vSize = vcm!=null ? vcm.getSize() : null;
			
				String frameRate = vtMeta!=null ? (""+(vtMeta.getTotalFrames()/vtMeta.getTotalDuration())) : "?";
				map.put("frameRate",frameRate);
				
				String streamType = "streamType?";
				map.put("streamType",streamType);
				
				String videoCodec = ""+vCodec;
				map.put("videoCodec",videoCodec);
				
				String duration = vtMeta!=null ? (""+(int)(vtMeta.getTotalDuration()*1000)) : "?";
				map.put(KEY_DURATION,duration);
				
				String timeFormat = "timeFormat?";
				map.put("timeFormat",timeFormat);
				
				String bitDepth = "bitDepth?";
				map.put("bitDepth",bitDepth);
				
				String aspectRatio = "aspectRatio?";
				map.put("aspectRatio",aspectRatio);
				
				String mediaDim = vSize!=null ? (vSize.getWidth()+";"+vSize.getHeight()) : "?";
				map.put("mediaDim",mediaDim);
			}
			else
			{
				map.put("videoTrack","Track not found");
			}
			
			
			if(!dm.getAudioTracks().isEmpty())
			{
				DemuxerTrack at = dm.getAudioTracks().get(0);
				DemuxerTrackMeta atMeta = at!=null ? at.getMeta() : null;
				Codec aCodec = atMeta!=null ? atMeta.getCodec() : null;
				
				String audioCodec = ""+aCodec;
				map.put("audioCodec",audioCodec);
				
				String audioProps = "audioProps?";
				map.put("audioProps",audioProps);
			}
			else
			{
				map.put("audioTrack","Track not found");
			}
			
			dm.close();
			return map;
		}
	}
}