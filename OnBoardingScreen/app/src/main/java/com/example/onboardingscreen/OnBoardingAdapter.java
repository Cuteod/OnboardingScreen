package com.example.onboardingscreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>{

    private List<OnBoardingItem> onBoardingItems;

    public OnBoardingAdapter(List<OnBoardingItem> onBoardingItems) {
        this.onBoardingItems = onBoardingItems;
    }

    @NonNull
    @Override
    public OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnBoardingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_onboard, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingViewHolder holder, int position) {
        holder.setOnboardingData(onBoardingItems.get(position));

    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    class OnBoardingViewHolder extends RecyclerView.ViewHolder{
        private TextView TxtTitle;
        private TextView TxtDescription;
        private ImageView imageBoard;

        OnBoardingViewHolder(@NonNull View itemView) {
            super(itemView);
            TxtTitle = itemView.findViewById(R.id.TxtTitle);
            TxtDescription = itemView.findViewById(R.id.TxtDescription);
            imageBoard = itemView.findViewById(R.id.imageOnBoard);
        }
        void setOnboardingData(OnBoardingItem onBoardingItem){
            TxtTitle.setText(onBoardingItem.getTitle());
            TxtDescription.setText(onBoardingItem.getDescription());
            imageBoard.setImageResource(onBoardingItem.getImage());

        }
    }
}
