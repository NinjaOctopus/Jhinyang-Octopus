/**
 *
 *  NetworkService.java
 *
 *  Created by Ashutosh Sharma on 08/15/18.
 *  Copyright © 2018 Jhinyang Food Ltd.  All rights reserved.
 *
 */

package jhinyang.octopus.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jhinyang.octopus.utils.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    static final String BASE_URL = Constant.base_url;

    public Retrofit start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
}
