package licence.meme.worrynot.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class User {
    private String username;
    private String email;
    private String image;
    private int experience;
    private int level;
//    private List<Method> methods = new ArrayList<>();//in ordinea in care le bag
//    private List<Method> methods = new ArrayList<>();//in ordinea in care le bag
    private HashMap<String,Method> methods = new HashMap<>();
    private Method activeMethod;
    public static User getFirebaseUserInstance(String uid){
        final User user[] = {null};
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user[0] = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                user[0] = null;
            }
        });
        return user[0];
    }
    public User(String username, String email, int experience, String image) {
        this.username = username;
        this.email = email;
        this.image = image;
        this.experience = experience;
        this.level = Math.abs((int)experience/100);
    }
    public  User(){

    }

//    public void addMethod(Method method){
//        methods.add(method);
//    }
//    public void removeMethod(Method method){
//        methods.remove(method);
//    }
    public void addMethod(String key,Method method){
        methods.put(key,method);
    }
//    public void addWorryNot(Method worryNot){
//        methods.add(worryNot);
//    }
    public void removeMethod(String key){
        methods.remove(key);
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

//    public List<Method> getMethods() {
//        return methods;
//    }
//
//    public void setMethods(List<Method> methods) {
//        this.methods = methods;
//    }

    public HashMap<String, Method> getMethods() {
        return methods;
    }

    public void setMethods(HashMap<String, Method> methods) {
        this.methods = methods;
    }

    public Method getActiveMethod() {
        return activeMethod;
    }

    public void setActiveMethod(Method activeMethod) {
        this.activeMethod = activeMethod;
    }


    public void updateExperience(List<Double> ratings){
        double experience = 0.0;
        for(double rating:ratings){
            experience+= rating;
        }
        this.experience+=experience;
        this.level =new Integer(Math.abs((this.experience)/100)) ;
    }


}
