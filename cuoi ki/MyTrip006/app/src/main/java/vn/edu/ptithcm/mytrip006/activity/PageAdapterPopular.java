package vn.edu.ptithcm.mytrip006.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by LENOVO on 5/3/2018.
 */

public class PageAdapterPopular extends FragmentStatePagerAdapter {
    private int numOfTab;
    public PageAdapterPopular(FragmentManager fm , int NumOfTab){
        super(fm);
        this.numOfTab = NumOfTab;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                TourFragmentPopular tourFragmentPopular = new TourFragmentPopular();
                return tourFragmentPopular;
            case 1:
                FoodFragmentPopular foodFragmentPopular = new FoodFragmentPopular();
                return foodFragmentPopular;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return numOfTab;
    }
}
