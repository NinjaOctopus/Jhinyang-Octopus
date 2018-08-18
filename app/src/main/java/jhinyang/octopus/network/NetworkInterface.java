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
import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkInterface {

   // Get Cookbook Recipe
   @GET("cookbook") Observable<List<CookbookDTO>> getRecipes();

   // TODO Post, Delete and edit
   // Post New Recipe

   // Edit Recipe
}
