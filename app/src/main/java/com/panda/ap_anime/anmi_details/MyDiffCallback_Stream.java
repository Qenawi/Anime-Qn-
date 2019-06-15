package com.panda.ap_anime.anmi_details;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import com.panda.ap_anime.anmi_details.details_repo.StreamObject;
import com.panda.ap_anime.anmi_details.details_repo.StreamObject;
import timber.log.Timber;

import java.util.ArrayList;

class MyDiffCallback_Stream extends DiffUtil.Callback
{

    /*
    DiffUtil is a utility class that can calculate the difference between two
     lists and output a list of update operations that converts the first list into the second one.
    It can be used to calculate updates for a RecyclerView Adapter.
     */
    ArrayList<StreamObject> old;
    ArrayList<StreamObject> neww;

    public MyDiffCallback_Stream(ArrayList<StreamObject> newPersons, ArrayList<StreamObject> oldPersons)
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
        return old.get(oldItemPosition).getStreamname().equals( neww.get(newItemPosition).getStreamname());
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
