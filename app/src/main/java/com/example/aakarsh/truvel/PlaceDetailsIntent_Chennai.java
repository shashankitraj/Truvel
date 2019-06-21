package com.example.aakarsh.truvel;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlaceDetailsIntent_Chennai extends AppCompatActivity {

    int[] mResourcesImg={
            R.drawable.marina_beach,
            R.drawable.kapaleeshwar_temple,
            R.drawable.fort_st_george,
            R.drawable.st_thomas_basilica
    };
    String[] mResourcesTitle={
            "Marina Beach",
            "Kapaleeshwarar Temple",
            "Fort St. George",
            "St. Thomas Basilica"
    };
    TextView tvDetails;
    ViewPager vp;
    PlaceDetailsIntent_Chennai.CustomPagerAdapter mCustomPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details_intent);
        mCustomPagerAdapter = new PlaceDetailsIntent_Chennai.CustomPagerAdapter(this);

        tvDetails = findViewById(R.id.detailsBody);
        tvDetails.setText("Chennai is the capital of the Indian state of Tamil Nadu....\n"+"\n"+"With temples, beaches and centres of historical" +
                " and cultural significance, including the UNESCO Heritage Site of Mahabalipuram, Chennai is one of the most visited cities in India." +
                " The city serves as the gateway to the southern part of India with tourists landing in the city and starting their trip to the rest" +
                " of the region. Chennai was the most visited Indian city by foreign tourists in 2009 and issued the third highest number of visas on arrival" +
                " in 2014.\n");
        vp = (ViewPager) findViewById(R.id.viewPager);
        vp.setAdapter(mCustomPagerAdapter);
    }

    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResourcesImg.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.detailsImage);
            imageView.setImageResource(mResourcesImg[position]);

            TextView tvTitle = (TextView) itemView.findViewById(R.id.pager_item_title);
            tvTitle.setText(mResourcesTitle[position]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
