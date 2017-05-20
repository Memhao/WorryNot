package licence.meme.worrynot.models;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by xander on 08.04.2017.
 */

public class User {
    private String username;
    private String email;
    private String experience;
    private String level;
    private Set<Method> methods = new LinkedHashSet<Method>();//in ordinea in care le bag


    public User(String username, String email, String experience, String level) {
        this.username = username;
        this.email = email;
        this.experience = experience;
        this.level = level;
    }


    public void addMethod(Method method){
         methods.add(method);
    }
    public void removeMethod(Method method){
        methods.remove(method);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public Set<Method> getMethods() {
        return methods;
    }

    public void setMethods(Set<Method> methods) {
        this.methods = methods;
    }

}
