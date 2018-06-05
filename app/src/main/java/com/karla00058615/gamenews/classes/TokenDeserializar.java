package com.karla00058615.gamenews.classes;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.karla00058615.gamenews.classes.Token;

import java.lang.reflect.Type;

/**
 * Created by Karla on 4/6/2018.
 */

public class TokenDeserializar implements JsonDeserializer<Token> {


    @Override
    public Token deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Token token = new Token();

        JsonObject jsonObject = json.getAsJsonObject();
        token.setToken(jsonObject.get("token").getAsString());

        return token;
    }
}
