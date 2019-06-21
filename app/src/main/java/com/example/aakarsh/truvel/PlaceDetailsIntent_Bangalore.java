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

public class PlaceDetailsIntent_Bangalore extends AppCompatActivity {

    int[] mResourcesImg={
            R.drawable.lal_bagh,
            R.drawable.bannerghatta_national_park,
            R.drawable.cubbon_park,
            R.drawable.wonderla
    };
    String[] mResourcesTitle={
            "Lal Bagh",
            "Bannerghatta National Park",
            "Cubbon Park",
            "Wonderla"
    };
    TextView tvDetails;
    ViewPager vp;
    PlaceDetailsIntent_Bangalore.CustomPagerAdapter mCustomPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details_intent);
        mCustomPagerAdapter = new PlaceDetailsIntent_Bangalore.CustomPagerAdapter(this);

        tvDetails = findViewById(R.id.detailsBody);
        tvDetails.setText("Bangalore is the capital of the Indian state of Karnataka...\n"+"\n"+"Bangalore is sometimes referred to as the" +
                " Silicon Valley of India (or IT capital of India) because of its role as the nation's leading information technology (IT) exporter." +
                "Indian technological organisations ISRO, Infosys, Wipro and HAL are headquartered in the city." +
                " A demographically diverse city, Bangalore is the second fastest-growing major metropolis in India." +
                "It is home to many educational and research institutions in India.\n");
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
