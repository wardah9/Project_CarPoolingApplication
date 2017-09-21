package carpoolingapplication.carpooling.com;

import android.*;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import carpoolingapplication.carpooling.com.map.MapsActivity;

public class InsertCarData extends AppCompatActivity {

    private int SELECT_FROM_GALLARY = 2;
    private int SELECT_FROM_CAMERA = 1;
    private ImageView img_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_car_data);

        Button register_user = (Button) findViewById(R.id.register_user);
        Button insert_img = (Button) findViewById(R.id.insert_img);
        img_car = (ImageView) findViewById(R.id.img_car);


        insert_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(InsertCarData.this);
                dialog.setContentView(R.layout.custom_dialog_ex2);
                Button camera_button = (Button) dialog.findViewById(R.id.cc);
                Button gallery_button = (Button) dialog.findViewById(R.id.gg);

                camera_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (ContextCompat.checkSelfPermission(InsertCarData.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(InsertCarData.this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
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


        register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InsertCarData.this , MapsActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        img_car.setVisibility(View.VISIBLE);

        if (requestCode == SELECT_FROM_CAMERA && resultCode == RESULT_OK) {

            Bitmap cameraBitmap = (Bitmap) data.getExtras().get("data");
//            img_car.setVisibility(View.VISIBLE);
            img_car.setImageBitmap(cameraBitmap);
        } else if (requestCode == SELECT_FROM_GALLARY && resultCode == RESULT_OK) {

            try {
                InputStream input = getContentResolver().openInputStream(data == null ? null : data.getData());
                final Bitmap bitmap = BitmapFactory.decodeStream(input, null, new BitmapFactory.Options());

                Drawable drawable = new BitmapDrawable(getResources(), bitmap);
//                img_car.setVisibility(View.VISIBLE);
                img_car.setImageDrawable(drawable);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, "Error occurred ! ,  " + resultCode, Toast.LENGTH_SHORT).show();
        }
    }
}
