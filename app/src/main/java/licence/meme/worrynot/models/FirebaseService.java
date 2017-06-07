package licence.meme.worrynot.models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;
import licence.meme.worrynot.R;
import licence.meme.worrynot.activities.MainActivity;
import licence.meme.worrynot.activities.MethodDetailsActivity;
import licence.meme.worrynot.activities.ProfileActivity;
import licence.meme.worrynot.activities.ResultPopUpActivity;
import licence.meme.worrynot.adapter.RecycleViewCommentAdapter;
import licence.meme.worrynot.adapter.RecycleViewItemAdapter;
import licence.meme.worrynot.adapter.RecycleViewUserMethodAdapter;
import licence.meme.worrynot.licence.meme.worrynot.Levels;
import licence.meme.worrynot.util.Utils;

public class FirebaseService {
    private  FirebaseAuth mFirebaseAuth;
    private  FirebaseDatabase mFirebaseDatabase;
    private  FirebaseUser mFirebaseUser;
    private  DatabaseReference mDatabaseReference;
    private static final String TAG = FirebaseService.class.getSimpleName();
    public static boolean downloadAvailable = true;
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

                            mDatabaseReference.child("methods").child("-dfltmtd02").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Log.d(this.getClass().getSimpleName(), "createUserWithEmail:success");
                                    User user = new User(username,email,0,image);
                                    Method method = dataSnapshot.getValue(Method.class);
                                    user.setActiveMethod(method);
                                    user.addMethod(method);
                                    addUserToDatabase(user);
                                    context.startActivity(new Intent(context, ProfileActivity.class));
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


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

