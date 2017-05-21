package licence.meme.worrynot.models;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import licence.meme.worrynot.activities.ProfileActivity;
import licence.meme.worrynot.licence.meme.worrynot.Levels;

public class SignupService {
    private FirebaseAuth mAuth;
    private static final String TAG = SignupService.class.getSimpleName();
    public SignupService (){
        mAuth = FirebaseAuth.getInstance();
    }




    public  void signUp(final Activity context,final String email, final String password, final String username, final String image){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(this.getClass().getSimpleName(), "createUserWithEmail:success");
                            User user = new User(username,email,0,image);
                            addUserToDatabase(user);
                            context.startActivity(new Intent(context, ProfileActivity.class));

                        } else {
                            Log.e(this.getClass().getSimpleName(), "Fail auth", task.getException());
                                        Toast.makeText(context, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void addUserToDatabase(User user) {

        String uid = mAuth.getCurrentUser().getUid(); // for security reason maybe you should use push instead of getUid
        DatabaseReference usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        usersDatabaseReference.setValue(user);

        DatabaseReference levelsDatabaseReference = FirebaseDatabase.getInstance().getReference().child("top").child(Levels.level_0).child("memebers");
        levelsDatabaseReference.push().setValue(true);

    }



}