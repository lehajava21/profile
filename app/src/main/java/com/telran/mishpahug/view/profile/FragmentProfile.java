package com.telran.mishpahug.view.profile;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.telran.mishpahug.R;
import com.telran.mishpahug.model.Profile;
import com.telran.mishpahug.model.RequestCode;
import com.telran.mishpahug.presenter.profile.IViewProfile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class FragmentProfile extends Fragment implements IViewProfile, View.OnClickListener {

    private IPresenterViewProfile presenter;
    private AppCompatActivity activity;
    private View v;
    private Profile profile;

    private int dim_max;
    private int dim_min;
    private final long tapTime = 1000;
    private int max_photo;

    private ImageView[] photo;
    private int photo_id;
    private EditText fullName;
    private CheckBox type;
    private CheckBox sex;
    private TextView birthday;
    private EditText address;
    private CheckBox marStatus;
    private EditText kitchen;
    private EditText religion;
    private EditText kosher;
    private EditText language;
    private EditText about;
    private EditText allergy;
    private Button save;
    private ProgressBar progressBar;

    public FragmentProfile() {
    }

    @SuppressLint("ValidFragment")
    public FragmentProfile(IPresenterViewProfile presenter, AppCompatActivity activity){
        this.presenter = presenter;
        this.activity = activity;
    }

    @Override
    public void setProfile(Profile profile) {
        this.profile = profile;
        max_photo = profile.getPhoto().length;
        photo = new ImageView[max_photo];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        dim_max = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                200, getResources().getDisplayMetrics());
        dim_min = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                80, getResources().getDisplayMetrics());
        photo[0] = v.findViewById(R.id.image_0);
        photo[1] = v.findViewById(R.id.image_1);
        photo[2] = v.findViewById(R.id.image_2);
        photo[0].setOnClickListener(this);
        photo[1].setOnClickListener(this);
        photo[2].setOnClickListener(this);
        TListener tl = new TListener();
        photo[0].setOnTouchListener(tl);
        photo[1].setOnTouchListener(tl);
        photo[2].setOnTouchListener(tl);
        fullName = v.findViewById(R.id.fullName_edit);
        type = v.findViewById(R.id.invite_check);
        sex = v.findViewById(R.id.female_check);
        birthday = v.findViewById(R.id.birthday_text);
        address = v.findViewById(R.id.address_edit);
        marStatus = v.findViewById(R.id.family_check);
        kitchen = v.findViewById(R.id.kitchen_edit);
        religion = v.findViewById(R.id.religion_edit);
        kosher = v.findViewById(R.id.kosher_edit);
        language = v.findViewById(R.id.language_edit);
        about = v.findViewById(R.id.about_edit);
        allergy = v.findViewById(R.id.allergy_edit);
        save = v.findViewById(R.id.save_btn);
        progressBar = v.findViewById(R.id.progress_bar);
        birthday.setOnClickListener(this);
        save.setOnClickListener(this);
        loadProfile(profile);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_0:
                photo[0].getLayoutParams().height = dim_max;
                photo[0].getLayoutParams().width = dim_max;
                photo[0].requestLayout();
                photo[1].getLayoutParams().height = dim_min;
                photo[1].getLayoutParams().width = dim_min;
                photo[1].requestLayout();
                photo[2].getLayoutParams().height = dim_min;
                photo[2].getLayoutParams().width = dim_min;
                photo[2].requestLayout();
                break;
            case R.id.image_1:
                photo[0].getLayoutParams().height = dim_min;
                photo[0].getLayoutParams().width = dim_min;
                photo[0].requestLayout();
                photo[1].getLayoutParams().height = dim_max;
                photo[1].getLayoutParams().width = dim_max;
                photo[1].requestLayout();
                photo[2].getLayoutParams().height = dim_min;
                photo[2].getLayoutParams().width = dim_min;
                photo[2].requestLayout();
                break;
            case R.id.image_2:
                photo[0].getLayoutParams().height = dim_min;
                photo[0].getLayoutParams().width = dim_min;
                photo[0].requestLayout();
                photo[1].getLayoutParams().height = dim_min;
                photo[1].getLayoutParams().width = dim_min;
                photo[1].requestLayout();
                photo[2].getLayoutParams().height = dim_max;
                photo[2].getLayoutParams().width = dim_max;
                photo[2].requestLayout();
                break;
            case R.id.birthday_text :
                presenter.onBirthday();
                break;
            case R.id.save_btn :
                presenter.onSave();
                break;
        }
    }

    @Override
    public void onMessage(String title, String content) {
        new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("Ok",null)
                .create()
                .show();
    }

    @Override
    public Profile saveProfile() {
        profile.setFullname(String.valueOf(fullName.getText()));
        profile.setType(type.isChecked());
        profile.setSex(sex.isChecked());
        profile.setBirthday((String) birthday.getText());
        profile.setAddress(String.valueOf(address.getText()));
        profile.setMarStatus(marStatus.isChecked());
        profile.setKitchen(String.valueOf(kitchen.getText()));
        profile.setReligion(String.valueOf(religion.getText()));
        profile.setKosher(String.valueOf(kosher.getText()));
        profile.setLanguage(String.valueOf(language.getText()));
        profile.setAbout(String.valueOf(about.getText()));
        profile.setAllergy(String.valueOf(allergy.getText()));
        return profile;
    }

    private void loadProfile(Profile p) {
        fullName.setText(p.getFullname());
        type.setChecked(p.getType());
        sex.setChecked(p.getSex());
        birthday.setText(p.getBirthday());
        address.setText(p.getAddress());
        marStatus.setChecked(p.getMarStatus());
        kitchen.setText(p.getKitchen());
        religion.setText(p.getReligion());
        kosher.setText(p.getKosher());
        language.setText(p.getLanguage());
        about.setText(p.getAbout());
        allergy.setText(p.getAllergy());
        for(int i = 0; i < photo.length; i++){
            if(p.getPhoto()[i].isEmpty()){
                Picasso.with(activity).load(R.drawable.unknown).into(photo[i]);
            }else {
                Picasso.with(activity).load(p.getPhoto()[i]).into(photo[i]);
            }
        }
    }

    @Override
    public void setBirthday(String date) {
        birthday.setText(date);
    }

    @Override
    public void showProgress(Boolean on) {
        if(on){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onCloudinaryUrl(String url) {
        for(int i = 0; i < photo.length; i++){
            if(photo[i].equals(v.findViewById(photo_id))){
                profile.getPhoto()[i] = url;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == activity.RESULT_OK
                    && requestCode == RequestCode.PHOTO.getId() && data != null) {
                presenter.onPhoto(data.getData());
                ImageView photoView = v.findViewById(photo_id);
                Picasso.with(activity).load(data.getData()).into(photoView);
            }
    }

    class TListener implements View.OnTouchListener{
        private long downTime;
        private long upTime;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                downTime = SystemClock.uptimeMillis();
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                upTime = SystemClock.uptimeMillis();
                if(upTime - downTime > tapTime){
                    photo_id = v.getId();
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, RequestCode.PHOTO.getId());
                    return true;
                }
            }
            return false;
        }
    }

}
