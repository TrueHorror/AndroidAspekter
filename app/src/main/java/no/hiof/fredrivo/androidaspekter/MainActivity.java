package no.hiof.fredrivo.androidaspekter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private ImageView imagePlaceholderView;
    private Bitmap bitmap;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("IMAGE", bitmap);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (bitmap != null){
            imagePlaceholderView = savedInstanceState.getParcelable("IMAGE");
        }

        ImageButton button = findViewById(R.id.cameraButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_main, menu);

        return true;

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
         bitmap = savedInstanceState.getParcelable("IMAGE");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Fra https://stackoverflow.com/questions/20114485/use-onactivityresult-android
        try {
            super.onActivityResult(requestCode,resultCode,data);

            Toast.makeText(this, "Før IF", Toast.LENGTH_SHORT).show();
            if (requestCode == 1 && resultCode == RESULT_OK){

                Toast.makeText(this, "Etter IF", Toast.LENGTH_SHORT).show();

                Bundle extras = data.getExtras();
                bitmap = (Bitmap) extras.get("data");
                setThumbnail(bitmap);
            }

        } catch (Exception ex){
            Toast.makeText(this, "Woops! something went wrong!" + ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void setThumbnail(Bitmap bitmap){
        imagePlaceholderView = findViewById(R.id.imagePlaceholderView);
        imagePlaceholderView.setImageBitmap(bitmap);

    }

}
