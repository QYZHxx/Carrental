package com.zuchexing.carrental.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.zuchexing.carrental.R;
import com.zuchexing.carrental.bmob.MyUser;
import java.io.File;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by 情谊纵横 on 2016/4/21
 */
public class MyInformation extends Activity {

    EditText editText;
    EditText edt_nickname;
    EditText edt_education;
    EditText edt_profession;
    EditText edt_hobby;
    EditText edt_email;
    TextView txt_name;
    EditText edt_age;
    EditText edt_birthday;

    //
        /* 头像文件 */
    final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    /* 请求识别码 */
    final int CODE_GALLERY_REQUEST = 0xa0;
    final int CODE_CAMERA_REQUEST = 0xa1;
    final int CODE_RESULT_REQUEST = 0xa2;

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    int output_X = 480;
    int output_Y = 480;

    private ImageView img_head = null;
    private AlertDialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.my_act_information);

        xs();
    }

    public void xs(){
        edt_email=(EditText)findViewById(R.id.edt_email);
        editText = (EditText)findViewById(R.id.editText);
        edt_nickname=(EditText)findViewById(R.id.edt_nickname);
        edt_education=(EditText)findViewById(R.id.edt_education);
        edt_profession=(EditText)findViewById(R.id.edt_profession);
        edt_hobby=(EditText)findViewById(R.id.edt_hobby);
        txt_name=(TextView)findViewById(R.id.txt_name);

        MyUser user = BmobUser.getCurrentUser(MyInformation.this,MyUser.class);
        edt_hobby.setText(user.getLikes());
        edt_profession.setText(user.getPost());
        edt_education.setText(user.getEducation());
        edt_nickname.setText(user.getNickname());
        editText.setText(user.getIntroduce());
        edt_email.setText(user.getEmail());
        txt_name.setText(user.getUsername());
        edt_age.setText(user.getAge());
        edt_birthday.setText(user.getBirthday());

    }

    public void savee(View v){
        EditText editText = (EditText)findViewById(R.id.editText);
        EditText edt_nickname=(EditText)findViewById(R.id.edt_nickname);
        EditText edt_education=(EditText)findViewById(R.id.edt_education);
        EditText edt_profession=(EditText)findViewById(R.id.edt_profession);
        EditText edt_hobby=(EditText)findViewById(R.id.edt_hobby);
        EditText edt_email=(EditText)findViewById(R.id.edt_email);
        EditText edt_birthday=(EditText)findViewById(R.id.edt_birthday);
        EditText edt_age=(EditText)findViewById(R.id.edt_age);

//        ImageView img_head=(ImageView)findViewById(R.id.img_head);
        MyUser user = BmobUser.getCurrentUser(MyInformation.this,MyUser.class);
//        System.out.println(user.getObjectId()+"id");
        user.setNickname(edt_nickname.getText().toString());
        user.setEducation(edt_education.getText().toString());
        user.setPost(edt_profession.getText().toString());
        user.setLikes(edt_hobby.getText().toString());
        user.setIntroduce(editText.getText().toString());
        user.setEmail(edt_email.getText().toString());
        user.setAge(edt_age.getText().toString());
        user.setBirthday(edt_birthday.getText().toString());
//        user.setHeadPortrait(img_head.);
        user.update(MyInformation.this, new UpdateListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MyInformation.this, "保存成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MyInformation.this, "保存失败", Toast.LENGTH_SHORT).show();
            }
        });

        BmobUser user1=new BmobUser();
        user1.setEmail(edt_email.getText().toString());
        user1.update(MyInformation.this, new UpdateListener() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailure(int i, String s) {
            }
        });



    }

    public void head(View v){
        LinearLayout isloginform=(LinearLayout)getLayoutInflater().inflate(R.layout.my_nvt_image, null);
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(isloginform);
        dialog=builder.create();
        dialog.show();
        Button back = (Button)isloginform.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
        img_head = (ImageView) findViewById(R.id.img_head);
        final Button buttonLocal = (Button)isloginform.findViewById(R.id.buttonLocal);
        buttonLocal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                choseHeadImageFromGallery();
            }
        });
        final Button buttonCamera = (Button)isloginform.findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                choseHeadImageFromCameraCapture();
            }
        });
    }

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }

        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {
                    setImageToHeadView(intent);
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            img_head.setImageBitmap(photo);
        }
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }


}
