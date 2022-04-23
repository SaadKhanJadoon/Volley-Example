package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);

        String username = "Alpha";
        String password = "123";

        StringRequest myReq = new StringRequest(Request.Method.GET, "http://localhost:8000/api/v1/auth/login?name="+username+";password="+password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("api", response);

                List<Todo> todoList = new ArrayList<>();
                Gson gson = new Gson();

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                        Todo todo = gson.fromJson(jsonObject.toString(),Todo.class);
                        todoList.add(todo);
                    }

                    Log.i("Test",todoList.size()+"");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.i("api", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("api", error.toString());
            }
        });
        queue.add(myReq);
    }
}
