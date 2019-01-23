package me.com.t;

import android.content.Context;
import android.util.Log;
import com.umeng.commonsdk.statistics.common.DeviceConfig;

public class UMengUtil {
    public static String[] getTestDeviceInfo(Context context){
        String[] deviceInfo = new String[2];
        try {
            if(context != null){
                deviceInfo[0] = DeviceConfig.getDeviceIdForGeneral(context);
                deviceInfo[1] = DeviceConfig.getMac(context);
                for (int i = 0; i <deviceInfo.length ; i++) {
                    Log.e("deviceInfo-->"+i,"-->"+deviceInfo[i]);
                }
            }
        } catch (Exception e){
        }
        return deviceInfo;
    }
}
