package com.example.crud_user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllFragment extends Fragment {
    RecyclerView recyclerViewEtudaint;
    RecyclerView.LayoutManager layoutManager;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AllFragment() {
        // Required empty public constructor
    }
    public static AllFragment newInstance(String param1, String param2) {
        AllFragment fragment = new AllFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_all, container, false);
        recyclerViewEtudaint = v.findViewById(R.id.rcvUser);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewEtudaint.setLayoutManager(layoutManager);
        if (getArguments() != null) {
            String Url=getArguments().getString("url","");
            if (!TextUtils.isEmpty(Url)) {

                Retrofit Rf = new Retrofit.Builder().baseUrl(Url).addConverterFactory(GsonConverterFactory.create()).build();
                ApiHandler api = Rf.create(ApiHandler.class);
                Call<List<User>> Listuser = api.getAllUsers();
                System.out.println("\n\n\nAPI URL: " + Url);
                Listuser.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Response<List<User>> response, Retrofit retrofit) {
                        System.out.println("\n\n\n"+response.body()+"\n\n\n");
                        if(response.isSuccess()){
                            List<User> users = response.body();
                            UserAdapter userAdapter = new UserAdapter(getContext(), users);
                            recyclerViewEtudaint.setAdapter(userAdapter);
                        }else {
                            Toast.makeText(getContext(), "Failed to get users", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getContext(), "Failed to get users: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("Failed to get users: " + t.getMessage());
                    }
                });
            }
            else {
                Toast.makeText(getContext(), "Base URL is null or empty", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Arguments are null", Toast.LENGTH_SHORT).show();
        }
        return v;
    }
}