package me.com.baselibrary;

import android.nfc.Tag;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class AutoTouch {
    public void autoClickPos(final double x1, final double y1,final double x2, final double y2){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                String[] order = {"input", "swipe", "" + x1, "" + y1, "" + x2, "" + y2,};
                try{
                    for (int i = 0; i <100000 ; i++) {
                        Log.e("autoClickPos", "ProcessBuilder-->"+i);
                        new ProcessBuilder(order).start();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
