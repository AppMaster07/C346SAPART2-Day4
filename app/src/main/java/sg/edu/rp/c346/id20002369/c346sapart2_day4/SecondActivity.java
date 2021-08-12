package sg.edu.rp.c346.id20002369.c346sapart2_day4;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<PSIReading> ReadList;
    CustomAdapter caRead;
    int requestCode = 9;
    Button btnLog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnLog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater =
                        (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.input, null);
                final EditText etInput1 = viewDialog.findViewById(R.id.editTextInput1);
                final EditText etInput2 = viewDialog.findViewById(R.id.editTextInput2);

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(SecondActivity.this);
                alertBuilder.setTitle("Add Log");
                alertBuilder.setCancelable(true);
                alertBuilder.setNegativeButton("CANCEL", null);


                DBHelper dbh = new DBHelper(SecondActivity.this);
                long result = dbh.insertConcert(index, place);

                alertBuilder.setPositiveButton("ADD",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Extract the text entered by the user
                    String message = etInput1.getText().toString();
                    String message2 = etInput2.getText().toString();

                }

                AlertDialog alertDialog = alertBuilder.create();
                alertBuilder.show();

            }




        });
    }
}
