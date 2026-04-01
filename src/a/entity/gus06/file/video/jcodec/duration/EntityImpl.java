package a.entity.gus06.file.video.jcodec.duration;

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

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201030";}
	

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
			
			if(dm.getVideoTracks().isEmpty()) return null;
			
			DemuxerTrack vt = dm.getVideoTracks().get(0);
			DemuxerTrackMeta vtMeta = vt.getMeta();
			
			if(vtMeta==null) return null;
			
			long duration = (long)(vtMeta.getTotalDuration()*1000);
			dm.close();
			
			return Long.valueOf(duration);
		}
	}
}