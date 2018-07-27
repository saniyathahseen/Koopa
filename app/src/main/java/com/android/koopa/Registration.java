package com.android.koopa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class Registration extends BaseClass implements RadioGroup.OnCheckedChangeListener,View.OnClickListener, AdapterView.OnItemSelectedListener,View.OnFocusChangeListener {
    Spinner category, spinner_district, spinner_subdistrict, numberOfWorker;
    RadioButton userRadio, workerRadio, contractorRadio;
    RadioGroup radioGroup;
    RelativeLayout usrLayOut, workerLayout, contractorLayout;
    Button submitButton;
    EditText name, address, adhaar, username, password, renterPassworsd;
    ImageView imageView;
    private FirebaseAuth mAuth;
    DatabaseReference mydb;
    FirebaseDatabase myfb;
    private StorageReference storage;

    private static  final String unamepattern="^.*(?=.{6,})$";
    private static  final String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    Matcher matcher;
    Pattern pname,pemail;

    final  int PICK_IMAGE_REQUEST=7;
    private Uri filePath;

    user user1;
    int i=1;
    String type, email, cat, dis, subd, workno, passw, confpass,uname,spinnerdistrict,spinnersubdistrict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        pname=Pattern.compile(unamepattern);
        pemail=Pattern.compile(emailpattern);

        userRadio = (RadioButton) findViewById(R.id.userRadio);
        workerRadio = (RadioButton) findViewById(R.id.workerRadio);
        contractorRadio = (RadioButton) findViewById(R.id.contractorRadio);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        submitButton = (Button) findViewById(R.id.submitButton);
        name = (EditText) findViewById(R.id.name);
        imageView=findViewById(R.id.image);
//        address = findViewById(R.id.address);
//        adhaar = findViewById(R.id.adhaar);
        category = findViewById(R.id.spinner_category);
        spinner_district = findViewById(R.id.spinner_district);
        spinner_subdistrict =findViewById(R.id.spinner_subdistrict);
        numberOfWorker = findViewById(R.id.spinner_numberOfWorker);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        renterPassworsd = findViewById(R.id.renter_password);




        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!validateemail(name.getText().toString())){
                        name.setError("Invaild email");
                    }

                }

            }
        });
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(username.getText().toString().length()<3){
                        username.setError("Please check length");

                    }
                }

            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(password.getText().toString().length()<5){
                        password.setError("Invaild password");
                    }

                }

            }
        });




        storage = FirebaseStorage.getInstance().getReference();

        radioGroup.setOnCheckedChangeListener(this);

        ArrayAdapter district = ArrayAdapter.createFromResource(this, R.array.district_array, android.R.layout.simple_spinner_item);
        district.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner_district.setAdapter(district);
        spinner_district.setOnItemSelectedListener(this);

        spinner_subdistrict.setOnItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.district_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
//        district.setAdapter(adapter);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        category.setAdapter(adapter1);


        submitButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View v){
        if (name.getText().toString()==null &&validateemail(name.getText().toString()) ){
            name.setError("Invalid email");
            }
                if (username.getText().toString()==null ){
                    name.setError("Invalid email");
                }
                if(password.getText().toString()==null&&validatepassword(password.getText().toString())){
                      password.setError("Invalid password");
                }
                if (renterPassworsd.getText().toString()==" "){
            password.setError("invalid password");
            renterPassworsd.setError("invalid password");
                }



                if (userRadio.isChecked() == true) {
                    type = userRadio.getText().toString();
                }
//                } else if (workerRadio.isChecked() == true) {
//                    cat = category.getSelectedItem().toString();
//                    dis = spinner_district.getSelectedItem().toString();
//                    subd=spinner_subdistrict.getSelectedItem().toString();
                   else if (workerRadio.isChecked() == true){
                    type = workerRadio.getText().toString();
                }

//                    cat = category.getSelectedItem().toString();
//                    dis = spinner_district.getSelectedItem().toString();
//                    subd=spinner_subdistrict.getSelectedItem().toString();
//                    workno = numberOfWorker.getSelectedItem().toString();
                else if(contractorRadio.isChecked()==true) {
                        type = contractorRadio.getText().toString();
                    }


//                    cat = category.getSelectedItem().toString();
//                    dis = spinner_district.getSelectedItem().toString();
                    if(spinner_subdistrict.getSelectedItem()==null){
                        subd="";
                    }else {
                        subd = spinner_subdistrict.getSelectedItem().toString();
                    }
                if(spinner_district.getSelectedItem()==null){
                    dis="";
                }else {
                    dis = spinner_district.getSelectedItem().toString();
                }
                if(category.getSelectedItem()==null){
                    cat="";
                }else {
                    cat = category.getSelectedItem().toString();
                }
                if(numberOfWorker.getSelectedItem()==null){
                    workno="";
                }else {
                    
                    workno = numberOfWorker.getSelectedItem().toString();
                }

//                    workno = numberOfWorker.getSelectedItem().toString();
                  type = contractorRadio.getText().toString();
                    email = name.getText().toString();



                passw = password.getText().toString();
                confpass = renterPassworsd.getText().toString();
                uname=username.getText().toString();
                if (passw.equals(confpass)) {



                    mAuth.createUserWithEmailAndPassword(email, passw)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        //task.getResult().getUser().getUid();
                                        // Sign in success, update UI with the signed-in user's information
                                        myfb = FirebaseDatabase.getInstance();
                                        mydb = myfb.getReference("users");
                                        //user1 = new user();
                                        FirebaseUser currentUser = mAuth.getCurrentUser();

                                        final DatabaseReference userId=mydb.child(currentUser.getUid());
                                        userId.child("Name").setValue(uname);
                                        userId.child("Email").setValue(email);
                                        userId.child("Type").setValue(type);
                                        userId.child("Category").setValue(cat);
                                        userId.child("Subdistrict").setValue(subd);
                                        userId.child("District").setValue(dis);
                                        userId.child("Workno").setValue(workno);

                                        uploadimage(currentUser.getUid());
                                        Toast t = Toast.makeText(getApplicationContext(), "Registration sucessfull", Toast.LENGTH_SHORT);

                                        t.show();
                                        Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                                        startActivity(intent);

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(getApplicationContext(), "This email is already registered",
                                                Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });

                } else
                    password.setError("password missmatch");
                    renterPassworsd.setError("password missmatch");
            }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.userRadio:
                category.setVisibility(View.GONE);
