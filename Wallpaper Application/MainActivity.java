package com.example.wallpaperapplication;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button changeWallpaper;
    Timer myTimer;
    Drawable drawable;
    WallpaperManager wpm;
    int prev=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        myTimer=new Timer();
        wpm=WallpaperManager.getInstance(this);
        changeWallpaper=findViewById(R.id.button);
        changeWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaper();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void setWallpaper()
    {
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (prev==1){
                    drawable=getResources().getDrawable(R.drawable.hornets);
                    prev=2;
                } else if (prev==2){
                    drawable=getResources().getDrawable(R.drawable.lakers);
                    prev=3;
                }
                else if (prev==3){
                    drawable=getResources().getDrawable(R.drawable.memphis);
                    prev=4;
                }
                else if (prev==4){
                    drawable=getResources().getDrawable(R.drawable.nets);
                    prev=5;
                }
                else if (prev==5){
                    drawable=getResources().getDrawable(R.drawable.okc);
                    prev=1;
                }
                Bitmap wallper=((BitmapDrawable)drawable).getBitmap();
                try{
                    wpm.setBitmap(wallper);
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }, 0,30000);
    }
}
