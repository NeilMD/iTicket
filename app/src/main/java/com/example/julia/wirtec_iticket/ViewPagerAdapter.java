package com.example.julia.wirtec_iticket;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Julia on 2/19/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> tabTitles = new ArrayList<>();

    public void addFragments (Fragment fragment, String title){
        boolean hasFragment = false;
        for(int i= 0; i < fragments.size(); i++){
            if (fragments.get(i).equals(fragment)){
                hasFragment = true;
            }
        }

        if(hasFragment == false){
            this.fragments.add(fragment);
            this.tabTitles.add(title);
            System.out.println("Added!");

        }
    }

    public void removeFragment(Fragment f) {
        boolean hasFragment = false;
        int tabpos = 0;
        for(int i= 0; i < fragments.size(); i++){
            if (fragments.get(i).equals(f)){
                hasFragment = true;
                tabpos = i;
            }
        }

        if(hasFragment == true){
            this.tabTitles.remove(tabpos);
            this.fragments.remove(tabpos);
            System.out.println("Removed!");
        }

    }

    public ViewPagerAdapter (FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public void clearAllFragments(){
        this.fragments.clear();
        this.tabTitles.clear();

    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
