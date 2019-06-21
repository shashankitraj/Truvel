package com.example.aakarsh.truvel;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.example.aakarsh.truvel.Adapter.CustomPagerAdapter;

public class PlaceDetailsIntent_NewDelhi extends AppCompatActivity {

    int[] mResourcesImg={
            R.drawable.red_fort,
            R.drawable.india_gate,
            R.drawable.qutub_minar,
            R.drawable.jama_masjid
    };
    String[] mResourcesTitle={
            "Red Fort",
            "India Gate",
            "Qutub Minar",
            "Jama Masjid"
    };
    TextView tvDetails;
    ViewPager vp;
    CustomPagerAdapter mCustomPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details_intent);
        mCustomPagerAdapter = new CustomPagerAdapter(this);

        tvDetails = findViewById(R.id.detailsBody);
        tvDetails.setText("New Delhi is the capital of India....\n"+"\n"+"New Delhi is home to several historic sites and museums." +
                "New Delhi is home to Indira Gandhi Memorial Museum, National Gallery of Modern Art," +
                " National Museum of Natural History, National Rail Museum, Jantar Mantra,Raj Ghat." +
                "It is particularly renowned for its beautifully landscaped gardens that can look quite stunning in spring." +
                " The largest of these include Buddha Jayanti Park and the historic Lodi Gardens.\n");

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
