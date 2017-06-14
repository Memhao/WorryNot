package licence.meme.worrynot.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by xander on 03.06.2017.
 */

public class ExperienceManager {
    private float experience;
    private int level;
    private String uid;
    public ExperienceManager(){
        uid =  FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                float currentExperience = dataSnapshot.child("experience").getValue(Float.class);
                int currentLevel = dataSnapshot.child("level").getValue(Integer.class);
                experience = currentLevel*100 + currentExperience;
                level = currentLevel;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void increaseExperience(float quota){
        experience+=Float.valueOf(quota);
        level = Math.abs((int)(experience/100));
    }
    public void persistExperience(){
        FirebaseDatabase.getInstance().getReference("users").child(uid).child("experience").setValue(experience%100);
        FirebaseDatabase.getInstance().getReference("users").child(uid).child("level").setValue(level);
    }
}
