package com.sam_chordas.android.stockhawk.ours;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class Constants {

//    public Constants(Context context) {
//
//        resources=context.getResources();
//        appPreferences = AppPreferences.getAppPreferences(context);
//    }

    //ROLE_ADMIN,,

    static ApiCalls apiCalls;

    public static final String baseUrl="https://query.yahooapis.com/v1/public/";

    public static String getQuery(String stockName){
        return "select * from yahoo.finance.historicaldata where symbol = \""+stockName+"\" and startDate = \"2009-09-11\" and endDate = \"2009-10-11\"";
    }



    public static ApiCalls getRetrofitInstance(){

        if(apiCalls!=null)
            return apiCalls;

        Gson gson = new GsonBuilder()
                //.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                // Register an adapter to manage the date types as long values
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                })
//                .registerTypeAdapter(VUser.class, new JsonDeserializer<VUser>() {
//                    public VUser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                        //return new Date(json.getAsJsonPrimitive().getAsLong());
//                        JsonObject object=json.getAsJsonObject();
//                        VUser user=new VUser();
//                        user.setId(object.get("id").getAsLong());
//                        user.setCompany(object.get("company").getAsString());
//                        user.setEmail(object.get("email").getAsString());
//                        user.setNumber(object.get("number").getAsString());
//                        user.setMaxFree(object.get("maxFree").getAsLong());
//                        user.setName(object.get("name").getAsString());
//                        user.setPassword(object.get("password").getAsString());
//                        user.setRole(object.get("role").getAsString());
//                        user.setSessionId(object.get("sessionId").getAsString());
//                        return user;
//                    }
//                })
                .serializeNulls()

                .create();


        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiCalls=retrofit.create(ApiCalls.class);
        return apiCalls;
    }
}
