package carpoolingapplication.carpooling.com.fragment;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import carpoolingapplication.carpooling.com.R;
import carpoolingapplication.carpooling.com.model.UserData;

public class InsertDataFragment extends Fragment {

    private FrameLayout fragmentContainer;
    private EditText userPass;
    private TextView str;
    private int i = 1;
    private ProgressBar progressBar;
    private EditText userName;
    private EditText userEmail;
    private EditText userPhone;
    private EditText userNationality;
    private Spinner userGender;
    private EditText userAge;
    private Button RegisterUser;

    String[] items = {
            "Male",
            "Female"
    };
    private FirebaseAuth uAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public UserData userData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_data, container, false);

        uAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Project_users");

        userName = (EditText) view.findViewById(R.id.user_name);
        userEmail = (EditText) view.findViewById(R.id.user_email);
        userPhone = (EditText) view.findViewById(R.id.user_phone);
        userNationality = (EditText) view.findViewById(R.id.user_nationality);
        userPass = (EditText) view.findViewById(R.id.user_pass);
        userGender = (Spinner) view.findViewById(R.id.user_gender);
        userAge = (EditText) view.findViewById(R.id.user_age);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        str = (TextView) view.findViewById(R.id.textView1);
        RegisterUser = (Button) view.findViewById(R.id.register_user);


        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        userGender.setAdapter(adapter);


        RegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.INVISIBLE);
                str.setVisibility(View.INVISIBLE);

                if (userName.getText().length()>0
                        && userEmail.getText().length()>0
                        && userPhone.getText().length()>0
                        && userNationality.getText().length()>0
                        && userPass.getText().length()>0
                        && userAge.getText().length()>0){

                    uAuth.createUserWithEmailAndPassword(userEmail.getText().toString(), userPass.getText().toString())
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("firebaseLogin", "createUserWithEmail:onComplete:" + task.isSuccessful());

                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "register successful", Toast.LENGTH_SHORT).show();
                                    } else
                                        Toast.makeText(getActivity(), "register failed ", Toast.LENGTH_SHORT).show();
                                    Log.d("firebaseLogin", "onComplete: Failed=" + task.getException().getMessage()); //ADD THIS

//                                    getUID();
                                }
                            });
                }else
                    Toast.makeText(getActivity(), "error !!", Toast.LENGTH_SHORT).show();

            }
        });
            userPass.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    // TODO Auto-generated method stub
                    if (userPass.getText().toString().length() == 0) {
                        userPass.setError("Enter your password..!");
                    } else {
                        progressBar.setVisibility(View.VISIBLE);
                        str.setVisibility(View.VISIBLE);
                        caculation();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                    // TODO Auto-generated method stub

                }

            });

        return view;
    }

    private void getUID() {

        String pkey = FirebaseAuth.getInstance().getCurrentUser().getUid();

        UserData.getInstance().setUserData(userName.getText().toString(),userEmail.getText().toString(),userPhone.getText().toString(),userNationality.getText().toString(),userGender.getSelectedItem().toString(),userAge.getText().toString(),"",pkey);

        myRef.child(pkey).setValue(UserData.getInstance());

    }

    protected void caculation() {
        // Auto-generated method stub
        String temp = userPass.getText().toString();
        System.out.println(i + " current password is : " + temp);
        i = i + 1;

        int length = 0, uppercase = 0, lowercase = 0, digits = 0, symbols = 0, bonus = 0, requirements = 0;

        int lettersonly = 0, numbersonly = 0, cuc = 0, clc = 0;

        length = temp.length();
        for (int i = 0; i < temp.length(); i++) {
            if (Character.isUpperCase(temp.charAt(i)))
                uppercase++;
            else if (Character.isLowerCase(temp.charAt(i)))
                lowercase++;
            else if (Character.isDigit(temp.charAt(i)))
                digits++;

            symbols = length - uppercase - lowercase - digits;

        }

        for (int j = 1; j < temp.length() - 1; j++) {

            if (Character.isDigit(temp.charAt(j)))
                bonus++;

        }

        for (int k = 0; k < temp.length(); k++) {

            if (Character.isUpperCase(temp.charAt(k))) {
                k++;

                if (k < temp.length()) {

                    if (Character.isUpperCase(temp.charAt(k))) {

                        cuc++;
                        k--;

                    }

                }

            }

        }

        for (int l = 0; l < temp.length(); l++) {

            if (Character.isLowerCase(temp.charAt(l))) {
                l++;

                if (l < temp.length()) {

                    if (Character.isLowerCase(temp.charAt(l))) {

                        clc++;
                        l--;

                    }

                }

            }

        }

//            System.out.println("length" + length);
//            System.out.println("uppercase" + uppercase);
//            System.out.println("lowercase" + lowercase);
//            System.out.println("digits" + digits);
//            System.out.println("symbols" + symbols);
//            System.out.println("bonus" + bonus);
//            System.out.println("cuc" + cuc);
//            System.out.println("clc" + clc);

        if (length > 7) {
            requirements++;
        }

        if (uppercase > 0) {
            requirements++;
        }

        if (lowercase > 0) {
            requirements++;
        }

        if (digits > 0) {
            requirements++;
        }

        if (symbols > 0) {
            requirements++;
        }

        if (bonus > 0) {
            requirements++;
        }

        if (digits == 0 && symbols == 0) {
            lettersonly = 1;
        }

        if (lowercase == 0 && uppercase == 0 && symbols == 0) {
            numbersonly = 1;
        }

        int Total = (length * 4) + ((length - uppercase) * 2)
                + ((length - lowercase) * 2) + (digits * 4) + (symbols * 6)
                + (bonus * 2) + (requirements * 2) - (lettersonly * length * 2)
                - (numbersonly * length * 3) - (cuc * 2) - (clc * 2);

        System.out.println("Total" + Total);

        if (Total < 30) {
            progressBar.setProgress(Total - 15);
            str.setText("Week");
            str.setTextColor(Color.BLUE);

        } else if (Total >= 40 && Total < 50) {
            progressBar.setProgress(Total - 20);
            str.setText("Medium");
            str.setTextColor(Color.BLUE);

        } else if (Total >= 56 && Total < 70) {
            progressBar.setProgress(Total - 25);
            str.setText("Medium");
            str.setTextColor(Color.BLUE);

        } else if (Total >= 76) {
            progressBar.setProgress(Total - 30);
            str.setText("Strong");
            str.setTextColor(Color.BLUE);

        } else {
            progressBar.setProgress(Total - 20);
            str.setText("Strong");
            str.setTextColor(Color.BLUE);

        }

    }

}
