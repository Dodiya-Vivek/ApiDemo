package com.example.exam_5dodiyavivek3_5_24;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exam_5dodiyavivek3_5_24.Get1.GetApi2;
import com.example.exam_5dodiyavivek3_5_24.post1.ApiInterface;
import com.example.exam_5dodiyavivek3_5_24.post1.PostData;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText etId, etName, etYear, etColor, pantone_value;
    TextView txt1, txt2, txt3, txt4, txt5, txt6;
    Button btnSubmit,btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetApi2.class);
                startActivity(intent);
            }
        });


        etId = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etYear = findViewById(R.id.etYear);
        etColor = findViewById(R.id.etColor);
        pantone_value = findViewById(R.id.etpantone_value);
        btnSubmit = findViewById(R.id.btnsubmit);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6 = findViewById(R.id.txt6);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String name = etName.getText().toString();
                String year = etYear.getText().toString();
                String color = etColor.getText().toString();
                String pantone = pantone_value.getText().toString();

                if (id.isEmpty() || name.isEmpty() || year.isEmpty() || color.isEmpty() || pantone.isEmpty()){
                    Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    postData(new PostData(id, name, year, color, pantone));
                }
            }
        });
    }

    private void postData(PostData postData){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface retrofitAPI = retrofit.create(ApiInterface.class);
        Call<PostData> call = retrofitAPI.createPostData(postData);

        call.enqueue(new Callback<PostData>() {
            @Override
            public void onResponse(@NonNull Call<PostData> call, @NonNull Response<PostData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PostData responsePost = response.body();
                    Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
                    Log.d("APIResponse", "Response: " + new Gson().toJson(response.body()));
                    txt1.setText("Response Code: " + response.code());
                    txt2.setText("ID: " + responsePost.getId());  // Ensure your PostData model has a getId() method.
                    txt3.setText("Name: " + responsePost.getName());
                    txt4.setText("Year: " + responsePost.getYear());
                    txt5.setText("Color: " + responsePost.getColor());
                    txt6.setText("Pantone Value: " + responsePost.getPantone_value());
                } else {
                    Toast.makeText(MainActivity.this, "Failed to retrieve data. Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostData> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
