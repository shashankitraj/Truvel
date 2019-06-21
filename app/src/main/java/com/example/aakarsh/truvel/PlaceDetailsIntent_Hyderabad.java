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

public class PlaceDetailsIntent_Hyderabad extends AppCompatActivity {

    int[] mResourcesImg={
            R.drawable.charminar,
            R.drawable.ramoji_filmcity,
            R.drawable.hussain_sagar,
            R.drawable.golconda_fort
    };
    String[] mResourcesTitle={
            "Char Minar",
            "Ramoji Film City",
            "Hussain Sagar Lake",
            "Golkonda"
    };
    TextView tvDetails;
    ViewPager vp;
    PlaceDetailsIntent_Hyderabad.CustomPagerAdapter mCustomPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details_intent);
        mCustomPagerAdapter = new PlaceDetailsIntent_Hyderabad.CustomPagerAdapter(this);

        tvDetails = findViewById(R.id.detailsBody);
        tvDetails.setText("Hyderabad is the capital of the Indian state of Telangana....\n"+"\n"+"Heritage buildings constructed during the Qutb Shahi" +
                " and Nizam eras showcase Indo-Islamic architecture influenced by Medieval, Mughal and European styles." +
                " After the 1908 flooding of the Musi River, the city was expanded and civic monuments constructed, particularly during the rule of" +
                " Mir Osman Ali Khan (the VIIth Nizam), whose patronage of architecture led to him being referred to as the maker of modern Hyderabad." +
                " In 2012, the government of India declared Hyderabad the first \"Best heritage city of India\".\n");
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
