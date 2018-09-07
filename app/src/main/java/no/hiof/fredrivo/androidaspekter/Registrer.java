package no.hiof.fredrivo.androidaspekter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrer extends AppCompatActivity {
    //Tatt hjelp ifra https://www.mkyong.com/android/android-alert-dialog-example/
    final Context context = this;
    private Button registrer;
    private EditText et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        registrer = findViewById(R.id.Registrer);
        et = findViewById(R.id.editTextEmail);

        registrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                //Som jeg forsto då skulle bare Emailen som ble skrevet, settes inn
                //som en message in Dialogen, og når man trykker på knappen "OK" i dialogen
                //kommer det opp en toast som sier "Welcome!"
                alertDialog.setTitle("Er dette emailen din?");

                alertDialog.setMessage(et.getText());
                alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Welcome!", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog ad = alertDialog.create();
                ad.show();

            }
        });
    }
}
