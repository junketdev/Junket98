package com.test.junket.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test.junket.R;

import java.util.ArrayList;
import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    List<Integer> imagesArray = new ArrayList<>();

    public CustomPagerAdapter(Context context) {
        mContext = context;

        imagesArray.add(R.drawable.bg);
        imagesArray.add(R.drawable.bg1);

    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.layout_image_row, collection, false);

        ImageView imgDest = (ImageView)layout.findViewById(R.id.imgDest);

        imgDest.setImageResource(imagesArray.get(position));

        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return imagesArray.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}
