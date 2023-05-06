package com.example.crud_user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText nom_input,username_input,email_input,password_input;
    private Button btnAdduser;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addFragment newInstance(String param1, String param2) {
        addFragment fragment = new addFragment();
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

        View v =inflater.inflate(R.layout.fragment_add, container, false);
        nom_input=v.findViewById(R.id.input_1);
        username_input=v.findViewById(R.id.input_2);
        password_input=v.findViewById(R.id.input_3);
        email_input=v.findViewById(R.id.input_4);
        btnAdduser=v.findViewById(R.id.btnadd);
        btnAdduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
        return v;
    }
    public void addUser(){
        String name=nom_input.getText().toString().trim();
        String username=username_input.getText().toString().trim();
        String password=password_input.getText().toString().trim();
        String email=email_input.getText().toString().trim();
        if (getArguments() != null) {
            String Url=getArguments().getString("url","");
            System.out.println("\n\n\n"+Url+"\n\n\n");
            if (!TextUtils.isEmpty(Url)) {
                Retrofit Rf = new Retrofit.Builder().baseUrl(Url).addConverterFactory(GsonConverterFactory.create()).build();
                ApiHandler api = Rf.create(ApiHandler.class);
                Call<User> adduser = api.insertUser(name, username, password, email);
                adduser.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Response<User> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            Toast.makeText(getActivity(), "User Add", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getActivity().getApplication(), "Failed mochkla mil url" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        else {
            Toast.makeText(getContext(), "Base URL is null or empty", Toast.LENGTH_SHORT).show();
        }
    } else {
        Toast.makeText(getContext(), "Arguments are null", Toast.LENGTH_SHORT).show();
    }
        }
    }