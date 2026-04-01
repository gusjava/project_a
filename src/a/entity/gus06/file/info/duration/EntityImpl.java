package a.entity.gus06.file.info.duration;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250512";}


	private Service isVideo;
	private Service isAudio;
	private Service videoDuration;
	
	
	public EntityImpl() throws Exception
	{
		isVideo = Outside.service(this,"gus06.file.filter.mime.issubtype.video");
		isAudio = Outside.service(this,"gus06.file.filter.mime.issubtype.audio");
		videoDuration = Outside.service(this,"gus06.file.video.generic.duration");
	}



	public Object t(Object obj) throws Exception
	{
		if(isVideo.f(obj)) return videoDuration.t(obj);
		
		// audio
		return null;
	}
}