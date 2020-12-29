package com.example.retorfitdatafetch;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView text_view_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_view_result = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.11.153:8082/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AttendanceApi jsonPlaceHolderApi = retrofit.create(AttendanceApi.class);
        Call<DepartmentsData> call = jsonPlaceHolderApi.getDepartments();

        call.enqueue(new Callback<DepartmentsData>() {
            @Override
            public void onResponse(Call<DepartmentsData> call, Response<DepartmentsData> response) {

                Log.d("datadata", "onResponse: "+response.body());



                String s="";
                for (int i = 0; i < response.body().data.size(); i++) {
                    s = s + response.body().data.get(i).getDepartmentName() + "\n";
                }
                text_view_result.setText(s);

            }

            @Override
            public void onFailure(Call<DepartmentsData> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}