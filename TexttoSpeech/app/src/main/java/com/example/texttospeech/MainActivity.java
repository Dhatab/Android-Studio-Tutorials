package com.example.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech mTextToSpeech;
    private EditText mEditText;
    private SeekBar mSeekPitch;
    private SeekBar mSeekSpeed;
    private Button mButtonSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonSpeak = findViewById(R.id.button_speak);

        mTextToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS){
                    int result = mTextToSpeech.setLanguage(Locale.US);

                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(getApplicationContext(),"Language not supported", Toast.LENGTH_SHORT).show();
                    } else {
                        mButtonSpeak.setEnabled(true);
                    }
                } else{
                    Toast.makeText(getApplicationContext(),"Initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mEditText = findViewById(R.id.edit_text);
        mSeekPitch = findViewById(R.id.seek_bar_pitch);
        mSeekSpeed = findViewById(R.id.seek_bar_speed);

        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
    }

    private void speak() {
        String text = mEditText.getText().toString();
        //Use /50 since it is middle to represent 1.0
        float pitch = (float) mSeekPitch.getProgress()/50;
        if(pitch < 0.1) pitch = 0.1f;

        float speed = (float) mSeekSpeed.getProgress()/50;
        if(speed < 0.1) speed = 0.1f;

        mTextToSpeech.setPitch(pitch);
        mTextToSpeech.setSpeechRate(speed);
        mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if(mTextToSpeech != null){
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
