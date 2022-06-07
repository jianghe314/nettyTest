package com.cidi.nettytest;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by CIDI zhengxuan on 2019/10/15
 * QQ:1309873105
 */
public class EventHolder extends RecyclerView.ViewHolder {

    private ImageView iv;
    private RelativeLayout relativeLayout;
    private TextView tv;

    public EventHolder(@NonNull View itemView) {
        super(itemView);
        iv=itemView.findViewById(R.id.item_iv);
        relativeLayout=itemView.findViewById(R.id.item_rela);
        tv=itemView.findViewById(R.id.item_tv);
    }

    public void setData(Event event){
        switch (event.getEventCode()){
            case "1":

                break;
            case "2":

                break;
            case "3":

                break;
            case "4":

                break;
            case "5":

                break;
                default:
                    return;
        }
    }
}
