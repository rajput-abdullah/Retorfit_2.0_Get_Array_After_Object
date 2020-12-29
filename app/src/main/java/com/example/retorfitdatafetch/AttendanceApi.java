package com.example.retorfitdatafetch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AttendanceApi {
//    @GET("user_info")
//    Call<List<Employee>> getPosts();
    @GET("api/departments")
    Call<DepartmentsData> getDepartments();
}
