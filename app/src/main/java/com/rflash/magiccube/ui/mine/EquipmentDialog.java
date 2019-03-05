package com.rflash.magiccube.ui.mine;

import android.app.Dialog;
import android.content.Context;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rflash.basemodule.utils.SpUtil;
import com.rflash.magiccube.Config;
import com.rflash.magiccube.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lys
 * @time 2018/11/8 09:11
 * @desc:
 */

public class EquipmentDialog extends Dialog {

    Context context;

    private LocationManager locationManager;

    private double latitude = 0;

    private double longitude = 0;

    String latLongString;


    @BindView(R.id.dismiss_iv)
    ImageView dismiss_iv;

    @BindView(R.id.local_area_tv)
    TextView local_area_tv;

    @BindView(R.id.MACHINECODE_tv)
    TextView MACHINECODE_tv;

    @BindView(R.id.TERMCODE_tv)
    TextView TERMCODE_tv;

    @BindView(R.id.merchantCode_tv)
    TextView merchantCode_tv;


    public EquipmentDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_equipment);
        ButterKnife.bind(this);
        initView();
        new TaskAsy().execute("http://ip-api.com/json/?lang=zh-CN");
//        getJW();
    }

    private void initView(){
        dismiss_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        MACHINECODE_tv.setText("POS机序列号："+ SpUtil.getString(context, Config.MACHINECODE,""));
        TERMCODE_tv.setText("终端号："+SpUtil.getString(context,Config.TERMCODE,""));
        merchantCode_tv.setText("商户号："+SpUtil.getString(context,Config.MERCHANTCODE,""));
    }


    /**
     * 确定按钮监听
     */
//    public void getJW() {
//        new Thread() {
//            @Override
//            public void run() {
//                @SuppressLint("MissingPermission")
//                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                if (location != null) {
//                    latitude = location.getLatitude(); // 经度
//                    longitude = location.getLongitude(); // 纬度
//                    double[] data = {latitude, longitude};
//                    Message msg = handler.obtainMessage();
//                    msg.obj = data;
//                    handler.sendMessage(msg);
//                }
//            }
//        }.start();
//    }

    class TaskAsy extends AsyncTask<String,Void,StringBuffer>{

        @Override
        protected StringBuffer doInBackground(String... strings) {

            return getJSONData(strings[0]);
        }

        @Override
        protected void onPostExecute(StringBuffer s) {
            super.onPostExecute(s);
            if(s!=null){
                parseAddressJSON(s);
            }
        }
    }

    private static StringBuffer getJSONData(String urlPath){
        try {
            URL url = new URL(urlPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            if(httpURLConnection.getResponseCode() == 200){
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(isr);
                String temp = null;
                StringBuffer jsonsb = new StringBuffer();
                while((temp = br.readLine()) != null){
                    jsonsb.append(temp);
                }
                return jsonsb;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private String parseAddressJSON(StringBuffer sb){
        try {
            if(sb != null){
                JSONObject jsonAllData = new JSONObject(sb.toString());
                String city=jsonAllData.getString("city")+"";
                String provice=jsonAllData.getString("regionName")+"省";
                local_area_tv.setText("当前区域是："+provice+city);
                Log.e("lys","获取到的城市是："+provice+city+"");
                return city;
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

}
