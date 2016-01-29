# Android-Google-URLShortener

Easy to use [Google URL Shortener API](https://developers.google.com/url-shortener/v1/getting_started) on Android.

### Demo Application Screenshot
![]
(https://raw.github.com/HeroGenie/Android-Google-URLShortener/master/sample/GoogleUrlShortener.png)

### Demo Application
You can find the demo application in sample Folder, which name is **GoogleUrlShortener.apk**.

### Before use the Library

##### Get the Library

1) You can find the **ShortURL.java** in library Folder.  
2) Add **ShortURL.java** on your project.

##### Permissions
```
<uses-permission android:name="android.permission.INTERNET" />
```

##### Create Google API KEY
Please create a server key or browser key.  
Please refer to the part “Acquiring and using an API key” in the below URL.  
[https://developers.google.com/url-shortener/v1/getting_started](https://developers.google.com/url-shortener/v1/getting_started)

##### Set Google API KEY
Set KEY variable in **ShortURL.java** Class.

    
### Using the Library

If no short URL is created, then the URL that was inserted when calling function is shown.
```
Ex) If you enter "http://www.google.com"
Success > http://www.goo.gl/fbsS
Fail > http://www.google.com
```

You can create short URL's either synchronous (in case you want to embedd it into an already existing asynchronous operation) or asynchroneous.

##### Create short URL's synchronously

```java
@Override
protected void doInBackground(Void... void) {
    String longUrl = "http://www.google.com";
    String shortUrl = ShortURL.makeShort(longUrl);
}
```

##### Create short URL's asynchronously

```java
String longUrl = "http://www.google.com";
TextView textView = (TextView) findViewById(R.id.textView);
ShortURL.makeShortUrl(longUrl, new ShortUrlListener() {
    @Override
    public void OnFinish(String url) {

        if (url != null && 0 < url.length()) {
            textView.setText(url);
        } else {
            textView.setText("Error");
        }

    }
});
```

### License
```
Copyright 2016 HeroGenie

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
