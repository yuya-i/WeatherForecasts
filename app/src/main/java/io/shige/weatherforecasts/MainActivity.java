package io.shige.weatherforecasts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import java.io.IOException;
import java.nio.file.WatchEvent;

public class MainActivity extends AppCompatActivity
{

    public class ApiTask extends GetWeatherForecastTask
    {
        @Override
        protected void onPostExecute(WeatherForecast data)
        {
            super.onPostExecute(data);

            if( data != null )
            {
                result.setText(data.location.area + " " + data.location.prefecture + " " + data.location.city);
                for(WeatherForecast.Forecast forecast : data.forcastList)
                {
                    result.append("/");
                    result.append(forecast.dateLabel + " " + forecast.telop);
                }
            }
            else if( exception != null )
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.tv_result);

        new ApiTask().execute("40040");
    }
}
