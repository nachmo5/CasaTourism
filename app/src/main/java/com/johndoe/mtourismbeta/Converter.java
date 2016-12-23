package com.johndoe.mtourismbeta;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Converter extends Activity {
    String sens="toDirham";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_converter);

        ImageButton imgb=(ImageButton) findViewById(R.id.button_toggle);

        imgb.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (sens.equals("toDirham")) {
                    sens = "fromDirham";
                    ImageButton imgb = (ImageButton) findViewById(R.id.button_toggle);
                    imgb.setImageResource(R.drawable.leftarrow);
                } else if (sens.equals("fromDirham")) {
                    sens = "toDirham";
                    ImageButton imgb = (ImageButton) findViewById(R.id.button_toggle);
                    imgb.setImageResource(R.drawable.rightarrow);
                }

            }

        });


    }

    public void onConvertir(View view){
        ImageButton imgb=(ImageButton) findViewById(R.id.button_toggle);

        Spinner devise=(Spinner) findViewById(R.id.spinner_currency);
        String curr=devise.getSelectedItem().toString();
        EditText Txt=(EditText)findViewById(R.id.editText_incurrency);
        TextView finale=(TextView)findViewById(R.id.textView_converted);
        Float nombre=Float.parseFloat(Txt.getText().toString());
        Float rate=0.0f;
        if(curr.equals("Euro"))
            rate=10.93f;
        else if(curr.equals("Dollars"))
            rate=9.69f;
        else if(curr.equals("British Pound"))
            rate=13.76f;
        else if(curr.equals("Yen"))
            rate=0.089f;
        if(sens.equals("toDirham")){
            nombre=nombre*rate;
        }
        else
            nombre=nombre/rate;

        finale.setText(nombre.toString());




    }
    public void onAnnuler(View view){
        finish();
    }

}
