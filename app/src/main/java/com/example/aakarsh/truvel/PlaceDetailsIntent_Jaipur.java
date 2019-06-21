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

public class PlaceDetailsIntent_Jaipur extends AppCompatActivity {

    int[] mResourcesImg={
            R.drawable.amber_fort,
            R.drawable.hawa_mahal,
            R.drawable.jantar_mantar,
            R.drawable.city_palace
    };
    String[] mResourcesTitle={
            "Amber Fort",
            "Hawa Mahal",
            "Jantar Mantar",
            "City Palace"
    };
    TextView tvDetails;
    ViewPager vp;
    PlaceDetailsIntent_Jaipur.CustomPagerAdapter mCustomPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details_intent);
        mCustomPagerAdapter = new PlaceDetailsIntent_Jaipur.CustomPagerAdapter(this);

        tvDetails=findViewById(R.id.detailsBody);
        tvDetails.setText("Jaipur is the capital of the Indian state of Rajasthan...\n"+"\n"+"Jaipur is a major tourist destination in India forming a" +
                " part of the Golden Triangle. In the 2008 Conde Nast Traveller Readers Choice Survey, Jaipur was ranked the 7th best place to visit in Asia." +
                "Visitor attractions include the Hawa Mahal, Jal Mahal, City Palace, Amer Fort, Jantar Mantar, Nahargarh Fort, Jaigarh Fort etc." +
                "The city was planned according to Indian Vastu shastra by Vidyadhar Bhattacharya in 1727." +
                "There are three gates facing east, west, and north. The eastern gate is called Suraj pol (sun gate)," +
                " the western gate is called Chand pol (moon gate) and the northern gate faces the ancestral capital of Amer.\n");
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
