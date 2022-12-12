package com.w9566041.locatporium;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initComponents(view);
        initListeners();
        return view;
    }

    private TextView tvMovies, tvMemories, tvPlaces, tvLogout;

    private void initComponents(View view) {
        tvMovies = view.findViewById(R.id.tvMovies);
        tvMemories = view.findViewById(R.id.tvMemories);
        tvPlaces = view.findViewById(R.id.tvPlaces);
        tvLogout = view.findViewById(R.id.tvLogout);
    }

    private void initListeners() {
        tvMovies.setOnClickListener(this);
        tvMemories.setOnClickListener(this);
        tvPlaces.setOnClickListener(this);
        tvLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvMovies) {
            navigateFragment(ActionType.MOVIE);
        } else if (v.getId() == R.id.tvMemories) {
            navigateFragment(ActionType.CAMERA);
        } else if (v.getId() == R.id.tvPlaces) {
//            navigateFragment(ActionType.MOVIE);
        } else if (v.getId() == R.id.tvLogout) {
            requireActivity().finish();
        }
    }

    private void navigateFragment(ActionType actionType) {
        NavController navController = NavHostFragment.findNavController(this);
        if (actionType == ActionType.MOVIE) {
            navController.navigate(
                    R.id.action_mainFragment_to_moviesFragment,
                    null,
                    new NavOptions.Builder()
                            .setEnterAnim(android.R.animator.fade_in)
                            .setExitAnim(android.R.animator.fade_out)
                            .build()
            );
        } else if (actionType == ActionType.CAMERA) {
            navController.navigate(
                    R.id.action_mainFragment_to_memoriesFragment,
                    null,
                    new NavOptions.Builder()
                            .setEnterAnim(android.R.animator.fade_in)
                            .setExitAnim(android.R.animator.fade_out)
                            .build()
            );
        }


    }
}