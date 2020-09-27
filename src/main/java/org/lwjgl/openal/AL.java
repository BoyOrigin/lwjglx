package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

public class AL {
	
	static long alContext;
	static ALCdevice alcDevice;
	
	//
	static ALCCapabilities alContextCaps;
	static ALCapabilities alCaps;
	
	private static boolean created = false;
	
	static {
		Sys.initialize(); // init using dummy sys method
	}
	
	public static void create() throws LWJGLException {
		if (alContext == MemoryUtil.NULL) {
			//ALDevice alDevice = ALDevice.create();
			long alDevice = ALC10.alcOpenDevice((ByteBuffer)null);
			if(alDevice == MemoryUtil.NULL){
				throw new LWJGLException("Cannot open the device");
			}
			
			IntBuffer attribs = BufferUtils.createIntBuffer(16);

			attribs.put(org.lwjgl.openal.ALC10.ALC_FREQUENCY);
			attribs.put(44100);

			attribs.put(org.lwjgl.openal.ALC10.ALC_REFRESH);
			attribs.put(60);

			attribs.put(org.lwjgl.openal.ALC10.ALC_SYNC);
			attribs.put(org.lwjgl.openal.ALC10.ALC_FALSE);

			attribs.put(0);
			attribs.flip();
			
			long contextHandle = org.lwjgl.openal.ALC10.alcCreateContext(alDevice, attribs);
			ALC10.alcMakeContextCurrent(contextHandle);
			//alContext = new ALContext(alDevice, contextHandle);
			alContext = ALC10.alcCreateContext(contextHandle, (IntBuffer)null);
			alContextCaps = ALC.createCapabilities(alContext);

			alCaps = org.lwjgl.openal.AL.createCapabilities(alContextCaps);
			
			alcDevice = new ALCdevice(alDevice);
			created = true;
		}
	}
	
	public static boolean isCreated() {
		return created;
	}
	
	public static void destroy() {
		//alContext.destroy();
		ALC10.alcMakeContextCurrent(MemoryUtil.NULL);
		ALC10.alcDestroyContext(alContext);
		ALC10.alcCloseDevice(alcDevice.device);
		alContext = -1;
		alcDevice = null;
		created = false;
	}
	
	public static ALCdevice getDevice() {
		return alcDevice;
	}

}
