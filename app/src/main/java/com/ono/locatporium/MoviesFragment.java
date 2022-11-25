package com.ono.locatporium;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "MoviesFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoviesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesFragment newInstance(String param1, String param2) {
        MoviesFragment fragment = new MoviesFragment();
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

    private EditText etMovies;
    private RecyclerView rvMovies;
    private Button btnMovieSearch;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        initComponents(view);
        initListeners();
        return view;
    }

    private void initComponents(View view) {
        etMovies = view.findViewById(R.id.etMovies);
        rvMovies = view.findViewById(R.id.rvMovies);
        btnMovieSearch = view.findViewById(R.id.btnMovieSearch);
        textView = view.findViewById(R.id.tvHobby);
    }

    private void initListeners() {
        btnMovieSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnMovieSearch) {
            searchMovieOnline();
        }
    }

    private RequestQueue mRequestQueue;

    private void searchMovieOnline() {
//        String url = String.format("https://movie-database-alternative.p.rapidapi.com/?s=%1$s&r=json&page=1", movieName);
        String url = "https://www.boredapi.com/api/activity";
        ProgressDialog pDialog = new ProgressDialog(requireContext());
        pDialog.setMessage("Loading...PLease wait");
        pDialog.show();

        mRequestQueue = Volley.newRequestQueue(requireContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "Response :" + response.toString());

                try {
                    if (response.has("activity") && response.has("type") && response.has("participants") && response.has("price") && response.has("link") && response.has("accessibility")) {
                        StringBuilder value = new StringBuilder().append("Activity Title : ").append(response.getString("activity") + "\n\n").append("Activity Type : ").append(response.getString("type") + "\n\n").append("Participants Required : ").append(response.getInt("participants") + "\n\n").append("Price : ").append(response.getDouble("price") + "\n\n").append("Activity Link : ").append(response.getString("link") + "\n\n").append("Activity Accessibility : ").append(response.getDouble("accessibility") + "\n\n");
                        textView.setText(value);
                    }
                } catch (Exception exception) {
                    Log.e(TAG, "onResponse: ", exception);
                }


                pDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null || error.networkResponse == null) {
                    return;
                }

                String body = "";
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    body = new String(error.networkResponse.data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // exception
                }

                Log.i(TAG, "Error :" + body);


                pDialog.dismiss();
            }


        }) {
            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("X-RapidAPI-Key", "27eb204886msh7788a5c81dac783p10b70bjsn81a33d56dc0b");
                headers.put("X-RapidAPI-Host", "movie-database-alternative.p.rapidapi.com");
                return headers;
            }
        };

        mRequestQueue.add(jsonObjectRequest);

    }
}