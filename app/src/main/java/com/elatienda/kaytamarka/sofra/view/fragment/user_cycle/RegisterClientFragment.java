package com.elatienda.kaytamarka.sofra.view.fragment.user_cycle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.elatienda.kaytamarka.sofra.R;
import com.elatienda.kaytamarka.sofra.adapter.SpinnerAdapter;
import com.elatienda.kaytamarka.sofra.data.model.general_response.GeneralResponse;
import com.elatienda.kaytamarka.sofra.data.model.general_response_not_paginated.GeneralResponseNotPaginated;
import com.elatienda.kaytamarka.sofra.data.model.register.ClientRegister;
import com.elatienda.kaytamarka.sofra.databinding.FragmentRegisterClientBinding;
import com.elatienda.kaytamarka.sofra.util.HelperMethod;
import com.elatienda.kaytamarka.sofra.view.fragment.BaseFragment;
import com.elatienda.kaytamarka.sofra.view.fragment.BaseViewModel;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.elatienda.kaytamarka.sofra.data.api.ApiClient.getClient;
import static com.elatienda.kaytamarka.sofra.util.Constants.CLIENT_GALLERY_REQUEST_CODE;
import static com.elatienda.kaytamarka.sofra.util.Constants.USER_CLIENT;
import static com.elatienda.kaytamarka.sofra.util.Constants.USER_TYPE_FILE;
import static com.elatienda.kaytamarka.sofra.util.Constants.USER_TYPE_KEY;
import static com.elatienda.kaytamarka.sofra.util.HelperMethod.closeKeypad;

public class RegisterClientFragment extends BaseFragment {

    //private ActivityHomeCycleBinding navBinding;
    private UserViewModel viewModel;
    private BaseViewModel baseModel;
    private SpinnerAdapter cityAdapter;
    private SpinnerAdapter regionAdapter;
    private boolean imageFlag = false;
    private FragmentRegisterClientBinding binding;
    private Uri imageData;
    private String userType;
    private File imageFile;

    public RegisterClientFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_register_client,container,false);
        View view = binding.getRoot();
        setUpActivity();

        cityAdapter = new SpinnerAdapter(getActivity(),R.color.white);

