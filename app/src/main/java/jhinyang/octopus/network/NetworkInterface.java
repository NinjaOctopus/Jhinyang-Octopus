/**
 *
 *  NetworkInterface.java
 *
 *  Created by Ashutosh Sharma on 08/15/18.
 *  Copyright © 2018 Jhinyang Food Ltd.  All rights reserved.
 *
 */

package jhinyang.octopus.network;

import java.util.List;

import io.reactivex.Observable;
import jhinyang.octopus.data.CookbookDTO;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkInterface {

   // Get Cookbook Recipe
   @GET("cookbook") Observable<List<CookbookDTO>> getRecipes();

   // TODO Delete and edit
   // Post New Recipe

   @POST("cookbook")
   Observable<List<CookbookDTO>> submitRecipe(@Body CookbookDTO cookbookDTO);

   // Edit Recipe
}
