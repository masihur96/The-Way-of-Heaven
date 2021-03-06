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
public class PurFragment extends Fragment {
    private TextSwitcher topSwitcher_roPur,leftSwitcher_roPur,rightSwitcher_roPur,middleSwitcher_roPur;
    private TextSwitcher textSwitcherPur;
    private ImageButton previousBtn,replayBtn,nextBtn,micButton,compareButtn;
    TextView userVoiceConvertPur;
    private  static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    MediaPlayer roPurMedia,goodToast,badToast;
    private int currenthorkot = 0;
    int position  = -1;

    String[] roPurTopImage = {

            "اَمٛرَ",
            "رُسُلٌ",
            "غَرٛقًا",
            "قُرٛأٰنًا",
    };



    String[] roPurRightImage = {

            "اَمٛرَ",
            "اَمٛرَ",
            "اَمٛرَ",
            "قُرٛأٰنًا",
    };
    String[] roPurMidleImage = {

            "",
            "رُسُلٌ",
            "رُسُلٌ",
            "قُرٛأٰنًا",
    };
    String[] roPurLeftImage = {

            "",
            "",
            "غَرٛقًا",
            "قُرٛأٰنًا",
    };

    String[] presetpronunciation = {

            "امر",
            "رسل",
            "غرقا",
            "قران",
    };


    int [] roPursound={ R.raw.amaro,R.raw.rusulun,R.raw.gorkon,R.raw.quraanun,};


    public PurFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pur, container, false);



        previousBtn = view.findViewById(R.id.roMotaPreviousBtn);
        replayBtn = view.findViewById(R.id.roMotaRepeatBtn);
        nextBtn = view.findViewById(R.id.roMotaNextBtn);
        micButton = view.findViewById(R.id.micBtnPur);
        compareButtn = view.findViewById(R.id.compareBtnPur);

        topSwitcher_roPur = view.findViewById(R.id.roMotaimageSwitherTop);
        leftSwitcher_roPur = view.findViewById(R.id.roMotatextSwitherLeft);
        middleSwitcher_roPur = view.findViewById(R.id.roMotatextSwitherMiddle);
        rightSwitcher_roPur = view.findViewById(R.id.roMotatextSwitherRight);
        userVoiceConvertPur = view.findViewById(R.id.userVoiceTextPur);
        textSwitcherPur = view.findViewById(R.id.textSwitcherPurPronunciation);


        roPurMedia = MediaPlayer.create(getContext(),roPursound[currenthorkot]);
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
        topSwitcher_roPur.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getContext());
                textView.setTextSize(150);
                textView.setTextColor(getResources().getColor(R.color.blackColor));
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });

        rightSwitcher_roPur.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getContext());
                textView.setTextSize(100);
                textView.setTextColor(getResources().getColor(R.color.blackColor));
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });
        middleSwitcher_roPur.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getContext());
                textView.setTextSize(100);
                textView.setTextColor(getResources().getColor(R.color.blackColor));
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });
        leftSwitcher_roPur.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getContext());
                textView.setTextSize(100);
                textView.setTextColor(getResources().getColor(R.color.blackColor));
                textView.setGravity(Gravity.CENTER);
                return textView;
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

                if(position<roPurTopImage.length-1){

                    position = position+1;
                    topSwitcher_roPur.setText(roPurTopImage[position]);
                    rightSwitcher_roPur.setText(roPurRightImage[position]);
                    middleSwitcher_roPur.setText(roPurMidleImage[position]);
                    leftSwitcher_roPur.setText(roPurLeftImage[position]);
                    textSwitcherPur.setText(presetpronunciation[position]);

                    try {
                        roPurMedia.stop();
                        roPurMedia = MediaPlayer.create(getContext(),roPursound[currenthorkot]);
                        currenthorkot++;
                        roPurMedia.start();
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
                    topSwitcher_roPur.setText(roPurTopImage[position]);
                    rightSwitcher_roPur.setText(roPurRightImage[position]);
                    middleSwitcher_roPur.setText(roPurMidleImage[position]);
                    leftSwitcher_roPur.setText(roPurLeftImage[position]);
                    textSwitcherPur.setText(presetpronunciation[position]);
                    try {
                        roPurMedia.stop();
                        currenthorkot--;
                        MediaPlayer.create(getContext(),roPursound[currenthorkot]);
                        roPurMedia.start();
                    }catch (Exception e){

                    }


                }

            }
        });

        replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (roPurMedia == null){
                    roPurMedia = MediaPlayer.create(getContext(),roPursound[currenthorkot]);

                }
                roPurMedia.start();
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
