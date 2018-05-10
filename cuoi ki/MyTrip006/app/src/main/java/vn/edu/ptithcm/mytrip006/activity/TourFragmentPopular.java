package vn.edu.ptithcm.mytrip006.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.ptithcm.mytrip006.R;

/**
 * Created by LENOVO on 3/26/2018.
 */

public class TourFragmentPopular extends Fragment {

    @Override
    public View onCreateView (LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        return inflater.inflate( R.layout.fragment_tour_popular,container,false);
    }
}
