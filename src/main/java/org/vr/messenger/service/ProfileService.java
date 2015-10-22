package org.vr.messenger.service;


import org.vr.messenger.database.DatabaseMock;
import org.vr.messenger.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {
    private static Map<String, Profile> profiles = DatabaseMock.getProfiles();

    public ProfileService() {
        profiles.put("user", new Profile(1l, "user", "USER"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<>(profiles.values());
    }

    public Profile getProfile(String name) {
        return profiles.get(name);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile(String name) {
        return profiles.remove(name);
    }
}
