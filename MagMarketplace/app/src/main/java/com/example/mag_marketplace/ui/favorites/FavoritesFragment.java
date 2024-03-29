package com.example.mag_marketplace.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.mag_marketplace.databinding.FragmentFavoritesBinding;

public class FavoritesFragment extends Fragment {

private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

    binding = FragmentFavoritesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textReflow2;
        favoritesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}