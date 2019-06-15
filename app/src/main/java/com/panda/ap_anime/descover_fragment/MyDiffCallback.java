package com.panda.ap_anime.descover_fragment;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import com.panda.ap_anime.descover_fragment.data_repo.Result;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.List;

class MyDiffCallback extends DiffUtil.Callback
{

    /*
    DiffUtil is a utility class that can calculate the difference between two
     lists and output a list of update operations that converts the first list into the second one.
    It can be used to calculate updates for a RecyclerView Adapter.
     */
    ArrayList<Result> old;
    ArrayList<Result> neww;

    public MyDiffCallback(ArrayList<Result> newPersons, ArrayList<Result> oldPersons)
    {
        this.neww = newPersons;
        this.old = oldPersons;
    }

    @Override
    public int getOldListSize()
    {
        Timber.tag("DiffUtil").v("%s", old.size());
        Log.v("a",old.size()+" old");

        return old.size();
    }

    @Override
    public int getNewListSize() {

        Log.v("a",neww.size()+" old");


        return neww.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition)
    {
        return old.get(oldItemPosition).getId() == neww.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
