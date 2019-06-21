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

public class PlaceDetailsIntent_Kolkata extends AppCompatActivity {

    int[] mResourcesImg={
            R.drawable.victoria_memorial,
            R.drawable.howrah_bridge,
            R.drawable.dakshineswar_temple,
            R.drawable.indian_museum
    };
    String[] mResourcesTitle={
            "Victoria Memorial",
            "Howrah Bridge",
            "Dakshineswas Kali Temple",
            "Indian Museum"
    };
    TextView tvDetails;
    ViewPager vp;
    PlaceDetailsIntent_Kolkata.CustomPagerAdapter mCustomPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details_intent);
        mCustomPagerAdapter = new PlaceDetailsIntent_Kolkata.CustomPagerAdapter(this);

        tvDetails = findViewById(R.id.detailsBody);
        tvDetails.setText("Kolkata is the capital city of the Indian state of West Benagal...\n"+"\n"+"Kolkata is known for its literary, artistic," +
                " and revolutionary heritage; as the former capital of India, it was the birthplace of modern Indian literary and artistic thought." +
                "Kolkata has been called the \"City of Furious, Creative Energy.Located on the east bank of the Hooghly River," +
                " it is the principal commercial, cultural, and educational centre of East India," +
                " while the Port of Kolkata is India's oldest operating port and its sole major riverine port." +
                " The city is widely regarded as the \"cultural capital\" of India, and is also nicknamed the \"City of Joy\".\n");
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
