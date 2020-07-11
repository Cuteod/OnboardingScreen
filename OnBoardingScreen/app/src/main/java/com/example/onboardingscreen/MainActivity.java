package com.example.onboardingscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout layoutOnboard;
    private MaterialButton buttonOnBoardingAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOnboard = findViewById(R.id.layoutOnBoard);
        buttonOnBoardingAction = findViewById(R.id.buttonOnBoard);

        setupOnboardingItems();
        setupOnBoardingIndicators();
        setCurrentOnBoardingIndicator(0);

        final ViewPager2 onboardViewPager = findViewById(R.id.onBoardViewPager);
        onboardViewPager.setAdapter(onBoardingAdapter);

        onboardViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicator(position);
            }
        });

        buttonOnBoardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardViewPager.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()){
                    onboardViewPager.setCurrentItem(onboardViewPager.getCurrentItem() + 1);

                }else {
                    startActivity(new Intent(getApplicationContext(), SecondActivity.class));
                    finish();
                }
            }
        });
    }
    private void setupOnboardingItems(){
        List<OnBoardingItem> onBoardingItems = new ArrayList<>();
        OnBoardingItem itemMotivate = new OnBoardingItem();
        itemMotivate.setTitle("Keep Calm");
        itemMotivate.setDescription("Feel less stressed and more mindful with meditation.");
        itemMotivate.setImage(R.drawable.keep_calm);

        OnBoardingItem itemMindful = new OnBoardingItem();
        itemMindful.setTitle("Mindfulness");
        itemMindful.setDescription("Mind is very powerful asset that we possess, to take care of our mind is of utmost priority.");
        itemMindful.setImage(R.drawable.mindfulness);

        OnBoardingItem itemFocus = new OnBoardingItem();
        itemFocus.setTitle("Stay focus");
        itemFocus.setDescription("Let's maintain focus by meditating everyday without hassle.");
        itemFocus.setImage(R.drawable.stay_focused);

        onBoardingItems.add(itemMotivate);
        onBoardingItems.add(itemMindful);
        onBoardingItems.add(itemFocus);

        onBoardingAdapter = new OnBoardingAdapter(onBoardingItems);
    }
    private void setupOnBoardingIndicators(){
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.second_shape));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboard.addView(indicators[i]);
        }
    }
    private void setCurrentOnBoardingIndicator(int index){
        int childCount = layoutOnboard.getChildCount();
        for (int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView) layoutOnboard.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext()
                , R.drawable.button_shape));
            }else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.second_shape));
            }
        }
        if (index == onBoardingAdapter.getItemCount() - 1){
            buttonOnBoardingAction.setText("Get started");

        }else{
            buttonOnBoardingAction.setText("Next");
            buttonOnBoardingAction.getResources().getColor(R.color.colorAppBackground);
        }
    }
}