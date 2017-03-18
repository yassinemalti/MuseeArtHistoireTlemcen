package com.wordpress.yassinemalti.museearthistoiretlemcen.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.wordpress.yassinemalti.museearthistoiretlemcen.R;

public class AccueilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AccueilFragment() {
        // Required empty public constructor
    }

    public static AccueilFragment newInstance(String notificationImageURL) {
        AccueilFragment fragment = new AccueilFragment();
        Bundle args = new Bundle();
        args.putString("imageUri", notificationImageURL);
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

        View rootView = inflater.inflate(R.layout.fragment_accueil, container, false);
        NativeExpressAdView adBanner_1 = (NativeExpressAdView) rootView.findViewById(R.id.adBanner_1);
        AdRequest request_1 = new AdRequest.Builder().build();
        adBanner_1.loadAd(request_1);

        Bundle args = getArguments();
        String notificationImageURL = args.getString("imageUri");

        if(notificationImageURL!=null)
            new DownloadImageTask((ImageView) rootView.findViewById(R.id.notificationImageView))
                    .execute(notificationImageURL);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
