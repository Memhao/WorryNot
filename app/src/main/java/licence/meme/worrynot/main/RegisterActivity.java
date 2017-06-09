package licence.meme.worrynot.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;
import licence.meme.worrynot.R;
import licence.meme.worrynot.models.FirebaseService;
import licence.meme.worrynot.util.Utils;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CAMERA_REQUEST_CODE = 1;
    private Button mRegisterButton;
    private Button mBackButton;

    private CircleImageView mProfileCircleImageView;
    private EditText mUserNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mRepeatPasswordEditText;

    private FirebaseAuth mAuth;

    private  Bitmap mProfileImage;
    private StorageReference mStorage;
//    private ProgressDialog mProgress;

    private FirebaseService mFirebaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseService = FirebaseService.getInstance();
        mStorage = FirebaseStorage.getInstance().getReference();
        mRegisterButton = (Button)findViewById(R.id.register_register_activity_btn);
        mBackButton = (Button)findViewById(R.id.back_register_activity_btn);
        mProfileCircleImageView = (CircleImageView)findViewById(R.id.profile_picture_register_activity_civ);
        mEmailEditText = (EditText)findViewById(R.id.email_register_activity_et);
        mUserNameEditText = (EditText)findViewById(R.id.username_register_activity_et);
        mPasswordEditText = (EditText)findViewById(R.id.password_register_activity_et);
        mRepeatPasswordEditText = (EditText)findViewById(R.id.repeat_password_register_activity_et);
        mProfileImage = BitmapFactory.decodeResource(getResources(),R.drawable.kakashi_hatake);
//        mProgress = new ProgressDialog(this);

        mRegisterButton.setOnClickListener(this);
        mBackButton.setOnClickListener(this);
        mProfileCircleImageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_register_activity_btn:

                final String email =  mEmailEditText.getText().toString();
                final String password = mPasswordEditText.getText().toString();
                final String repeatpassword = mRepeatPasswordEditText.getText().toString();
                final String username = mUserNameEditText.getText().toString();
                final String image = Utils.encodeBitmap(mProfileImage);
                final Activity activity = RegisterActivity.this;
                if(password.equals(repeatpassword)
                        && !email.equals("")
                        && !username.equals("")
                        ){
                    mFirebaseService.signUp(RegisterActivity.this,email,password,username,image);
                }
                else{
                    Toast.makeText(this,"Error: Smt went wrong",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back_register_activity_btn:
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                finish();
                break;
            case R.id.profile_picture_register_activity_civ:
                 Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 startActivityForResult(imageCaptureIntent, CAMERA_REQUEST_CODE);
                break;
            default:break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
            mProfileImage = (Bitmap)data.getExtras().get("data");
            mProfileCircleImageView.setImageBitmap(mProfileImage);
        }else
            Toast.makeText(RegisterActivity.this,"Failt to load picture",Toast.LENGTH_LONG).show();
    }

    private void saveToDatabase(){
//        mProgress.setMessage("Register in progress...");
//        Uri uri = data.getData();
//        StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment()); // random name if u don't like to change the images
//        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                mProgress.dismiss();
//                Toast.makeText(RegisterActivity.this,"Register succeed...",Toast.LENGTH_LONG);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });
    }
}
