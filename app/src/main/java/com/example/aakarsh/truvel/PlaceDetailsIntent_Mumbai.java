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

public class PlaceDetailsIntent_Mumbai extends AppCompatActivity {

    int[] mResourcesImg={
            R.drawable.gateway_india,
            R.drawable.elephanta_caves,
            R.drawable.marine_drive,
            R.drawable.chhatapati_shivaji_terminus
    };
    String[] mResourcesTitle={
            "Gateway of India",
            "Elephanta Caves",
            "Marine Drive",
            "Chhatrapati Shivaji Terminus"
    };
    TextView tvDetails;
    ViewPager vp;
    PlaceDetailsIntent_Mumbai.CustomPagerAdapter mCustomPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details_intent);
        mCustomPagerAdapter = new PlaceDetailsIntent_Mumbai.CustomPagerAdapter(this);


        tvDetails = findViewById(R.id.detailsBody);
        tvDetails.setText("umbai is the capital city of the Indian state of Maharashtra...\n"+"\n"+"The architecture of the city is a blend of Gothic Revival," +
                " Indo-Saracenic, Art Deco, and other contemporary styles.Mumbai has a tropical climate," +
                " specifically a tropical wet and dry climate with seven months of dryness and peak of rains in July." +
                "It's culture is a blend of traditional festivals, food, music, and theatres." +
                " The city offers a cosmopolitan and diverse lifestyle with a variety of food, entertainment, and night life," +
                " available in a form and abundance comparable to that in other world capitals.\n");
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
