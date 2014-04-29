package com.wyw.chat;

import java.net.Socket;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder.AudioSource;

//电话模式，持续的语音聊天
public class ContinuedAudioRecord {
	
	private Boolean isRecording = false;
	
	//需要更换成自己的socket，列在此处待更改
	private Socket tcpSocket = null;
	
	public ContinuedAudioRecord(Socket mySocket){
		
		tcpSocket = mySocket;
	}
	
	public void start(){
		//此处初始化音频对象的具体数值设置，需要看api研究
		int frequency = 11025;//频率
		int channelConfiguration = AudioFormat.CHANNEL_IN_DEFAULT;//频道
		int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;//编码格式：16bit比8bit质量好（但耗电量、内存、cpu大）
		int bufferSize = AudioRecord.getMinBufferSize(frequency, channelConfiguration,  audioEncoding);
		AudioRecord audioRecord = new AudioRecord(AudioSource.MIC,
                frequency, channelConfiguration,
                audioEncoding, bufferSize);
		
		short[] buffer = new short[bufferSize];
		
		audioRecord.startRecording();
		isRecording = true;
		while (isRecording) {
			int bufferReadResult = audioRecord.read(buffer, 0, bufferSize);
			
			//这里用socket发送bufferReadResult大小的buffer
			//在接收端组成音频播放
			//是否需要放到线程中需实际测试
			//...
			
		}
		
		audioRecord.stop();
		audioRecord.release();
	}
	
	
	public void stop(){
		isRecording = false;
	}
}
