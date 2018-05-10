package vn.edu.ptithcm.mytrip006.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by LENOVO on 5/3/2018.
 */

public class PageAdapterFavorite extends FragmentStatePagerAdapter {

    private int numOfTab;
    public PageAdapterFavorite(FragmentManager fm , int NumOfTab){
        super(fm);
        this.numOfTab = NumOfTab;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TourFragmentFavorite tourFragmentFavorite = new TourFragmentFavorite();
                return tourFragmentFavorite;
            case 1:
                FoodFragmentFavorite foodFragmentFavorite = new FoodFragmentFavorite();
                return foodFragmentFavorite;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTab;
    }
}
