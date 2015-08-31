package com.example.kimbui.cpuinfo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    TextView cpuInfo;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        cpuInfo = (TextView) view.findViewById(R.id.cpuInfo);
        displayInfo();
        return view;
    }

    public void displayInfo(){
        try {
            Process proc = Runtime.getRuntime().exec("cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            InputStream input = proc.getInputStream();
            StringBuilder builder = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String line = null;
            while((line = br.readLine()) != null ){
                builder.append(line);
                builder.append("\n");
            }
            br.close();

            cpuInfo.setText(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
