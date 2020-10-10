package com.example.soundtest.Learn;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.soundtest.R;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllahFragmentChikon extends Fragment {

    private ImageSwitcher topSwitchermota,leftSwitchermota,rightSwitchermota,middleSwitchermota;
    private ImageButton previousBtn,replayBtn,nextBtn,micButton,compareButtn;
    private  static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    TextView userVoiceConvertPur;
    private TextSwitcher textSwitcherPur;
    MediaPlayer motaMedia,goodToast,badToast;
    private int currenthorkot = 0;
    int position  = -1;

    int[] motaTopImage = {
            R.drawable.bismillahi,
            R.drawable.balillahi,
            R.drawable.dinillahi,

    };

    int[] motaRightImage = {
            R.drawable.bismillahi,
            R.drawable.bismillahi,
            R.drawable.bismillahi,

    };
    int[] motaMidleImage = {
            R.drawable.book11,
            R.drawable.balillahi,
            R.drawable.balillahi,
    };
    int[] motaLeftImage = {
            R.drawable.book11,
            R.drawable.book11,
            R.drawable.dinillahi,
    };

    String[] presetpronunciation = {

            "بسم الله",
            "بل الله",
            "دين  الله",
    };
    int [] motasound={R.raw.bismillahi,R.raw.balillahu,R.raw.dinillahi,
    };

    public AllahFragmentChikon() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_allah_fragment_chikon, container, false);


        previousBtn = view.findViewById(R.id.chikonPreviousBtn);
        replayBtn = view.findViewById(R.id.chikonRepeatBtn);
        nextBtn = view.findViewById(R.id.chikonNextBtn);
        micButton = view.findViewById(R.id.chikonmicBtnAllah);
        compareButtn = view.findViewById(R.id.chikoncompareBtnAllah);

        topSwitchermota = view.findViewById(R.id.chikonimageSwitherTop);
        leftSwitchermota = view.findViewById(R.id.chikonimageSwitherLeft);
        middleSwitchermota = view.findViewById(R.id.chikonimageSwitherMiddle);
        rightSwitchermota = view.findViewById(R.id.chikonimageSwitherRight);


        userVoiceConvertPur = view.findViewById(R.id.chikonuserVoiceTextAllah);
        textSwitcherPur = view.findViewById(R.id.chikontextSwitcherAllahPronunciation);


        motaMedia = MediaPlayer.create(getContext(),motasound[currenthorkot]);
        goodToast = MediaPlayer.create(getContext(),R.raw.masha_allah2);
        badToast = MediaPlayer.create(getContext(),R.raw.try_again);

        textSwitcherPur.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getContext());
                textView.setTextSize(20);
                textView.setTextColor(getResources().getColor(R.color.blackColor));
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });

        topSwitchermota.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getContext());
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT));
                topSwitchermota.setBackgroundResource(R.drawable.book11);
                return imageView;
            }
        });

        rightSwitchermota.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getContext());
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT));
                rightSwitchermota.setBackgroundResource(R.drawable.book11);
                return imageView;
            }
        });
        middleSwitchermota.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getContext());
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT));
                middleSwitchermota.setBackgroundResource(R.drawable.book11);
                return imageView;
            }
        });
        leftSwitchermota.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getContext());
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT));
                leftSwitchermota.setBackgroundResource(R.drawable.book11);
                return imageView;
            }
        });
        compareButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView sentanceOne = (TextView) textSwitcherPur.getCurrentView();

                String sentanceOnee = sentanceOne.getText().toString();
                String sentenceTwoo = userVoiceConvertPur.getText().toString();

                if (sentanceOnee.equals(sentenceTwoo) ) {

                    goodToast.start();

                }
                else {

                    badToast.start();
                }
            }
        });
        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ar-JO");

                try {
                    startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
                }catch (Exception e){

                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position<motaTopImage.length-1){

                    position = position+1;
                    topSwitchermota.setBackgroundResource(motaTopImage[position]);
                    rightSwitchermota.setBackgroundResource(motaRightImage[position]);
                    middleSwitchermota.setBackgroundResource(motaMidleImage[position]);
                    leftSwitchermota.setBackgroundResource(motaLeftImage[position]);
                    textSwitcherPur.setText(presetpronunciation[position]);

                    try {
                        motaMedia.stop();
                        motaMedia = MediaPlayer.create(getContext(),motasound[currenthorkot]);
                        currenthorkot++;
                        motaMedia.start();
                    }catch (Exception e){

                    }

                }

            }

        });

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position>0){

                    position = position-1;
                    topSwitchermota.setBackgroundResource(motaTopImage[position]);
                    rightSwitchermota.setBackgroundResource(motaRightImage[position]);
                    middleSwitchermota.setBackgroundResource(motaMidleImage[position]);
                    leftSwitchermota.setBackgroundResource(motaLeftImage[position]);

                    try {
                        motaMedia.stop();
                        currenthorkot--;
                        MediaPlayer.create(getContext(),motasound[currenthorkot]);
                        motaMedia.start();
                    }catch (Exception e){

                    }


                }

            }
        });

        replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (motaMedia == null){
                    motaMedia = MediaPlayer.create(getContext(),motasound[currenthorkot]);

                }
                motaMedia.start();
            }
        });








        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case  REQUEST_CODE_SPEECH_INPUT:{

                if(resultCode == RESULT_OK && null!= data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    userVoiceConvertPur.setText(result.get(0));
                }
                break;
            }
        }
    }

}
