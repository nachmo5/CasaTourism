package com.johndoe.mtourismbeta;


import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Notes extends AppCompatActivity {
    EditText txt;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);




        spin=(Spinner)findViewById(R.id.spinner);
        refreshSpiner();
        txt=(EditText) findViewById(R.id.editText_note);
        txt.setHint(R.string.noteplaceholder);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void  onItemSelected(AdapterView<?> parent, View item, int pos,long id) {

                String selectedfile = spin.getSelectedItem().toString();
                if(!selectedfile.equals("New Note"))
                {File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Mtourism/Notes/" + selectedfile);
                    String noteread = readNote(file);
                    txt.setText(noteread);}
                else{
                    txt.setText("");
                    txt.setHint(R.string.noteplaceholder);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txt.setHint(R.string.noteplaceholder);
            }

        });

    }
    public void onNoteSave(View view){
        String namenote=spin.getSelectedItem().toString();
        String text = txt.getText().toString();
        if(namenote.equals("New Note"))
            writeNote(text,getNoteName());
        else
            writeNote(text,namenote);
        txt.setText("");
        txt.setHint(R.string.noteplaceholder);
        refreshSpiner();
    }

    public void writeNote(String text,String filename){
        File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "Mtourism/Notes");
        directory.mkdirs();
        File file=new File(Environment.getExternalStorageDirectory() + File.separator + "Mtourism/Notes/"+filename);
        try {
            if(!file.exists())
                file.delete();
            file.createNewFile();


            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.flush();
            fileWriter.close();
            Toast.makeText(Notes.this, "Note Enregistrée", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String readNote(File file)
    {       StringBuilder text=new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public String getNoteName(){
        File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "Mtourism/Notes");
        directory.mkdirs();
        File[] names=directory.listFiles();
        int max=0;
        for(int i=0;i<names.length;i++)
        {    String numberOnly= names[i].getName().replaceAll("[^0-9]", "");
            int number=Integer.parseInt(numberOnly);
            if(number>max){
                max=number;
            }
        }
        return "note"+(max+1)+".txt";

    }

    public void onNoteDelete(View view){
        String namenote=spin.getSelectedItem().toString();

        if(!namenote.equals("New Note")) {

            File file=new File(Environment.getExternalStorageDirectory() + File.separator + "Mtourism/Notes/" + namenote);
            if(file.exists())
                file.delete();
        }

        refreshSpiner();
    }

    public void refreshSpiner(){
        File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "Mtourism/Notes");
        File[] names=directory.listFiles();
        String[] noms;
        if(names!=null)
       noms=new String[names.length+1];
        else
        noms=new String[1];
        noms[0]="New Note";
        for(int i=1;i<noms.length;i++){
            noms[i]=names[i-1].getName();
        }
        ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_list_item_1,noms);
        spin.setAdapter(ad);

    }
}

