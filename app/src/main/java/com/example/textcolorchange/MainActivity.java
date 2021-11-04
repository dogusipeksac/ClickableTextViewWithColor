package com.example.textcolorchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView1;
    String text= "@H @asfsafsfa asfasfasfasf @adada 26262626262626sfaasf";
    String text1= "@kişiprofiliiçintıkla 1542584";
    String word="";
    int wordPaint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        textView1=findViewById(R.id.textView1);


        //textView.setText(spannableString(text));
        textView1.setText(spannableStringWithLocation(text1,1,text1.length()-8));
        String formattedText = "This <i>is</i> a <b>test</b> of <a href='https://www.instagram.com/'>html</a>";
        // or getString(R.string.htmlFormattedText);

        //textView.setText(HtmlCompat.fromHtml(formattedText, HtmlCompat.FROM_HTML_MODE_LEGACY));
        // Set text within a `TextView`
        TextView textView = findViewById(R.id.textView);
        textView.setText("Hey @dogus, where did @esra go? #lost sfasfa fsasfa #saf @asf");
        // Style clickable spans based on pattern
        new PatternEditableBuilder().
                addPattern(Pattern.compile("\\@(\\w+)"), Color.BLUE,
                        new PatternEditableBuilder.SpannableClickedListener() {
                            @Override
                            public void onSpanClicked(String text) {
                                Toast.makeText(MainActivity.this, "Clicked username: " + text,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).into(textView);
        new PatternEditableBuilder().
                addPattern(Pattern.compile("\\#(\\w+)"), Color.RED,
                        new PatternEditableBuilder.SpannableClickedListener() {
                            @Override
                            public void onSpanClicked(String text) {
                                Toast.makeText(MainActivity.this, "Clicked username: " + text,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).into(textView);
    }



    private SpannableString spannableString(String text) {

        int start=0,end=0;
        boolean change;
        int i=0;
        int j=0;
        boolean click=true;
        SpannableString spannableString = new SpannableString(text);
        for(i=0;i<text.length();i++){

            if(text.charAt(i)=='@') {
                start = i;
                change=true;
            for (j=i+1;j<text.length()-1;j++){
                // i=0 j=1
                System.out.println();
                if((text.charAt(j)==' ' && change) ){


                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void updateDrawState(TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setColor(Color.RED);
                            ds.setUnderlineText(true);

                        }
                        @Override
                        public void onClick(View textView) {

                            Toast.makeText(getApplicationContext(),""+textView.getId(), Toast.LENGTH_SHORT).show();

                        }

                    };


                    System.out.println("boolean");
                    end = j;
                    change=false;
                    spannableString.setSpan(clickableSpan,
                            start, end,
                            Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setMovementMethod(LinkMovementMethod.getInstance());
                    textView.setHighlightColor(Color.TRANSPARENT);
                }

            }
                if(change){
                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View textView) {
                            Toast.makeText(getApplicationContext(), textView.toString(), Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void updateDrawState(TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setColor(Color.BLUE);
                            ds.setUnderlineText(true);
                        }
                    };
                    spannableString.setSpan(clickableSpan,
                            start, text.length(),
                            Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setMovementMethod(LinkMovementMethod.getInstance());
                    textView.setHighlightColor(Color.TRANSPARENT);
                }
         }
        }

        return spannableString;
    }
    private SpannableString spannableStringWithLocation(String text,int startLocation,int finishLocation){
        SpannableString spannableString = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Toast.makeText(getApplicationContext(), "Kişi profiline gidildi", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(true);
            }
        };
        spannableString.setSpan(clickableSpan,
                startLocation, finishLocation,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);


        textView1.setMovementMethod(LinkMovementMethod.getInstance());
        textView1.setHighlightColor(Color.TRANSPARENT);

        return spannableString;
    }
    private String catchPlace(int i,int j){


        for(int k=i;k<j;k++){
            char one=text.charAt(k);
            word+=""+one;
            System.out.print(word);
        }

        return word;
    }



}
