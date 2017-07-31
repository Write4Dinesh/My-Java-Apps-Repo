package com.example.helloandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dinesh.k.masthaiah on 7/25/2017.
 */

public class ProfileListAdapter extends BaseAdapter {
    private Context mContext;
    private List<OfferModel> mOfferModelList;


    public ProfileListAdapter(Context context, List<OfferModel> offerModelList) {
        mContext = context;
        mOfferModelList = offerModelList;
    }

    public void addItem(OfferModel model) {
        mOfferModelList.add(model);
    }

    public void removeItem(OfferModel model) {
        for (OfferModel model1 : mOfferModelList) {
            if (model1.profileId.equals(model.profileId)) {
                mOfferModelList.remove(model1);
            }
        }
    }

    public int getCount() {
        return mOfferModelList.size();
    }

    public Object getItem(int i) {
        return mOfferModelList.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_template, null);
        }
        OfferModel offerModel = mOfferModelList.get(i);
        TextView profileTv = (TextView) view.findViewById(R.id.profile_id_tv);
        profileTv.setText(offerModel.profileId);
        TextView offerIdTv = (TextView) view.findViewById(R.id.offer_id_tv);
        offerIdTv.setText(offerModel.offerId);
        return view;
    }
}
