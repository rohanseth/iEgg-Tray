package com.cedt.iot.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
//import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements LocationListener {

    TextView txtbox;
    TextView txtbox2;
    LocationManager locationManager;
    ToggleButton toggle;
    HttpClient httpClient;
    MediaPlayer objPlayer;
    RadioGroup RadioGroup01;
    RadioButton RadioButton001;
    RadioButton RadioButton002;
    RadioButton RadioButton003;
    RadioButton RadioButton004;
    RadioButton RadioButton005;
    RadioButton RadioButton006;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.disableStrictMode();
        
        objPlayer = MediaPlayer.create(this,R.raw.cm8);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        httpClient = new DefaultHttpClient();
        txtbox = (TextView) findViewById(R.id.textBox1);
        txtbox2 = (TextView) findViewById(R.id.textview2);
        toggle = (ToggleButton) findViewById(R.id.toggleButton1);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
        RadioGroup01 = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioButton001 = (RadioButton) findViewById(R.id.radio0);
        RadioButton002 = (RadioButton) findViewById(R.id.radio1);
        RadioButton003 = (RadioButton) findViewById(R.id.radio2);
        RadioButton004 = (RadioButton) findViewById(R.id.radio3);
        RadioButton005 = (RadioButton) findViewById(R.id.radio4);
        RadioButton006 = (RadioButton) findViewById(R.id.radio5);
        
        
        
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        update();
                    }
                });
            }
        };
        t.scheduleAtFixedRate(task, 0, 1000);

    }

    @Override
    public void onLocationChanged(Location location) {

        String str = "Latitude: "+location.getLatitude()+" Longitude: "+location.getLongitude();
        txtbox.setText(str);
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    private void update(){
        HttpResponse response;
        try {
            HttpGet httpget = new HttpGet("http://cedtnsit.in/rohan/get.php?name=LED1");
            //HttpGet httpget = new HttpGet("http://10.0.2.2:8051/get.php?name=LED1");
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);
                //Log.i("Result",result);
                if(result.startsWith("1"))
                {
                    toggle.setChecked(true);
                    RadioButton001.setChecked(true);
                	//objPlayer.start();
                }
                
                else if(result.startsWith("2"))
                {
                	toggle.setChecked(false);
                	RadioButton001.setChecked(true);
                	RadioButton002.setChecked(true);
                	//objPlayer.stop();
                	//objPlayer.release();
                }
                
                else if(result.startsWith("3"))
                {
                	toggle.setChecked(false);
                	RadioButton001.setChecked(true);
                	RadioButton002.setChecked(true);
                	RadioButton003.setChecked(true);
                	//objPlayer.stop();
                	//objPlayer.release();
                }
                
                else if(result.startsWith("4"))
                {
                	toggle.setChecked(false);
                	RadioButton001.setChecked(true);
                	RadioButton002.setChecked(true);
                	RadioButton003.setChecked(true);
                	RadioButton004.setChecked(true);
                	//objPlayer.stop();
                	//objPlayer.release();
                }
                
                else if(result.startsWith("5"))
                {
                	toggle.setChecked(false);
                	RadioButton001.setChecked(true);
                	RadioButton002.setChecked(true);
                	RadioButton003.setChecked(true);
                	RadioButton004.setChecked(true);
                	RadioButton005.setChecked(true);
                	//objPlayer.stop();
                	//objPlayer.release();
                }
                
                else
                {
                	toggle.setChecked(false);
                	RadioButton001.setChecked(true);
                	RadioButton002.setChecked(true);
                	RadioButton003.setChecked(true);
                	RadioButton004.setChecked(true);
                	RadioButton005.setChecked(true);
                	RadioButton006.setChecked(true);
                	//objPlayer.stop();
                	//objPlayer.release();
                }
                	txtbox2.setText(result);
                instream.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is), 1024);
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private void disableStrictMode() {
        try {
            Class<?> strictModeClass = Class.forName("android.os.StrictMode", true, Thread.currentThread().getContextClassLoader());
            Class<?> threadPolicyClass = Class.forName("android.os.StrictMode$ThreadPolicy", true, Thread .currentThread().getContextClassLoader());
            Class<?> threadPolicyBuilderClass = Class.forName("android.os.StrictMode$ThreadPolicy$Builder", true, Thread.currentThread().getContextClassLoader());
            Method setThreadPolicyMethod = strictModeClass.getMethod("setThreadPolicy", threadPolicyClass);
            Method detectAllMethod = threadPolicyBuilderClass.getMethod("detectAll");
            Method penaltyMethod = threadPolicyBuilderClass.getMethod("penaltyLog");
            Method buildMethod = threadPolicyBuilderClass.getMethod("build");
            Constructor<?> threadPolicyBuilderConstructor = threadPolicyBuilderClass.getConstructor();
            Object threadPolicyBuilderObject = threadPolicyBuilderConstructor.newInstance();
            Object obj = detectAllMethod.invoke(threadPolicyBuilderObject);
            obj = penaltyMethod.invoke(obj);
            Object threadPolicyObject = buildMethod.invoke(obj);
            setThreadPolicyMethod.invoke(strictModeClass, threadPolicyObject);
        }
        catch (Exception ex) {
            Log.w("disableStrictMode", ex);
        }
    }

}