    public void changePassword(final Activity context, String email, String oldPassword, final String newPassword){
        AuthCredential credential = EmailAuthProvider.getCredential(email,oldPassword);
        mFirebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mFirebaseUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context,"Password successfully changed ",Toast.LENGTH_LONG);
                            } else {
                                Toast.makeText(context,"Password does not changed Please try again :D ",Toast.LENGTH_LONG);
                            }
                        }
                    });
                } else {
                    Toast.makeText(context,"Please try again :D ",Toast.LENGTH_LONG);
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


    public void writeComment(final Activity activity,final String methodKey,final String commentLine){
        String uid = mFirebaseUser.getUid();
        DatabaseReference commentsDatabaseReference = FirebaseDatabase.getInstance().getReference().child("methods").child(methodKey).child("metadata").child("comments");

        Comment comment = new Comment();
        comment.setId(uid);
        comment.setMessage(commentLine);
        commentsDatabaseReference.child(uid).setValue(comment);
        Toast.makeText(activity,"Thanks for your time !",Toast.LENGTH_SHORT).show();

    }
    public void existsMethod(final Activity context,final String key,final String name,final String author){
        FirebaseService.downloadAvailable = true;
        String uid = mFirebaseUser.getUid();
        final DatabaseReference destMethodsUserPath = mDatabaseReference.child("users").child(uid).child("methods");
        destMethodsUserPath.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children  = dataSnapshot.getChildren();
                List<Method> methods = new ArrayList<Method>();
                for(DataSnapshot child:children) {
                    Method cMethod = child.getValue(Method.class);
                    methods.add(cMethod);
                }
                boolean downloadOk = true;
                for(Method m: methods){
                    if(m.getMetadata().getName().equals(name) && m.getMetadata().getAuthor().equals(author)){
                        downloadAvailable = false;
                        downloadOk = false;
                        Toast.makeText(context,"Your already posses this method, please check your WorryNot list ",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if(downloadOk){
                    downloadMethodFromStore(context,key);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void downloadMethodFromStore(final Activity context, final String key){

        String uid = mFirebaseUser.getUid();
        DatabaseReference sourceMethodsPath = mDatabaseReference.child("methods").child(key);
        final DatabaseReference destMethodsUserPath = mDatabaseReference.child("users").child(uid).child("methods").push();

        sourceMethodsPath.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    destMethodsUserPath.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if(databaseError != null){
                                Toast.makeText(context,"Download fail, please try again",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context,"Download succeed, please check your WorryNot list ",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

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
        DatabaseReference usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("methods");
        usersDatabaseReference.push().setValue(method);
        Toast.makeText(context,"Worry Not successfully added to your bucket",Toast.LENGTH_SHORT).show();
    }

    public void setActiveMethod(final Activity context, final Method method){
        String uid = mFirebaseUser.getUid();
        DatabaseReference usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("activeMethod");
        usersDatabaseReference.setValue(method);
        Toast.makeText(context,"Method has been activated",Toast.LENGTH_SHORT).show();
    }
    public void getActiveMethod(final Context context, final LayoutInflater inflater, final View parentView){
        String uid = mFirebaseUser.getUid();
        DatabaseReference ref = mFirebaseDatabase.getReference("users").child(uid).child("activeMethod");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Method method = dataSnapshot.getValue(Method.class);
                MethodRender render = new MethodRender(method);
                Log.e("[getActiveMethod]","_______ACTIVE METHOD________"+method.getMetadata().getName()+"_____________");

                render.drawMethod(context,inflater,parentView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,"Fail database");
            }
        });
    }


    public void populateQuestionnaire(ListView listView,Context context, Score score){
        String uid = mFirebaseUser.getUid();
        final QuestionnaireActiveMethodValueEventListener questionnaireActiveMethodValueEventListener = new QuestionnaireActiveMethodValueEventListener( listView, context,score);
        mDatabaseReference.child("users").child(uid).child("activeMethod").addValueEventListener(questionnaireActiveMethodValueEventListener);
    }


    public void updateMethod(){

    }

    public void rateMethod(int score,String methodKey){

    }

    /**
     * This method is used to populate and update in real time listView given as parameter within the context with WorryNots from "methods" section from database
     *
     * @param listView
     * @param context
     * @return List with all methods created by all users
     */
    public void populateMethodsContainer(ListView listView,Context context){
        final MethodsValueEventListener methodsValueEventListener = new MethodsValueEventListener( listView, context);
        mDatabaseReference.child("methods").addValueEventListener(methodsValueEventListener);
    }

    public void populateUserMethodsRecyclerView(RecyclerView recyclerView, LayoutInflater layoutInflater,Activity activity,TextView textView){
        String uid = mFirebaseUser.getUid();
        final UserMethodsValueEventListener userMethodsValueEventListener = new UserMethodsValueEventListener( recyclerView,layoutInflater,activity,textView);
        mDatabaseReference.child("users").child(uid).child("methods").addValueEventListener(userMethodsValueEventListener);
    }


    public void populateMethodsStoreRecycleView(RecyclerView recyclerView, LayoutInflater inflater,Activity activity){
        MethodsStoreEventListener methodsStoreEventListener = new MethodsStoreEventListener(recyclerView,inflater,activity);
        mDatabaseReference.child("methods").addValueEventListener(methodsStoreEventListener);
    }


    public void populateMethodsCommentsRecycleView(RecyclerView recyclerView, LayoutInflater layoutInflater,Activity activity,String key){
        Log.e(TAG,"Comment:--------"+"[populateMethodsCommentsRecycleView]"+key);
        MethodCommentsEventListener metohdCommentsEventListener = new MethodCommentsEventListener(recyclerView,layoutInflater,activity);
        mDatabaseReference.child("methods").child(key).child("metadata").child("comments").addValueEventListener(metohdCommentsEventListener);
    }
    /**
     * This method gives feedback to user depending on score obtained for questionnaire
     * @param score
     * @param activity
     */
    public void popUpAdvice(int score, final Activity activity) {
        Log.e("FireBaseService","----SCORE:"+score);
        String uid = mFirebaseUser.getUid();
        DatabaseReference ref = mFirebaseDatabase.getReference("users").child(uid).child("activeMethod");
        if(score<3){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Method method = dataSnapshot.getValue(Method.class);
                    String result = method.getInfo().getResults().getLow();
                    Log.e("FireBaseService","----RESULT FROM FIREBASE <3 :"+ result );
                    Intent toResultPopUpActivityIntent = new Intent(activity, ResultPopUpActivity.class);
                    final String TAG_TRANSFER = "RESULT";
                    toResultPopUpActivityIntent.putExtra(TAG_TRANSFER,result);
                    activity.startActivity(toResultPopUpActivityIntent);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //bring in results low from database into a pop up window
        }else if(score>3){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Method method = dataSnapshot.getValue(Method.class);
                    String result = method.getInfo().getResults().getHigh();
                    Log.e("FireBaseService","----RESULT FROM FIREBASE >3 :"+ result);
                    Intent toResultPopUpActivityIntent = new Intent(activity, ResultPopUpActivity.class);
                    final String TAG_TRANSFER = "RESULT";
                    toResultPopUpActivityIntent.putExtra(TAG_TRANSFER,result);
                    activity.startActivity(toResultPopUpActivityIntent);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //bring in results high from database
        }else{
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Method method = dataSnapshot.getValue(Method.class);
                    String result = method.getInfo().getResults().getMedium();
                    Log.e("FireBaseService","----RESULT FROM FIREBASE =3 :"+ result);
                    Intent toResultPopUpActivityIntent = new Intent(activity, ResultPopUpActivity.class);
                    final String TAG_TRANSFER = "RESULT";
                    toResultPopUpActivityIntent.putExtra(TAG_TRANSFER,result);
                    activity.startActivity(toResultPopUpActivityIntent);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //bring in results medium from database
        }

    }


    private  class MethodCommentsEventListener implements ValueEventListener{


        private RecyclerView mRecyclerView;
        private LayoutInflater mInflater;
        private List<RecycleViewComment> mComments;
        private RecycleViewCommentAdapter mAdapter;
        private Activity mActivity;

        public MethodCommentsEventListener(RecyclerView recyclerView, LayoutInflater layoutInflater,Activity activity){
            this.mRecyclerView = recyclerView;
            this.mInflater = layoutInflater;
            this.mActivity = activity;
            mComments = new ArrayList<>();
        }
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.e(TAG,"Comment:--------"+"[MethodCommentsEventListener]:[onDataChange]"+dataSnapshot.getChildrenCount());

            Iterable<DataSnapshot> children  = dataSnapshot.getChildren();
            for(DataSnapshot child:children){
                Comment comment = child.getValue(Comment.class);
                String key = child.getKey();
                mComments.add(new RecycleViewComment(key,comment));
            }
            mAdapter = new RecycleViewCommentAdapter(mComments,mInflater);
            mRecyclerView.setAdapter(mAdapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }


    private class UserMethodsValueEventListener implements ValueEventListener, RecycleViewUserMethodAdapter.ItemClickCallback{

        private RecyclerView mRecyclerView;
        private LayoutInflater mInflater;
        private List<Method> mMethods;
        private RecycleViewUserMethodAdapter mAdapter;
        private Activity mActivity;
        private TextView mTextView;

        public UserMethodsValueEventListener(RecyclerView recyclerView, LayoutInflater inflater,Activity activity,TextView textView){
            this.mRecyclerView = recyclerView;
            this.mInflater = inflater;
            this.mActivity = activity;
            mTextView = textView;
            mMethods = new ArrayList<>();
        }
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Iterable<DataSnapshot> children  = dataSnapshot.getChildren();
            for(DataSnapshot child:children){
                Method method = child.getValue(Method.class);
                String key = child.getKey();
                mMethods.add(method);
                Log.e(TAG,"Methods lendth is :" + "Method 1 name is: "+ method.getMetadata().getAuthor());
            }
            mAdapter = new RecycleViewUserMethodAdapter(mMethods,mInflater);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setItemClickCallback(this);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }

        @Override
        public void onItemClick(int position) {
            Method item= mMethods.get(position);
            mTextView.setText(item.getMetadata().getName() +"has been selected.");
        }

        @Override
        public void onActiveSelected(int position) {
            setActiveMethod(mActivity,mMethods.get(position));
        }


    }


        private class MethodsStoreEventListener implements ValueEventListener, RecycleViewItemAdapter.ItemClickCallback{
        private static final String METHOD_TITLE = "METHOD_TITLE";
        private static final String METHOD_AUTHOR = "METHOD_AUTHOR";
        private static final String METHOD_RATING = "METHOD_RATING";
        private static final String METHOD_DESCRIPTION = "METHOD_DESCRIPTION";
        private static final String KEY = "KEY";
        private static final String BUNDLE = "BUNDLE";

        private RecyclerView mRecyclerView;
        private LayoutInflater mInflater;
        private List<RecycleViewItem> mMethods;
        private RecycleViewItemAdapter mAdapter;
        private Activity mActivity;
        public MethodsStoreEventListener(RecyclerView recyclerView, LayoutInflater inflater,Activity activity) {
            this.mRecyclerView = recyclerView;
            this.mInflater = inflater;
            this.mActivity = activity;
            mMethods = new ArrayList<>();

        }

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Iterable<DataSnapshot> children  = dataSnapshot.getChildren();
            for(DataSnapshot child:children){
                Method method = child.getValue(Method.class);
                String key = child.getKey();
                mMethods.add(new RecycleViewItem(method,key));
                Log.e(TAG,"Methods lendth is :" + "Method 1 name is: "+ method.getMetadata().getAuthor());
            }
            mAdapter = new RecycleViewItemAdapter(mMethods,mInflater);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.setItemClickCallback(this);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }

        @Override
        public void onItemClick(int position) {
            RecycleViewItem item= mMethods.get(position);
            Intent i = new Intent(mActivity, MethodDetailsActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString(METHOD_TITLE,item.getItemName());
            bundle.putString(METHOD_AUTHOR,item.getItemAuthor());
            bundle.putInt(METHOD_RATING,item.getItemRating());
            bundle.putString(METHOD_DESCRIPTION,item.getItemDescription());
            bundle.putString(KEY,item.getKey());
            i.putExtra(BUNDLE,bundle);
            mActivity.startActivity(i);
        }

        @Override
        public void onFavouriteIconClick(int position) {

        }
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


    private class QuestionnaireActiveMethodValueEventListener implements ValueEventListener{
        private ListView mListView;
        private Context mContext;
        private Score score;
        public QuestionnaireActiveMethodValueEventListener(ListView mListView, Context mContext,Score score) {
            this.mListView = mListView;
            this.mContext = mContext;
            this.score = score;
        }

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Method method = dataSnapshot.getValue(Method.class);
            List<String> questionnaire = method.getInfo().getQuestionnaire();
            QuestionAdapter questionAdapter = new QuestionAdapter(mContext,questionnaire,score);
            mListView.setAdapter(questionAdapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }

    private boolean validatePassword(String password) {
        if(password == null || password.length()<6)
            return false;
        return  true;
    }

}