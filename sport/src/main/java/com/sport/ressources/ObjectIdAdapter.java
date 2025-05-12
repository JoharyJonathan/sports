package com.sport.ressources;

import org.bson.types.ObjectId;

import jakarta.json.bind.adapter.JsonbAdapter;

public class ObjectIdAdapter implements JsonbAdapter<ObjectId, String> {

    @Override
    public String adaptToJson(ObjectId obj) {
        return obj.toHexString();
    }

    @Override
    public ObjectId adaptFromJson(String obj) {
        return new ObjectId(obj);
    }
}
