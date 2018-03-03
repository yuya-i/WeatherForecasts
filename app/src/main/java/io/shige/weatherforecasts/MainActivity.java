package io.shige.weatherforecasts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.tv_result);

        Thread subThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    final String data = WeatherAPI.getWeather("400040");
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            result.setText(data);
                        }

                    });
                }
                catch(IOException e)
                {
                    Toast.makeText(getApplicationContext(), "IOException is occured.", Toast.LENGTH_SHORT).show();
                }
            }
        };
        subThread.start();
    }
}