//                category.setVisibility(View.INVISIBLE);
                spinner_district.setVisibility(View.GONE);
                spinner_subdistrict.setVisibility(View.GONE);
                numberOfWorker.setVisibility(View.GONE);
                break;
            case R.id.workerRadio:
                category.setVisibility(View.VISIBLE);
                spinner_district.setVisibility(View.VISIBLE);
                spinner_subdistrict.setVisibility(View.VISIBLE);
                numberOfWorker.setVisibility(View.GONE);
                break;
            case  R.id.contractorRadio:
                numberOfWorker.setVisibility(View.VISIBLE);

        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "selecting ");
//
            if (parent.getId() == R.id.spinner_subdistrict)
            {
                spinnersubdistrict=spinner_subdistrict.getSelectedItem().toString();
            }
            if (parent.getId() == R.id.spinner_district) {


                if (spinner_district.getSelectedItem().equals("Kasargod")) {


                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_kasargod, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();

                } else if (spinner_district.getSelectedItem().equals("Kannur")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_kannur, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();

                } else if (spinner_district.getSelectedItem().equals("Kozhikode")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_kozhikode, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();



                } else if (spinner_district.getSelectedItem().equals("Wayanad")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_wayanad, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();



                } else if (spinner_district.getSelectedItem().equals("Malappuram")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_malappuram, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();



                } else if (spinner_district.getSelectedItem().equals("Idukki")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_idukki, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();



                } else if (spinner_district.getSelectedItem().equals("Palakkad")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_palakkad, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();



                } else if (spinner_district.getSelectedItem().equals("Alappuzha")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_alappuzha, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();



                } else if (spinner_district.getSelectedItem().equals("Thrissur")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_thissur, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();



                } else if (spinner_district.getSelectedItem().equals("Ernakulam")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_Ernamkulam, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();



                } else if (spinner_district.getSelectedItem().equals("Kottayam")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_kottayam, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();



                } else if (spinner_district.getSelectedItem().equals("Kollam")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_kollam, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();



                } else if (spinner_district.getSelectedItem().equals("Pathanamthitta")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,

                            R.array.Thaluk_pathanamthitta, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();



                } else if (spinner_district.getSelectedItem().equals("Thiruvandapuram")) {

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
                            R.array.Thaluk_thiruvanandapuram, android.R.layout.simple_spinner_item);
                    spinner_subdistrict.setAdapter(adapter2);
                    spinnerdistrict=spinner_district.getSelectedItem().toString();



                }
                spinner_subdistrict.setEnabled(true);

            }




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void uploadimage(String UID) {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storage.child("images/"+ UID.toString());

            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    public void ProfileImageClick(View view) {
        pickimage();
    }

    private void pickimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE_REQUEST );
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);


            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    public boolean validateemail(String email)
    {

        matcher=pemail.matcher(email);

        return matcher.matches();
    }
    public boolean validatepassword(String email)
    {

        matcher=pname.matcher(email);

        return matcher.matches();
    }



    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}



