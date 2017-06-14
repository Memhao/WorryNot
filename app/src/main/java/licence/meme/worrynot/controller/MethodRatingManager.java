package licence.meme.worrynot.controller;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by xander on 06.06.2017.
 */

public class MethodRatingManager {
    private int methodRating;
    private String methodKey;

    public MethodRatingManager(String methodKey){
        this.methodKey = methodKey;
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("methods").child(methodKey);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                methodRating = dataSnapshot.child("metadata").child("rating").getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void addRating(int quoata){
        switch (quoata){
            case 1:methodRating-=2;;break;
            case 2:methodRating-=1;break;
            case 3:methodRating+=0;break;
            case 4:methodRating+=1;break;
            case 5:methodRating+=2;break;
            default:break;
        }
    }
    public void persistRating(){
        FirebaseDatabase.getInstance().getReference("methods").child(methodKey).child("metadata").child("rating").setValue(methodRating);
    }
}
