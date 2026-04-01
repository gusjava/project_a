package a.entity.gus06.file.video.jcodec.build.demuxer;

import a.framework.*;
import java.io.File;
import org.jcodec.common.Format;
import org.jcodec.common.JCodecUtil;
import org.jcodec.common.Demuxer;
import org.jcodec.common.io.FileChannelWrapper;
import org.jcodec.containers.mp4.demuxer.MP4Demuxer;
import org.jcodec.containers.mkv.demuxer.MKVDemuxer;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201002";}
	

	private Service getMime;

	public EntityImpl() throws Exception
	{
		getMime = Outside.service(this,"gus06.file.mime.tika.detect.asstring");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		FileChannelWrapper ch = (FileChannelWrapper) o[1];
		
		return handle(file,ch);
	}
	
	
	
	
	private Demuxer handle(File file, FileChannelWrapper ch) throws Exception
	{
		Format format1 = JCodecUtil.detectFormatChannel(ch);
		if(format1!=null) {
			return JCodecUtil.createDemuxer(format1, file);
		}
		Format format2 = JCodecUtil.detectFormat(file);
		if(format2!=null) {
			return JCodecUtil.createDemuxer(format2, file);
		}
		return handle1(file,ch);
	}
	
	
	
	private Demuxer handle1(File file, FileChannelWrapper ch) throws Exception
	{
		String mime = (String) getMime.t(file);
		
		if(mime.equals("video/mp4"))
			return MP4Demuxer.createMP4Demuxer(ch);
			
//		if(mime.equals("video/x-matroska"))
//			return new MKVDemuxer(ch);
			
		throw new Exception("Unsupported mime type: "+mime);
	}
}
