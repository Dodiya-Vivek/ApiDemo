package com.example.exam_5dodiyavivek3_5_24.Post4;

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

import com.example.exam_5dodiyavivek3_5_24.Get2.GetApi1;
import com.example.exam_5dodiyavivek3_5_24.R;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostApi4 extends AppCompatActivity {

    EditText etID, etcreateAt;
    TextView txt1, txt2, txt3;
    Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_api4);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id = etID.getText().toString();
                String createAt = etcreateAt.getText().toString();

                if (Id.isEmpty() || createAt.isEmpty()) {
                    Toast.makeText(PostApi4.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    sendPostData(new PostData2(Id, createAt));
                }
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostApi4.this, GetApi1.class);
                startActivity(intent);
            }
        });

        etID = findViewById(R.id.etId);
        etcreateAt = findViewById(R.id.etCreateAt);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
    }

    private void sendPostData(PostData2 postData) { // Rename postData method
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface2 retrofitAPI = retrofit.create(ApiInterface2.class);
        Call<PostData2> call = retrofitAPI.post2(postData);

        call.enqueue(new Callback<PostData2>() {
            @Override
            public void onResponse(@NonNull Call<PostData2> call, @NonNull Response<PostData2> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PostData2 responsePost = response.body();
                    Toast.makeText(PostApi4.this, "Data added to API", Toast.LENGTH_SHORT).show();
                    Log.d("APIResponse", "Response: " + new Gson().toJson(response.body()));
                    txt1.setText("Response Code: " + response.code());
                    txt2.setText("ID: " + responsePost.getId());
                    txt3.setText("Name: " + responsePost.getCreateAt());
                } else {
                    Toast.makeText(PostApi4.this, "Failed to retrieve data. Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PostData2> call, @NonNull Throwable t) {
                Toast.makeText(PostApi4.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
