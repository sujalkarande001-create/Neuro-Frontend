package com.example.minivone;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etEdu, etApoe4;
    RadioGroup radioGender;
    Button btnPredict, btnHistory;

    String URL= "https://neuro-backend-hdnb.onrender.com/predict";
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etEdu = findViewById(R.id.etEdu);
        etApoe4 = findViewById(R.id.etApoe4);
        radioGender = findViewById(R.id.radioGender);

        btnPredict = findViewById(R.id.btnPredict);
        btnHistory = findViewById(R.id.btnHistory);

        dbHelper = new DatabaseHelper(this);

        btnPredict.setOnClickListener(v -> predict());

        btnHistory.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, HistoryActivity.class))
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        etName.setText("");
        etAge.setText("");
        etEdu.setText("");
        etApoe4.setText("");
        radioGender.clearCheck();
    }

    private void predict() {

        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String edu = etEdu.getText().toString();
        String apoe4 = etApoe4.getText().toString();

        String gender;
        int selectedId = radioGender.getCheckedRadioButtonId();

        if (selectedId == R.id.rbMale) {
            gender = "Male";
        } else if (selectedId == R.id.rbFemale) {
            gender = "Female";
        } else {
            gender = "";
        }

        if(name.isEmpty() || age.isEmpty() || gender.isEmpty() || edu.isEmpty() || apoe4.isEmpty()){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show();
            return;
        }

        try{

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("age", Double.parseDouble(age));
            jsonBody.put("gender", gender);
            jsonBody.put("education", Integer.parseInt(edu));
            jsonBody.put("apoe4", Integer.parseInt(apoe4));

            RequestQueue queue = Volley.newRequestQueue(this);

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    URL,
                    jsonBody,
                    response -> {

                        try{

                            String prediction = response.getString("prediction");
                            String confidence = response.getString("confidence");

                            SQLiteDatabase db = dbHelper.getWritableDatabase();

                            db.execSQL("INSERT INTO patients(name,age,gender,education,apoe4,prediction,confidence) VALUES('"
                                    +name+"','"+age+"','"+gender+"','"+edu+"','"+apoe4+"','"+prediction+"','"+confidence+"')");

                            Intent i = new Intent(MainActivity.this, ResultActivity.class);

                            i.putExtra("name",name);
                            i.putExtra("prediction",prediction);
                            i.putExtra("confidence",confidence);

                            startActivity(i);

                        }catch(Exception e){
                            e.printStackTrace();
                        }

                    },
                    error -> Toast.makeText(this,"Server Error",Toast.LENGTH_SHORT).show()
            );

            queue.add(request);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}