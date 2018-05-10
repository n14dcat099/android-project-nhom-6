package vn.edu.ptithcm.mytrip006.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by LENOVO on 5/3/2018.
 */

public class PageAdapterMyPlace extends FragmentStatePagerAdapter {

    private int numOfTab;
    public PageAdapterMyPlace(FragmentManager fm , int NumOfTab){
        super(fm);
        this.numOfTab = NumOfTab;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TourFragmentMyPlace tourFragmentMyPlace = new TourFragmentMyPlace();
                return tourFragmentMyPlace;
            case 1:
                FoodFragmentMyPlace foodFragmentMyPlace = new FoodFragmentMyPlace();
                return foodFragmentMyPlace;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTab;
    }
}
