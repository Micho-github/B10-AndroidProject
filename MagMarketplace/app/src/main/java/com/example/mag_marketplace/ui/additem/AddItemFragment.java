package com.example.mag_marketplace.ui.additem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mag_marketplace.AddItemActivity;
import com.example.mag_marketplace.ForgotPasswordActivity;
import com.example.mag_marketplace.LoginActivity;
import com.example.mag_marketplace.MainActivity;
import com.example.mag_marketplace.R;
import com.example.mag_marketplace.databinding.FragmentAdditemBinding;
import com.example.mag_marketplace.databinding.FragmentFavoritesBinding;
import com.example.mag_marketplace.ui.favorites.FavoritesViewModel;

public class AddItemFragment extends Fragment {

private FragmentAdditemBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        AdditemViewModel AdditemViewModel =
                new ViewModelProvider(this).get(AdditemViewModel.class);

    binding = FragmentAdditemBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textReflow;
        AdditemViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        LinearLayout linearLayout = root.findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewItem(v);
            }
        });

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void AddNewItem(View v){
        Log.d("AddItemFragment", "AddNewItem method called");
        // Your implementation
        Intent intent = new Intent(getActivity(), AddItemActivity.class);
        startActivity(intent);
    }

}