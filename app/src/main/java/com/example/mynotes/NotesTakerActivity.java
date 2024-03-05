package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mynotes.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {

    EditText etTitle, etNotes;
    ImageView ivSave;
    Notes notes;
    boolean isOldNote = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        etNotes = findViewById(R.id.etNotes);
        etTitle = findViewById(R.id.etTitle);

        ivSave = findViewById(R.id.ivSave);
        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("oldNote");
            etTitle.setText(notes.getTitle());
            etNotes.setText(notes.getNotes());
            isOldNote = true;

        }catch (Exception e){
            e.printStackTrace();
        }


        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String description = etNotes.getText().toString();


                if (description.isEmpty() ){
                    Toast.makeText(NotesTakerActivity.this, "Пожалуйста, добавьте текст заметки.", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d.MM.yyyy HH:mm");
                Date date = new Date();

                if (!isOldNote){
                    notes = new Notes();
                }

                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }
}