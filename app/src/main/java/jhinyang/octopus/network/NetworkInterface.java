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
import io.reactivex.Single;
import jhinyang.octopus.data.CookbookDTO;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NetworkInterface {

   // Get Cookbook Recipe
   @GET("cookbook") Observable<List<CookbookDTO>> getRecipes();

   // Post New Recipe
   @POST("cookbook")
   Observable<List<CookbookDTO>> submitRecipe(@Body CookbookDTO cookbookDTO);

   // Delete Recipe
   @DELETE("cookbook/{id}")
   Observable<Single> deleteRecipe(@Path("id") String id);

   // Put Recipe
   @PUT("cookbook/{id}")
   Observable<Single> editRecipe(@Path("id") String id);
}
