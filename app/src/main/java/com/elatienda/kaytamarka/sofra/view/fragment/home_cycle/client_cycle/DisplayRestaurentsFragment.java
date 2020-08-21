package com.elatienda.kaytamarka.sofra.view.fragment.home_cycle.client_cycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import com.elatienda.kaytamarka.sofra.R;
import com.elatienda.kaytamarka.sofra.databinding.ActivityHomeCycleBinding;
import com.elatienda.kaytamarka.sofra.databinding.FragmentDisplayRestaurantsBinding;
import com.elatienda.kaytamarka.sofra.util.HelperMethod;
import com.elatienda.kaytamarka.sofra.view.activity.SplashActivity;
import com.elatienda.kaytamarka.sofra.view.fragment.BaseFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import static com.elatienda.kaytamarka.sofra.util.HelperMethod.keyboardVisibility;

public class DisplayRestaurentsFragment extends BaseFragment {

    private FragmentDisplayRestaurantsBinding binding;
    private ActivityHomeCycleBinding activityBinding;
    private BottomNavigationView navBar;

    public DisplayRestaurentsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_display_restaurants,container,false);
        View view = binding.getRoot();
        setUpActivity();
        activityBinding = DataBindingUtil.inflate(inflater, R.layout.activity_home_cycle ,container,false);

        HelperMethod.customToolbar(getActivity(),false,"", R.color.white);

        if (navBar != null){
            navBar.setVisibility(View.VISIBLE);
        }

        keyboardVisibility(view);

        return view;
    }

    @Override
    public void onBack() {
        startActivity(new Intent(getActivity(), SplashActivity.class));
    }
}