package com.elatienda.kaytamarka.sofra.view.fragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.elatienda.kaytamarka.sofra.data.model.general_response.GeneralResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseViewModel extends ViewModel {

    public MutableLiveData<GeneralResponse> citiesMutableLiveData;
    public MutableLiveData<GeneralResponse> regionsMutableLiveData;
    public MutableLiveData<Boolean> mutableLiveDataError;
    public String ErrorText, onFailureMsg;

    public void getCities(Call<GeneralResponse> call){
        citiesMutableLiveData = new MutableLiveData<>();
        mutableLiveDataError = new MutableLiveData<>();

        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(@NonNull Call<GeneralResponse> call, @NonNull Response<GeneralResponse> response) {
                citiesMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<GeneralResponse> call, @NonNull Throwable t) {
                ErrorText = null;
                onFailureMsg = t.getMessage();
                mutableLiveDataError.setValue(true);
            }
        });
    }

    public void getRegions(Call<GeneralResponse> call){
        regionsMutableLiveData = new MutableLiveData<>();
        mutableLiveDataError = new MutableLiveData<>();

        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(@NonNull Call<GeneralResponse> call, @NonNull Response<GeneralResponse> response) {
                regionsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<GeneralResponse> call, @NonNull Throwable t) {
                ErrorText = null;
                onFailureMsg = t.getMessage();
                mutableLiveDataError.setValue(true);
            }
        });
    }


}
