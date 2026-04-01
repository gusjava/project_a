package a.entity.gus06.file.video.jcodec.capture;

import a.framework.*;
import java.io.File;
import java.awt.image.BufferedImage;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.FileChannelWrapper;
import org.jcodec.common.DemuxerTrackMeta;
import org.jcodec.common.Demuxer;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.jcodec.api.FrameGrab;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200114";}


	private Service findWidth;
	private Service findPos;
	private Service resizeWidth;
	private Service buildDemuxer;

	public EntityImpl() throws Exception
	{
		findWidth = Outside.service(this,"gus06.file.image.width.rebuild");
		findPos = Outside.service(this,"gus06.file.video.duration.rebuild");
		resizeWidth = Outside.service(this,"gus06.awt.bufferedimage.resizewidth");
		buildDemuxer = Outside.service(this,"gus06.file.video.jcodec.build.demuxer");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object posObj = o[1];
		Object widthObj = o[2];
		
		
		BufferedImage image = null;
		
		try(FileChannelWrapper ch = NIOUtils.readableChannel(file))
		{
			Demuxer dm = (Demuxer) buildDemuxer.t(new Object[]{file,ch});
			DemuxerTrackMeta vtMeta = dm.getVideoTracks().get(0).getMeta();
			
			int totalWidth = vtMeta.getVideoCodecMeta().getSize().getWidth();
			int totalFrames = vtMeta.getTotalFrames();
			int totalDuration = (int) (vtMeta.getTotalDuration()*1000);
			
			FrameGrab frameGrab = FrameGrab.createFrameGrab(ch);
			
			int pos = (int) findPos.t(new Object[]{totalDuration,posObj});
			int width = (int) findWidth.t(new Object[]{totalWidth,widthObj});
			
			Picture picture = frameGrab.seekToFramePrecise(pos).getNativeFrame();
			image = AWTUtil.toBufferedImage(picture);
			image = (BufferedImage) resizeWidth.t(new Object[]{image,width});
			
			dm.close();
		}
		return image;
	}
	
	
	private int findFramePos(Object posObj, int totalFrames, int totalDuration)
	{
		return 0;
	}
	
}