package com.zaphlabs.lovecalculator;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Devoloped by ZaphLabs", Toast.LENGTH_LONG).show();
        //New code for button
        final EditText name=(EditText) findViewById(R.id.your_name);
        final EditText cname=(EditText) findViewById(R.id.her_name);
        final TextView res=(TextView) findViewById(R.id.textView);
        final TextView tt=(TextView) findViewById(R.id.Show_text);


        final CheckBox c1 = (CheckBox) findViewById(R.id.Know_view);
        final CheckBox c2 = (CheckBox) findViewById(R.id.Commit_view);
        final CheckBox c3 = (CheckBox) findViewById(R.id.Advise_view);
        final CheckBox c4=(CheckBox) findViewById(R.id.you_view);

        Button calc=(Button) findViewById(R.id.Calculate_view);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res.setText("0%");
                Editable n = name.getText();
                Editable cn = cname.getText();
                String concat = String.valueOf(n).concat(String.valueOf(cn)).toUpperCase();
                if ((n.toString().trim().length() == 0) || (cn.toString().trim().length() == 0)) {
                    Toast.makeText(MainActivity.this, "Please fill both the fields ", Toast.LENGTH_LONG).show();
                } else {
                    int sum = 0;
                    for (int i = 0; i < concat.length(); i++) {
                        char character = concat.charAt(i);
                        int ascii = (int) character;
                        sum += ascii;
                    }
                    res.setText(sum % 100+"%");
                    //Advise code

                 //radio buttons for suggestion
                    Boolean check1 = c1.isChecked();
                    Boolean check2 = c2.isChecked();
                    Boolean check3 = c3.isChecked();



                    if (check3) {
                        if (check1 && check2) {
                            String str = "Try to be his/her friend!";
                            tt.setText(str);
                        } else if (check2) {
                            String str1 = "Try finding other girl/boy!";
                            tt.setText(str1);
                        } else if (check1) {
                            String str2 = "Go Propose her/him.Don't be Afraid!";
                            tt.setText(str2);
                        } else {
                            String str3 = "Take some courage and talk to her/him.";
                            tt.setText(str3);
                        }
                    }

                }
            }
        });
        Button reset=(Button) findViewById(R.id.Reset_view);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res.setText("0%");
                name.setText(null);
                cname.setText(null);
                tt.setText("Suggestions");
                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
            }
        });
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(getApplicationContext(), "Click on Ad!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
                //Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                //Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                //Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
              //  Toast.makeText(getApplicationContext(), "Ad is opened!", Toast.LENGTH_SHORT).show();
            }
        });
    }




    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
