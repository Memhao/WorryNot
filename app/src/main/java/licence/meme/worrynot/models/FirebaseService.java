package licence.meme.worrynot.models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import licence.meme.worrynot.R;
import licence.meme.worrynot.activities.MainActivity;
import licence.meme.worrynot.activities.ProfileActivity;
import licence.meme.worrynot.licence.meme.worrynot.Levels;
import licence.meme.worrynot.util.Utils;

public class FirebaseService {
    private  FirebaseAuth mFirebaseAuth;
    private  FirebaseDatabase mFirebaseDatabase;
    private  FirebaseUser mFirebaseUser;
    private  DatabaseReference mDatabaseReference;
    private static final String TAG = FirebaseService.class.getSimpleName();
    private FirebaseService(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
    }
    private static FirebaseService instance;

    public static FirebaseService getInstance(){
        if(instance == null){
            instance = new FirebaseService();
        }
        return  instance;
    }

    /**
     *  This method is used for sign up a new user If sign up succeed Profile Activity is launched
     *
     * @param context
     * @param email
     * @param password
     * @param username
     * @param image
     */
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
    private void addUserToDatabase(User user) {

        String uid = mFirebaseAuth.getCurrentUser().getUid(); // for security reason maybe you should use push instead of getUid
        DatabaseReference usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        usersDatabaseReference.setValue(user);

        DatabaseReference levelsDatabaseReference = FirebaseDatabase.getInstance().getReference().child("top").child(Levels.level_0).child("memebers");
        levelsDatabaseReference.push().setValue(true);

    }


    /**
     * This method is used for login a user that already has an account If succeed ProfileActivity is launched
     *
     * @param context
     * @param email
     * @param password
     */
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

    /**
     * Logout current user and turn back to MainActivity if succeed
     *
     * @param fragment
     */
    public void logout(final Fragment fragment){
        mFirebaseAuth.signOut();
        if(mFirebaseAuth == null) {
            fragment.startActivity(new Intent(fragment.getContext(), MainActivity.class));
            fragment.getActivity().finish();
        }
    }

    /**
     * This method is used to change password
     *
     * @param context  ChangePasswordActivity
     * @param newPassword
     */
    public void changePassword(final Activity context , String newPassword){
        mFirebaseUser.updatePassword(newPassword).addOnCompleteListener(context, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context,"Password successfully changed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * This method is used to reset password
     *
     * @param context ResetPasswordActivity
     * @param email to send your request for reset the password
     */
    public void resetPassword(final Activity context, String email ){
        mFirebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(context, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context,"Email sent. Please check your inbox ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * This method is used to update  basic info for current user
     *
     * @param avatarImageView
     * @param userNameTextView
     * @param progressBar
     */
    public void updateUserHomeInfo(final CircleImageView avatarImageView, final TextView userNameTextView, final ProgressBar progressBar){
        String uid = mFirebaseUser.getUid();
        DatabaseReference ref = mFirebaseDatabase.getReference("users/"+uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                avatarImageView.setImageBitmap(Utils.decodeFromFirebaseBase64(user.getImage()));
                userNameTextView.setText(user.getUsername());
                progressBar.setProgress(user.getExperience());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,"Fail database");
            }
        });
    }

    /**
     * Use for download a certain method given as parameter
     * @param context is used for feedback
     * @param method
     */
    public void downloadMethod(final Activity context,final Method method){
        mDatabaseReference.child("methods").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children  = dataSnapshot.getChildren();
                for(DataSnapshot child:children){
                    Method cMethod = child.getValue(Method.class);
                    if(cMethod.equals(method)){
                        String uid = mFirebaseUser.getUid();
                        DatabaseReference usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("user_methods");
                        usersDatabaseReference.push().setValue(method);
                        Toast.makeText(context,"Download succeed, please check your WorryNot list ",Toast.LENGTH_SHORT).show();
                        break;
                    }else{

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context,"Download fail, please try again",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This method is used for upload a method created by user
     * @param context
     * @param method
     */
    public  void  uploadMethod(final Activity context, final Method method){
        mDatabaseReference.child("methods").push().setValue(method);
        Toast.makeText(context,"Worry Not successfully added to the world ",Toast.LENGTH_SHORT).show();

    }

    /**
     * This method is use to create a worry not and add to the current user list only
     * @param context
     * @param method
     */
    public void  createMethod(final Activity context, final Method method){
        String uid = mFirebaseUser.getUid();
        DatabaseReference usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("user_methods");
        usersDatabaseReference.push().setValue(method);
        Toast.makeText(context,"Worry Not successfully added to your bucket",Toast.LENGTH_SHORT).show();
    }

    public void setMethodAvailable(final Activity context, final Method method){

    }
    public void getMethodAvailable(final Activity context, final Method method){

    }
    /**
     * This method is used to populate and update in real time listView given as parameter within the context with WorryNots from "methods" section from database
     *
     * @param listView
     * @param context
     * @return List with all methods created by all users
     */
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