package com.asimio.demo.fixtures;

import java.util.Date;

import com.asimio.demo.domain.Actor;

public class ActorFixtures {

    public static Actor createActor(int id, String firstName, String lastName, Date lastUpdated) {
        Actor result = new Actor();
        result.setActorId(id);
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setLastUpdate(lastUpdated);
        return result;
    }
}