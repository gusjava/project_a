package a.entity.gus06.file.video.dsj.build.dsmovie;

import a.framework.*;
import java.io.File;
import de.humatic.dsj.DSMovie;
import de.humatic.dsj.DSFiltergraph;
import de.humatic.dsj.DSJException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200114";}
	

	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		try
		{
			return new DSMovie(file.getAbsolutePath(),DSFiltergraph.DD7,null);
		}
		catch(DSJException e)
		{
			int errCode = e.getErrorCode();
			String errMessage = codeToMessage(errCode);
			throw new Exception("Failed to read video file: "+file+" ["+errMessage+"]",e);
		}
	}
	
	
	
	private String codeToMessage(int code)
	{
		if(code==DSJException.E_ASX) return "E_ASX";
		if(code==DSJException.E_BAD_RETURN) return "E_BAD_RETURN";
		if(code==DSJException.E_CANCELLED) return "E_CANCELLED";
		if(code==DSJException.E_CANT_ADD_FILTER) return "E_CANT_ADD_FILTER";
		if(code==DSJException.E_CANT_ADD_SRC) return "E_CANT_ADD_SRC";
		if(code==DSJException.E_CANT_CONNECT) return "E_CANT_CONNECT";
		if(code==DSJException.E_CANT_LOCK) return "E_CANT_LOCK";
		if(code==DSJException.E_CANT_OPEN) return "E_CANT_OPEN";
		if(code==DSJException.E_CANT_PUMP_AUDIO) return "E_CANT_PUMP_AUDIO";
		if(code==DSJException.E_CANT_RESOLVE) return "E_CANT_RESOLVE";
		if(code==DSJException.E_CANT_SET_SINK) return "E_CANT_SET_SINK";
		if(code==DSJException.E_CANT_SHOW_DLG) return "E_CANT_SHOW_DLG";
		if(code==DSJException.E_CTRL_NOT_SUPPORTED) return "E_CTRL_NOT_SUPPORTED";
		if(code==DSJException.E_D3D_YUV) return "E_D3D_YUV";
		if(code==DSJException.E_DD_ERR) return "E_DD_ERR";
		if(code==DSJException.E_DD_SURFACELOST) return "E_DD_SURFACELOST";
		if(code==DSJException.E_DES_DISCONNECT) return "E_DES_DISCONNECT";
		if(code==DSJException.E_DES_FRONTEND) return "E_DES_FRONTEND";
		if(code==DSJException.E_DES_INVALID_OUTPUT) return "E_DES_INVALID_OUTPUT";
		if(code==DSJException.E_DES_NOCODEC) return "E_DES_NOCODEC";
		if(code==DSJException.E_DES_RENDER_A) return "E_DES_RENDER_A";
		if(code==DSJException.E_DES_RENDER_V) return "E_DES_RENDER_V";
		if(code==DSJException.E_DEVICE_BUSY) return "E_DEVICE_BUSY";
		if(code==DSJException.E_DRM) return "E_DRM";
		if(code==DSJException.E_DV_BUSY) return "E_DV_BUSY";
		if(code==DSJException.E_EXP_FAILED) return "E_EXP_FAILED";
		if(code==DSJException.E_FILE_NOT_FOUND) return "E_FILE_NOT_FOUND";
		if(code==DSJException.E_FILTER_NOT_FOUND) return "E_FILTER_NOT_FOUND";
		if(code==DSJException.E_FLAGS_CHANGED) return "E_FLAGS_CHANGED";
		if(code==DSJException.E_FORMAT_MISMATCH) return "E_FORMAT_MISMATCH";
		if(code==DSJException.E_FORMAT_READ) return "E_FORMAT_READ";
		if(code==DSJException.E_HTTP_ERROR) return "E_HTTP_ERROR";
		if(code==DSJException.E_INVALID_ARG) return "E_INVALID_ARG";
		if(code==DSJException.E_INVALID_DATA) return "E_INVALID_DATA";
		if(code==DSJException.E_INVALID_FILE) return "E_INVALID_FILE";
		if(code==DSJException.E_INVALID_FILE_TYPE) return "E_INVALID_FILE_TYPE";
		if(code==DSJException.E_INVALID_LIST) return "E_INVALID_LIST";
		if(code==DSJException.E_INVALID_PB_RATE) return "E_INVALID_PB_RATE";
		if(code==DSJException.E_INVALID_RATE) return "E_INVALID_RATE";
		if(code==DSJException.E_INVALID_TIMES) return "E_INVALID_TIMES";
		if(code==DSJException.E_INVALID_TYPE) return "E_INVALID_TYPE";
		if(code==DSJException.E_INVALID_XML) return "E_INVALID_XML";
		if(code==DSJException.E_MP4_NO_VOL) return "E_MP4_NO_VOL";
		if(code==DSJException.E_MPG_NO_PID) return "E_MPG_NO_PID";
		if(code==DSJException.E_MPG_NO_ST) return "E_MPG_NO_ST";
		if(code==DSJException.E_MPG_NO_TS) return "E_MPG_NO_TS";
		if(code==DSJException.E_NO_AUDIO_FILTER) return "E_NO_AUDIO_FILTER";
		if(code==DSJException.E_NO_DEVICE) return "E_NO_DEVICE";
		if(code==DSJException.E_NO_DISC) return "E_NO_DISC";
		if(code==DSJException.E_NO_EDITS) return "E_NO_EDITS";
		if(code==DSJException.E_NO_ENTRY_POINT) return "E_NO_ENTRY_POINT";
		if(code==DSJException.E_NO_HANDLER) return "E_NO_HANDLER";
		if(code==DSJException.E_NO_IMG_BUFFER) return "E_NO_IMG_BUFFER";
		if(code==DSJException.E_NO_INTERFACE) return "E_NO_INTERFACE";
		if(code==DSJException.E_NO_NIO_BUFFER) return "E_NO_NIO_BUFFER";
		if(code==DSJException.E_NO_PROTOCOL_PREF) return "E_NO_PROTOCOL_PREF";
		if(code==DSJException.E_NO_REC_CTRL) return "E_NO_REC_CTRL";
		if(code==DSJException.E_NO_RECORDER) return "E_NO_RECORDER";
		if(code==DSJException.E_NO_RENDERER) return "E_NO_RENDERER";
		if(code==DSJException.E_NO_STREAM) return "E_NO_STREAM";
		if(code==DSJException.E_NO_TAPE) return "E_NO_TAPE";
		if(code==DSJException.E_NO_TRACKS) return "E_NO_TRACKS";
		if(code==DSJException.E_NO_YUV_FORMAT) return "E_NO_YUV_FORMAT";
		if(code==DSJException.E_NOT_ACTIVE) return "E_NOT_ACTIVE";
		if(code==DSJException.E_NOT_AVAILABLE) return "E_NOT_AVAILABLE";
		if(code==DSJException.E_NULL) return "E_NULL";
		if(code==DSJException.E_OUT_OF_RANGE) return "E_OUT_OF_RANGE";
		if(code==DSJException.E_PIN_NOT_FOUND) return "E_PIN_NOT_FOUND";
		if(code==DSJException.E_POINTER) return "E_POINTER";
		if(code==DSJException.E_PREVIEW_VIA_TEE) return "E_PREVIEW_VIA_TEE";
		if(code==DSJException.E_REDIRECT) return "E_REDIRECT";
		if(code==DSJException.E_REG_NO_ACCESS) return "E_REG_NO_ACCESS";
		if(code==DSJException.E_RENDER_AUDIO) return "E_RENDER_AUDIO";
		if(code==DSJException.E_RENDER_VIDEO) return "E_RENDER_VIDEO";
		if(code==DSJException.E_SAVE_FAILED) return "E_SAVE_FAILED";
		if(code==DSJException.E_SINK_CONNECT) return "E_SINK_CONNECT";
		if(code==DSJException.E_STREAM_FORMAT) return "E_STREAM_FORMAT";
		if(code==DSJException.E_TIMEOUT) return "E_TIMEOUT";
		if(code==DSJException.E_TRACK_LOCKED) return "E_TRACK_LOCKED";
		if(code==DSJException.E_TRACK_NOT_FOUND) return "E_TRACK_NOT_FOUND";
		if(code==DSJException.E_UNDEFINED) return "E_UNDEFINED";
		if(code==DSJException.E_UNKNOWN) return "E_UNKNOWN";
		if(code==DSJException.E_UNSUPPORTED) return "E_UNSUPPORTED";
		if(code==DSJException.E_UNSUPPRTED_FILE_TYPE) return "E_UNSUPPRTED_FILE_TYPE";
		if(code==DSJException.E_WRONG_PINDIR) return "E_WRONG_PINDIR";
		if(code==DSJException.E_WRONG_SIZE) return "E_WRONG_SIZE";
		if(code==DSJException.E_WRONG_STATE) return "E_WRONG_STATE";
		
		return "Unsupported code: "+code;
	}
}