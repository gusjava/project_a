package a.entity.gus06.file.video.jcodec.mosaic;

import a.framework.*;
import java.io.File;
import java.awt.image.BufferedImage;
import org.jcodec.common.Codec;
import org.jcodec.common.Demuxer;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.FileChannelWrapper;
import org.jcodec.common.SeekableDemuxerTrack;
import org.jcodec.common.DemuxerTrackMeta;
import org.jcodec.common.DemuxerTrack;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.jcodec.api.specific.AVCMP4Adaptor;
import org.jcodec.api.specific.ContainerAdaptor;
import org.jcodec.api.FrameGrab;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200114";}
	

	private Service merge;
	private Service findWidth;
	private Service resizeWidth;
	private Service buildDemuxer;

	public EntityImpl() throws Exception
	{
		merge = Outside.service(this,"gus06.awt.bufferedimage.merge.grid");
		findWidth = Outside.service(this,"gus06.file.image.width.rebuild");
		resizeWidth = Outside.service(this,"gus06.awt.bufferedimage.resizewidth");
		buildDemuxer = Outside.service(this,"gus06.file.video.jcodec.build.demuxer");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object widthObj = o[1];
		Integer nb = (Integer) o[2];
		
		BufferedImage[][] im = new BufferedImage[nb][nb];
		
		try(FileChannelWrapper ch = NIOUtils.readableChannel(file))
		{
			Demuxer dm = (Demuxer) buildDemuxer.t(new Object[]{file,ch});
			if(dm==null) throw new Exception("Null demuxer returned");
			if(dm.getVideoTracks().isEmpty()) throw new Exception("No video track found");
				
			DemuxerTrack track = dm.getVideoTracks().get(0);
			DemuxerTrackMeta meta = track.getMeta();
			
			if(!(track instanceof SeekableDemuxerTrack))
				throw new Exception("Unsupported demuxerTrack class: "+track.getClass());
			
			SeekableDemuxerTrack seekableTrack = (SeekableDemuxerTrack) track;
			
			Codec codec = meta.getCodec();
			
			ContainerAdaptor decoder = new AVCMP4Adaptor(meta);
			FrameGrab frameGrab = new FrameGrab(seekableTrack,decoder);
			
			int widthTotal = meta.getVideoCodecMeta().getSize().getWidth();
			int totalFrames = meta.getTotalFrames();
			
			
			int width = (int) findWidth.t(new Object[]{widthTotal,widthObj});
			
			int dt = (int) ((double)totalFrames/(double) (nb*nb+1));
			int dw = (int) ((double)width/(double) nb);
			
			int pos = 0;
			for(int i=0;i<nb;i++)
			for(int j=0;j<nb;j++)
			{
				pos += dt;
				
				Picture picture = frameGrab.seekToFramePrecise(pos).getNativeFrame();
				im[i][j] = AWTUtil.toBufferedImage(picture);
			}
			BufferedImage output = (BufferedImage) merge.t(im);
			dm.close();
			
			return resizeWidth.t(new Object[]{output,width});
		}
	}
}