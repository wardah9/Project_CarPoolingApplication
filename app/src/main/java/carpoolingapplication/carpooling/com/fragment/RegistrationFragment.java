package carpoolingapplication.carpooling.com.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import carpoolingapplication.carpooling.com.BarcodeScanner;
import carpoolingapplication.carpooling.com.R;


public class RegistrationFragment extends Fragment {

    private FrameLayout fragmentContainer;

    /**
     * Create a new instance of the fragment
     */
    public static RegistrationFragment newInstance(int index) {
        RegistrationFragment fragment = new RegistrationFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments().getInt("index", 0) == 0) {
            View view = inflater.inflate(R.layout.fragment_insert_data, container, false);
//            initDemoSettings(view);
            return view;
        } else if (getArguments().getInt("index", 1) == 1) {
            View view = inflater.inflate(R.layout.fragment_validate, container, false);
//            initDemoList(view);
            return view;
        } else {
            View view = inflater.inflate(R.layout.fragment_barcode, container, false);
//            initDemoList(view);
            return view;
        }
    }

    /**
     * Init the fragment1
     */
    private void initDemoSettings(View view) {
        // fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_container);
    }

    /**
     * Init the fragment2
     */
    private void initDemoList(View view) {
        //  fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_container);

    }

    /**
     * Refresh
     */
    public void refresh() {

    }

    /**
     * Called when a fragment will be displayed
     */
    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    /**
     * Called when a fragment will be hidden
     */
    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }
    }

    public static class Reg1 extends FragmentActivity {

        private EditText userName;
        private EditText userEmail;
        private EditText userPhone;
        private EditText userNationality;
        private EditText userPass;
        private Spinner userGender;
        private EditText userAge;
        private Button RegisterUser;

        String[] items = {
                "Male",
                "Female"
        };

        private DatabaseReference myRef;
        private FirebaseAuth uAuth;
        private FirebaseDatabase database;

        @Override
        public View onCreateView(String name, final Context context, AttributeSet attrs) {
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_insert_data, null, false);


            uAuth = FirebaseAuth.getInstance();
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("Project_users");

            userName = (EditText) v.findViewById(R.id.user_name);
            userEmail = (EditText) v.findViewById(R.id.user_email);
            userPhone = (EditText) v.findViewById(R.id.user_phone);
            userNationality = (EditText) v.findViewById(R.id.user_nationality);
            userPass = (EditText) v.findViewById(R.id.user_pass);
            userGender = (Spinner) v.findViewById(R.id.user_gender);
            userAge = (EditText) v.findViewById(R.id.user_age);

            RegisterUser = (Button) v.findViewById(R.id.register_user);


            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
            userGender.setAdapter(adapter);

            if (userName.getText().length() > 5 && userName.getText().length() != 0) {

            }

            if (Patterns.EMAIL_ADDRESS.matcher(userEmail.getText().toString()).matches()) {

            }


            if (userPhone.getText().length() > 5 && userPhone.getText().length() != 0) {

            }


            if (userNationality.getText().length() != 0) {

            }

            RegisterUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    uAuth.createUserWithEmailAndPassword(userEmail.getText().toString(), userPass.getText().toString())
                            .addOnCompleteListener(Reg1.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("firebaseLogin", "createUserWithEmail:onComplete:" + task.isSuccessful());

                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "register successful", Toast.LENGTH_SHORT).show();
                                    } else
                                        Toast.makeText(getApplicationContext(), "register failed ", Toast.LENGTH_SHORT).show();
//                                    getUID();
                                }
                            });
                }
            });


            return v;
        }
    }

    public static class Reg2 extends FragmentActivity {
        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_validate, null, false);
            return v;
        }
    }

    public static class Reg3 extends FragmentActivity {

        TextView view_barcode;

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_barcode, null, false);

            view_barcode = (TextView) v.findViewById(R.id.view_barcode);

            view_barcode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    startActivity(new Intent(getApplicationContext(), BarcodeScanner.class));
                }
            });
            return v;
        }
    }

}
