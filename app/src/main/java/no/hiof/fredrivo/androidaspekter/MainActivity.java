package no.hiof.fredrivo.androidaspekter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imagePlaceholderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton button = findViewById(R.id.cameraButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Fra https://stackoverflow.com/questions/20114485/use-onactivityresult-android
        try {
            super.onActivityResult(requestCode,resultCode,data);

            if (requestCode == 1 && resultCode == RESULT_OK){

                Bundle extras = data.getExtras();
                Bitmap imageBm = (Bitmap) extras.get("data");
                setThumbnail(imageBm);
            }

        } catch (Exception ex){
            Toast.makeText(this, "Woops! something went wrong!" + ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void setThumbnail(Bitmap imageBm){
        imagePlaceholderView = findViewById(R.id.imagePlaceholderView);
        imagePlaceholderView.setImageBitmap(imageBm);

    }
}
