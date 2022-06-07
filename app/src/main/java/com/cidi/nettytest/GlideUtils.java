package com.cidi.nettytest;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


/**
 * Created by CIDI zhengxuan on 2019/3/26
 * QQ:1309873105
 */
public class GlideUtils {

    private static RequestOptions requestOptions=new RequestOptions();
    public static void Load(int photoAddress, ImageView iv){
        Glide.with(MyApplication.getMyApplicationContext()).load(photoAddress).apply(requestOptions).into(iv);
    }
}
