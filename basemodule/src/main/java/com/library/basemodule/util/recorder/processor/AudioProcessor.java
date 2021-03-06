package com.library.basemodule.util.recorder.processor;



import com.library.basemodule.util.recorder.AudioRecordEngine;
import com.library.basemodule.util.recorder.Recorder;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * created by zjr on 2019/11/14
 */
public interface AudioProcessor {

    void onBegin(RandomAccessFile writer, Recorder.Config config) throws IOException ;

    int onRead(AudioRecordEngine.PCMReader recorder, byte[] buffer) throws IOException;

    void onAudioChunk(RandomAccessFile writer, byte[] chunk, int readLen) throws IOException;

    void onEnd(RandomAccessFile writer) throws IOException ;

}
