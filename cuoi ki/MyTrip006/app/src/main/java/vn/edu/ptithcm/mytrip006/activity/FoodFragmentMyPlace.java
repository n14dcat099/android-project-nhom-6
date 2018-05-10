package vn.edu.ptithcm.mytrip006.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.ptithcm.mytrip006.R;

/**
 * Created by LENOVO on 5/3/2018.
 */

public class FoodFragmentMyPlace extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        return inflater.inflate( R.layout.fragment_food_my_place,container,false );
    }
}
