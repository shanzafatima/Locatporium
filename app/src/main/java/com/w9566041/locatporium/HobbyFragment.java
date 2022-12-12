package com.w9566041.locatporium;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HobbyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HobbyFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "MoviesFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HobbyFragment() {
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
    public static HobbyFragment newInstance(String param1, String param2) {
        HobbyFragment fragment = new HobbyFragment();
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
    private StringBuilder activity;
    private Button btnShare, btnSave;
    private ImageView imgSavedActivities;
    private Executor executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hobby, container, false);
        initComponents(view);
        initListeners();
        searchActivity();
        return view;
    }

    private void initComponents(View view) {
        etMovies = view.findViewById(R.id.etMovies);
        rvMovies = view.findViewById(R.id.rvMovies);
        btnMovieSearch = view.findViewById(R.id.btnSearchActivity);
        textView = view.findViewById(R.id.tvHobby);
        btnShare = view.findViewById(R.id.btnShare);
        btnSave = view.findViewById(R.id.btnSave);
        imgSavedActivities = view.findViewById(R.id.imgSavedActivities);
    }

    private void initListeners() {
        btnMovieSearch.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        imgSavedActivities.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSearchActivity) {
            searchActivity();
        } else if (v.getId() == R.id.btnShare) {
            shareHobby();
        } else if (v.getId() == R.id.btnSave) {
            addItemToSavedItem();
        } else if (v.getId() == R.id.imgSavedActivities) {
            showSavedActivities();
        }
    }

    private RequestQueue mRequestQueue;
    private DtoActivity model;

    private void searchActivity() {
        String url = "https://www.boredapi.com/api/activity";
        ProgressDialog pDialog = new ProgressDialog(requireContext());
        pDialog.setMessage("Fetching something exciting...");
        pDialog.show();
        mRequestQueue = Volley.newRequestQueue(requireContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            Log.i(TAG, "Response :" + response.toString());
            try {
                if (response.has("activity") && response.has("type") && response.has("participants") && response.has("price") && response.has("link") && response.has("accessibility")) {
                    activity = new StringBuilder().append("Activity Title : ").append(response.getString("activity") + "\n\n").append("Activity Type : ").append(response.getString("type") + "\n\n").append("Participants Required : ").append(response.getInt("participants") + "\n\n").append("Price : ").append(response.getDouble("price") + "\n\n").append("Activity Link : ").append(response.getString("link") + "\n\n").append("Activity Accessibility : ").append(response.getDouble("accessibility") + "\n\n");
                    textView.setText(activity);
                }
                Gson gson = new Gson();
                model = gson.fromJson(response.toString(), DtoActivity.class);


            } catch (Exception exception) {
                Log.e(TAG, "onResponse: ", exception);
            }
            pDialog.dismiss();
        }, error -> {
            if (error == null || error.networkResponse == null) {
                return;
            }
            String body = "";
            final String statusCode = String.valueOf(error.networkResponse.statusCode);
            try {
                body = new String(error.networkResponse.data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // exception
            }
            Log.i(TAG, "Error :" + body);
            pDialog.dismiss();
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    private void shareHobby() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, activity.toString());
        sendIntent.putExtra(Intent.EXTRA_TITLE, "Something worthy...");
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    private void addItemToSavedItem() {
        executor.execute(() -> DatabaseEngine.getInstance().getDBInstance().activityDao().addNewActivity(model));
        Toast.makeText(requireContext(), "Activity saved.", Toast.LENGTH_SHORT).show();

    }

    private void showSavedActivities() {
        executor.execute(() -> {
            List<DtoActivity> activities = DatabaseEngine.getInstance().getDBInstance().activityDao().getAllActivities();
            handler.post(() -> {
                if (activities.isEmpty()) {
                    Toast.makeText(requireContext(), "No saved items..", Toast.LENGTH_SHORT).show();
                } else {
                    NavController navController = NavHostFragment.findNavController(this);
                    navController.navigate(R.id.action_moviesFragment_to_savedHobbiesFragment, null, new NavOptions.Builder().setEnterAnim(android.R.animator.fade_in).setExitAnim(android.R.animator.fade_out).build());
                }
            });
        });
    }

}