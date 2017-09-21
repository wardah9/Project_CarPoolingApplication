package carpoolingapplication.carpooling.com.fragment;


import android.Manifest;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import carpoolingapplication.carpooling.com.R;

import static android.app.Activity.RESULT_OK;
import static carpoolingapplication.carpooling.com.R.drawable.user_identity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAccountFragment extends Fragment {


    private Button update_bt;
    private ImageView img_profile;
    TextView textView_dialog;
    private int SELECT_FROM_GALLARY = 2;
    private int SELECT_FROM_CAMERA = 1;
    private TextView phoneNumber;
    private EditText editphoneNumber;
    private TextView phoneNumbervisiable;
    private TextView EmailTv;
    private EditText editEmail;
    private TextView EmailTvvisiable;

    public UserAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);


        update_bt = (Button) rootView.findViewById(R.id.update_bt);
        img_profile = (ImageView) rootView.findViewById(R.id.img_profile);

        textView_dialog = (TextView) rootView.findViewById(R.id.choose_view);

        img_profile.setImageResource(R.drawable.user_identity);

//        phoneNumber = (TextView) rootView.findViewById(R.id.tvNumber1);
//        editphoneNumber = (EditText) rootView.findViewById(R.id.edit_Number1);
//        phoneNumbervisiable = (TextView) rootView.findViewById(R.id.tvNumber1_visable);
//        EmailTv = (TextView) rootView.findViewById(R.id.tvEmail);
//        editEmail = (EditText) rootView.findViewById(R.id.edit_tvEmail);
//        EmailTvvisiable = (TextView) rootView.findViewById(R.id.tvEmail_visable);
        update_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                phoneNumbervisiable.setVisibility(View.GONE);
//                EmailTvvisiable.setVisibility(View.GONE);
//
//                editphoneNumber.setVisibility(View.VISIBLE);
//                editEmail.setVisibility(View.VISIBLE);

            }
        });


        textView_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_dialog_ex2);
                Button camera_button = (Button) dialog.findViewById(R.id.cc);
                Button gallery_button = (Button) dialog.findViewById(R.id.gg);

                camera_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                        } else {
                            Intent cameraIntent = new Intent();
                            cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, SELECT_FROM_CAMERA);
                        }

                        dialog.dismiss();
                    }
                });
                gallery_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(intent, SELECT_FROM_GALLARY);

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SELECT_FROM_CAMERA && resultCode == RESULT_OK) {

            Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
            img_profile.setImageBitmap(cameraBitmap);
        } else if (requestCode == SELECT_FROM_GALLARY && resultCode == RESULT_OK) {

            try {
                InputStream input = getActivity().getContentResolver().openInputStream(data == null ? null : data.getData());
                final Bitmap bitmap = BitmapFactory.decodeStream(input, null, new BitmapFactory.Options());

                Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                img_profile.setImageDrawable(drawable);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(getActivity(), "Error occurred ! ,  " + resultCode, Toast.LENGTH_SHORT).show();
        }


    }
}