//        BottomNavigationView navBar = navBinding.activityHomeCycleBtnNav;
//        navBar.setVisibility(View.GONE);

        if (getActivity()!=null){
            SharedPreferences userTypePreference = getActivity().getSharedPreferences(USER_TYPE_FILE, Context.MODE_PRIVATE);
            userType = userTypePreference.getString(USER_TYPE_KEY,null);

            //initialize viewModel
            viewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);
            baseModel = new ViewModelProvider(getActivity()).get(BaseViewModel.class);
        }


        getCitiesObserve();

        binding.fragmentRegisterClientCImgVProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity()!=null){
                    closeKeypad(getActivity());
                }
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Choose an image"), CLIENT_GALLERY_REQUEST_CODE);
            }
        });

        binding.fragmentRegisterClientBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity()!=null){
                    closeKeypad(getActivity());
                }
                onCheck();
            }
        });

        binding.fragmentRegisterClientSpCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity()!=null){
                    closeKeypad(getActivity());
                }
            }
        });

        binding.fragmentRegisterClientSpDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity()!=null){
                    closeKeypad(getActivity());
                }
            }
        });

        return view;
    }

    private void getCitiesObserve() {
        baseModel.getCities(getClient().getCitiesNotPaginated());
        if (getActivity()!=null){
            baseModel.citiesMutableLiveData.observe(getActivity(), new Observer<GeneralResponse>() {
                @Override
                public void onChanged(GeneralResponse generalResponse) {
                    try{
                        if(generalResponse.getStatus() == 1){
                            binding.fragmentRegisterClientSpCity.setVisibility(View.VISIBLE);
                            cityAdapter.setData(generalResponse.getData(), "Select City");
                            binding.fragmentRegisterClientSpCity.setAdapter(cityAdapter);
                        }else{
                            Toast.makeText(getActivity(), "onResponse status-0: \n" + generalResponse.getMsg() , Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(getActivity(), "Exeption: \n" + e.getMessage() , Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void onCheck() {
        if(!imageFlag ||
                binding.fragmentRegisterClientEtName.getText().toString().isEmpty() ||
                binding.fragmentRegisterClientEtEmail.getText().toString().isEmpty() ||
                binding.fragmentRegisterClientEtPhone.getText().toString().isEmpty() ||
                //binding.fragmentRegisterClientSpCity.getSelectedItemPosition() == 0 ||
                //binding.fragmentRegisterClientSpDistrict.getSelectedItemPosition() == 0 ||
                binding.fragmentRegisterClientEtPassword.getText().toString().isEmpty() ||
                binding.fragmentRegisterClientEtConfirmPassword.getText().toString().isEmpty()){

            if(!imageFlag){
                binding.fragmentRegisterClientCImgVProfile.setBorderWidth(5);
                binding.fragmentRegisterClientCImgVProfile.setBorderColor(Color.RED);
            }

            if (binding.fragmentRegisterClientEtName.getText().toString().isEmpty()){
                binding.fragmentRegisterClientEtName.setError(getString(R.string.register_client_et_name_error));
            }

            if (binding.fragmentRegisterClientEtEmail.getText().toString().isEmpty()){
                binding.fragmentRegisterClientEtEmail.setError(getString(R.string.login_et_email_error));
            }

            if (binding.fragmentRegisterClientEtPhone.getText().toString().isEmpty()){
                binding.fragmentRegisterClientEtPhone.setError(getString(R.string.register_client_et_phone_error));
            }

            if(binding.fragmentRegisterClientSpDistrict.getSelectedItemPosition() == 0 ||
                    binding.fragmentRegisterClientSpDistrict.getSelectedItemPosition() == 0){
                Toast.makeText(getActivity(), getString(R.string.et_empty_field_error), Toast.LENGTH_SHORT).show();
            }
            if (binding.fragmentRegisterClientEtPassword.getText().toString().isEmpty()){
                binding.fragmentRegisterClientEtPassword.setError(getString(R.string.register_client_et_password_error));
            }

            if (binding.fragmentRegisterClientEtConfirmPassword.getText().toString().isEmpty()){
                binding.fragmentRegisterClientEtConfirmPassword.setError(getString(R.string.reset_password_et_confirm_password_error));
            }

        }else {
            onRegister();
        }
    }

    private void onRegister() {
        // Register User
        if (userType.equals(USER_CLIENT)) {
            viewModel.onClientRegister(getClient().onClientRegister(
                    binding.fragmentRegisterClientEtName.getText().toString().trim(),
                    binding.fragmentRegisterClientEtEmail.getText().toString().trim(),
                    binding.fragmentRegisterClientEtPassword.getText().toString().trim(),
                    binding.fragmentRegisterClientEtConfirmPassword.getText().toString().trim(),
                    binding.fragmentRegisterClientEtPhone.getText().toString().trim(),
                   1, //binding.fragmentRegisterClientSpDistrict.getSelectedItemPosition(),
                    imageFile
            ));
            setObserve();
        }
    }

    private void setObserve() {
        if (getActivity()!=null){
            viewModel.clientRegisterMutableLiveData.observe(getActivity(), new Observer<ClientRegister>() {
                @Override
                public void onChanged(ClientRegister clientRegister) {
                    try{
                        if(clientRegister.getStatus() == 1){

                            Toast.makeText(getActivity(), "Registered", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getActivity(), "onResponse status-0: \n" + clientRegister.getMsg() , Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(getActivity(), "Exeption: \n" + e.getMessage() , Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    // Error : the profile image must be an image
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CLIENT_GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            imageData = data.getData();
            imageFile = new File(String.valueOf(imageData));
            binding.fragmentRegisterClientCImgVProfile.setImageURI(imageData);
            binding.fragmentRegisterClientCImgVProfile.setBorderWidth(0);
            binding.fragmentRegisterClientCImgVProfile.setBorderColor(Color.WHITE);
            imageFlag = true;
        }
    }

    @Override
    public void onBack() {
        if(getActivity()!=null){
            HelperMethod.replaceFragment(getActivity().getSupportFragmentManager(), R.id.activity_user_cycle_fl_frame, new LoginFragment());
        }
    }
}