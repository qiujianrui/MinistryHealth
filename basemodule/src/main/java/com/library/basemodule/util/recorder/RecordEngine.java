package com.library.basemodule.util.recorder;


import com.library.basemodule.util.recorder.listener.OnAudioChunkListener;
import com.library.basemodule.util.recorder.listener.OnRecordListener;
import com.library.basemodule.util.recorder.listener.OnVolumeListener;

/**
 * created by zjr on 2019/11/14
 */
interface RecordEngine {

    void start();
    
    void pause();

    void cancel();

    void stop();

    void release();
    
    void setConfig(Recorder.Config config);

    Recorder.Config getConfig();

    boolean isRecording();

    void setRecordListener(OnRecordListener listener);

    void setOnAudioChunkListener(OnAudioChunkListener listener);

    void setVolumeListener(OnVolumeListener volumeListener);
    
}
