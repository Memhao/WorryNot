package licence.meme.worrynot.models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import licence.meme.worrynot.R;
import licence.meme.worrynot.activities.ProfileActivity;
import licence.meme.worrynot.licence.meme.worrynot.Levels;

public class FirebaseService {
    private  FirebaseAuth mFirebaseAuth;
    private  FirebaseDatabase mFirebaseDatabase;
    private  DatabaseReference mDatabaseReference;
    private static final String TAG = FirebaseService.class.getSimpleName();
    private FirebaseService(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }
    private static FirebaseService instance;

    public static FirebaseService getInstance(){
        if(instance == null){
            instance = new FirebaseService();
        }
        return  instance;
    }



    public  void signUp(final Activity context,final String email, final String password, final String username, final String image){
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
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

        String uid = mFirebaseAuth.getCurrentUser().getUid(); // for security reason maybe you should use push instead of getUid
        DatabaseReference usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        usersDatabaseReference.setValue(user);

        DatabaseReference levelsDatabaseReference = FirebaseDatabase.getInstance().getReference().child("top").child(Levels.level_0).child("memebers");
        levelsDatabaseReference.push().setValue(true);

    }

    public void loginUser(final Activity context,String email, final String password) {
        mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(!validatePassword(password)){
                        Toast.makeText(context,"Password invalid, Please try again !",Toast.LENGTH_SHORT).show();
                    }else{
                        context.startActivity(new Intent(context,ProfileActivity.class));
                        context.finish();
                    }
                }else{

                }
            }
        });
    }
    public List<Method> populateMethodsContainer(ListView listView,Context context){
        List<Method> methods = new ArrayList<Method>();
        final MethodsValueEventListener methodsValueEventListener = new MethodsValueEventListener( listView, context);
        mDatabaseReference.child("methods").addValueEventListener(methodsValueEventListener);
        Log.e(TAG,"------" + "SIZE IS: "+ methods.size());

        return  methods;
    }
    private class MethodsValueEventListener implements ValueEventListener{
        private List<Method> mMethods;
        private ListView mListView;
        private Context mContext;
        public MethodsValueEventListener(ListView listView,Context context){
            mMethods = new ArrayList<>();
            mListView = listView;
            mContext = context;
        }
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Iterable<DataSnapshot> children  = dataSnapshot.getChildren();
            for(DataSnapshot child:children){
                Method method = child.getValue(Method.class);
                mMethods.add(method);
                Log.e(TAG,"Methods lendth is :" + "Method 1 name is: "+ method.getMetadata().getAuthor());
            }
            //Build Adapter
            MethodAdapter methodAdapter = new MethodAdapter(mContext, R.layout.row_method,mMethods);

            //Configure the list view
            mListView.setAdapter(methodAdapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
        public  List<Method> getMethods(){
            return mMethods;
        }
        public int getMethodsSize(){
            return mMethods.size();
        }
    }
    private boolean validatePassword(String password) {
        if(password == null || password.length()<6)
            return false;
        return  true;
    }

}