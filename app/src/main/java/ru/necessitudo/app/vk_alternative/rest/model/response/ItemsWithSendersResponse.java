package ru.necessitudo.app.vk_alternative.rest.model.response;

import java.util.ArrayList;
import java.util.List;

import ru.necessitudo.app.vk_alternative.model.Group;
import ru.necessitudo.app.vk_alternative.model.Owner;
import ru.necessitudo.app.vk_alternative.model.Profile;

/**
 * Created by olegdubrovin on 18/12/17.
 */

public class ItemsWithSendersResponse<T> extends  BaseItemResponse<T> {

    private List<Profile> profiles = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();

    private List<Profile> getProfiles() {
        return profiles;
    }

    private List<Group> getGroups() {
        return groups;
    }



    private List<Owner> getAllSenders(){

        List<Owner> all = new ArrayList<>();
        all.addAll(getProfiles());
        all.addAll(getGroups());

        return all;

    }

    public Owner getSender(int id){
        for (Owner owner : getAllSenders()){

            if (owner.getId()==Math.abs(id)){
                return owner;

            }
        }
        return null;
    }


}
