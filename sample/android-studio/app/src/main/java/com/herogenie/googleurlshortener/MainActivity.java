package com.herogenie.googleurlshortener;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.herogenie.shorturl.ShortURL;
import com.herogenie.shorturl.ShortURL.ShortUrlListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    boolean canClick = false;

    Context context;
    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textView.getText().toString();

                if(text != null && 0 < text.length() && canClick) {

                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(text));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        editText = (EditText) findViewById(R.id.editText);
        editText.setText("http://www.google.com");

        //////////////////////////////////////////////////
		/*
		URL has the parameter. If URL parameter contains "characters other than English characters and numbers", UTF-8 encoding is done as shown in the below example.

		We passed it without doing UTF-8 encoding on the parameter containing "characters other than English characters and numbers".
		Nonetheless, it worked well.
		Hence, it seems that Google URL Shortener API automatically manages it even if UTF-8 encoding is not done on the parameter that contains "characters other than English characters and numbers".

		<<< Example >>>
		Original URL : http://www.google.com?param1=abc&param2=가나다
		UTF-8 URL : http://www.google.com?param1=abc&param2=%EA%B0%80%EB%82%98%EB%8B%A4
		*/
        String url = "http://www.google.com";
        String param1 = "abc";
        String param2 = "가나다";
        try {
            param1 = URLEncoder.encode(param1, "UTF-8");
            param2 = URLEncoder.encode(param2, "UTF-8");
            url = url + "?param1=" + param1 + "&param2=" + param2;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //////////////////////////////////////////////////

        ///
        textView.setText("Add KEY in ShortUrl Class.");
        ///

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText("");

                ///////////////////////////////////////////////////////////////////////////////
				/*
				If no short URL is created, then the URL that was inserted when calling function is shown.
				Ex) If you enter "http://www.google.com"
				Success > http://www.goo.gl/fbsS
				Fail > http://www.google.com
				*/
                ///////////////////////////////////////////////////////////////////////////////
                ShortURL.makeShortUrl(editText.getText().toString(), new ShortUrlListener() {
                    @Override
                    public void OnFinish(String url) {

                        if(url != null && 0 < url.length()) {
                            canClick = true;
                            textView.setText(url);
                        } else {
                            canClick = false;
                            textView.setText("Error");
                        }

                    }
                });
                ///////////////////////////////////////////////////////////////////////////////

            }
        });

    }

}
