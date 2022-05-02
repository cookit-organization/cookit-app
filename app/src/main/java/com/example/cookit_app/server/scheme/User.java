package com.example.cookit_app.server.scheme;

import java.util.List;

public class User {

    private String _id;
    private Profile profile;
    private Private_Info private_info;
    private Recipes recipes;

    public String get_id() {
        return _id;
    }

    public Profile getProfile() {
        return profile;
    }

    public Private_Info getPrivate_info() {
        return private_info;
    }

    public Recipes getRecipes() {
        return recipes;
    }

    public class Profile {

        private String name, image, bio;
        private double rate;

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }

        public String getBio() {
            return bio;
        }

        public Number getRate() {
            return rate;
        }
    }

    public class Private_Info {

        private String email, username, password;

        public String getEmail() {
            return email;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public class Recipes {

        private List<String> his, saved, favorite;

        public List<String> getHis() {
            return his;
        }

        public List<String> getSaved() {
            return saved;
        }

        public List<String> getFavorite() {
            return favorite;
        }
    }
}


