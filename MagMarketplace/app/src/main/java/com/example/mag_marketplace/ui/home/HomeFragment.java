package com.example.mag_marketplace.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mag_marketplace.AddItemActivity;
import com.example.mag_marketplace.FilterActivity;
import com.example.mag_marketplace.Item;
import com.example.mag_marketplace.ItemAdapter;
import com.example.mag_marketplace.R;
import com.example.mag_marketplace.SearchActivity;
import com.example.mag_marketplace.databinding.FragmentHomeBinding;
import com.example.mag_marketplace.databinding.ItemTransformBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<Item> itemListForYou = new ArrayList<>();
    private List<Item> itemListOther = new ArrayList<>();
    private Button Search,Filter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerViewForYou = binding.recyclerViewForYou;
        RecyclerView recyclerViewOther = binding.recyclerViewForOther;

        // Use separate ItemAdapters for each RecyclerView
        ItemAdapter adapterForYou = new ItemAdapter(itemListForYou);
        ItemAdapter adapterOther = new ItemAdapter(itemListOther);

        recyclerViewForYou.setAdapter(adapterForYou);
        recyclerViewForYou.setLayoutManager(new GridLayoutManager(requireContext(), 3));

        recyclerViewOther.setAdapter(adapterOther);
        recyclerViewOther.setLayoutManager(new GridLayoutManager(requireContext(), 3));

         Search = root.findViewById(R.id.search_button);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        Filter = root.findViewById(R.id.filter_button);
        Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FilterActivity.class);
                startActivity(intent);
            }
        });
        // Assuming you want to observe some LiveData from HomeViewModel
        homeViewModel.getYourLiveDataForYou().observe(getViewLifecycleOwner(), itemsForYou -> {
            // Update your itemListForYou with the new data
            itemListForYou.clear();
            itemListForYou.addAll(itemsForYou);
            // Notify the adapter about the data change
            adapterForYou.notifyDataSetChanged();
        });

        // Assuming you have a separate LiveData for "Other"
        homeViewModel.getYourLiveDataOther().observe(getViewLifecycleOwner(), itemsOther -> {
            // Update your itemListOther with the new data
            itemListOther.clear();
            itemListOther.addAll(itemsOther);
            // Notify the adapter about the data change
            adapterOther.notifyDataSetChanged();
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private static class TransformViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public TransformViewHolder(ItemTransformBinding binding) {
            super(binding.getRoot());
            imageView = binding.imageViewItemTransform;
            textView = binding.textViewItemTransform;
        }
    }
}
