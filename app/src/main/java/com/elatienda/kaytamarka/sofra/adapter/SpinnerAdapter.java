package com.elatienda.kaytamarka.sofra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.databinding.DataBindingUtil;
import com.elatienda.kaytamarka.sofra.R;
import com.elatienda.kaytamarka.sofra.data.model.general_response.GeneralResponseData;
import com.elatienda.kaytamarka.sofra.databinding.ItemCustomSpinnerBinding;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    // extends BaseAdapter
    private Context context;
    public List<GeneralResponseData> generalResponseNotPaginatedData = new ArrayList<>();
    //private LayoutInflater inflater;
    public int selectedId = 0;
    int textColor = 0;

    private ItemCustomSpinnerBinding binding;

    public SpinnerAdapter(Context applicationContext, int textColor){
        this.context = applicationContext;
        this.textColor = textColor;
        //inflater = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<GeneralResponseData> generalResponseDataList, String hint) {
        generalResponseDataList.add(0, new GeneralResponseData(0,hint));
        this.generalResponseNotPaginatedData = new ArrayList<>();
        this.generalResponseNotPaginatedData = generalResponseDataList;
    }

    @Override
    public int getCount() {
        return generalResponseNotPaginatedData.size();
    }

    @Override
    public Object getItem(int position) {
        return generalResponseNotPaginatedData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_custom_spinner, parent ,false);
        }

        binding.itemCustomSpinnerTvText.setText(generalResponseNotPaginatedData.get(position).getName());
        // if spinner in user cycle ==> White
        // if spinner in home cycle ==> fragment_forgot_password_et_hint
        if(textColor!=0){
            binding.itemCustomSpinnerTvText.setTextColor(textColor);
        }
        selectedId = generalResponseNotPaginatedData.get(position).getId();

        return convertView;
    }
}
