package com.outsourcing.llxpb.util.ui;

import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

public class AudioUtil {
    public interface OnAudioPrepareListener {
        void onSuccess(String filePath);
    }

    private static MediaRecorder mRecorder;

    public static void startRecord(OnAudioPrepareListener listener) {
        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
        }
        try {
            File folder = new File(Environment.getExternalStorageDirectory(), "PoliceServiceAudio");
            if (!folder.exists()) {
                folder.mkdir();
            }
            File file = new File(folder.getAbsolutePath(), "audio_" + System.currentTimeMillis() + ".mp3");
            if (file.exists()) {
                file.delete();
            }
            boolean newFile = file.createNewFile();
            if (newFile) {
                String path = file.getAbsolutePath();
                mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mRecorder.setAudioChannels(1);
                mRecorder.setAudioSamplingRate(44100);
                mRecorder.setAudioEncodingBitRate(192000);
                mRecorder.setOutputFile(path);
                mRecorder.prepare();
                mRecorder.start();
                listener.onSuccess(file.getAbsolutePath());
            } else {
                UIUtils.showSingleToast("创建录音文件失败！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            UIUtils.showSingleToast(e.getLocalizedMessage());
        }
    }

    public static void pauseRecord() {
        if (mRecorder == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mRecorder.pause();
        } else {
            mRecorder.stop();
            mRecorder.release();
        }
    }

    public static void resumeRecord() {
        if (mRecorder == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mRecorder.resume();
        } else {
            try {
                mRecorder.prepare();
                mRecorder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopRecord() {
        if (mRecorder == null) return;
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }
}
