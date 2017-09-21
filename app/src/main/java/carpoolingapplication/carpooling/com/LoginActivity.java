package carpoolingapplication.carpooling.com;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText pass_edt , email_edt;
    CheckBox RememberMeCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        pass_edt = (EditText) findViewById(R.id.pass_edt);
        email_edt = (EditText) findViewById(R.id.email_edt);
        RememberMeCheck = (CheckBox) findViewById(R.id.RememberMeCheck);

    }

    public void CreateNewAccount(View view) {
        startActivity(new Intent(this , user_registration.class));
    }

    public void ForgetPassword(View view) {
        startActivity(new Intent(this , ForgetPassActivity.class));
    }

    public void OnClickSignIn(View view) {

        if (pass_edt.getText().length() > 0 && email_edt.getText().length() > 0 ){

            startActivity(new Intent(LoginActivity.this, HomeActivity.class));

        mAuth.signInWithEmailAndPassword(email_edt.getText().toString(), pass_edt.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("firebaseLogin", "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w("firebaseLogin", "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "Login failed",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login success ", Toast.LENGTH_SHORT).show();
                            getUID();
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                        }
                    }
                });
    }else

            Toast.makeText(this, "Email and Password should not be empty", Toast.LENGTH_SHORT).show();


    }

    private void getUID() {

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userID = user.getUid();
            startActivity(new Intent(LoginActivity.this, ChooseOptionsActivity.class).putExtra("user_id",userID));
        } else {
            // User is signed out
            Log.d("firebase", "onAuthStateChanged:signed_out");
        }
    }
}
