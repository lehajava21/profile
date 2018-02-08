package com.telran.mishpahug.view.profile;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
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

public class FragmentProfile extends Fragment implements IViewProfile, View.OnClickListener {

    private IPresenterViewProfile presenter;
    private AppCompatActivity activity;

    private EditText fullName;
    private CheckBox type;
    private CheckBox sex;
    private TextView birthday;
    private ImageView photo;
    private EditText address;
    private CheckBox marStatus;
    private EditText kitchen;
    private EditText religion;
    private EditText kosher;
    private EditText language;
    private EditText about;
    private EditText allergy;
    private Button save;
    private Uri uri;
    private Boolean photoChanged;
    private ProgressBar progressBar;

    public FragmentProfile() {
    }

    @SuppressLint("ValidFragment")
    public FragmentProfile(IPresenterViewProfile presenter, AppCompatActivity activity){
        this.presenter = presenter;
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        fullName = v.findViewById(R.id.fullName_edit);
        type = v.findViewById(R.id.invite_check);
        sex = v.findViewById(R.id.female_check);
        birthday = v.findViewById(R.id.birthday_text);
        photo = v.findViewById(R.id.photo_image);
        address = v.findViewById(R.id.address_edit);
        marStatus = v.findViewById(R.id.family_check);
        kitchen = v.findViewById(R.id.kitchen_edit);
        religion = v.findViewById(R.id.religion_edit);
        kosher = v.findViewById(R.id.kosher_edit);
        language = v.findViewById(R.id.language_edit);
        about = v.findViewById(R.id.about_edit);
        allergy = v.findViewById(R.id.allergy_edit);
        save = v.findViewById(R.id.save_btn);
        progressBar = activity.findViewById(R.id.progress_bar);
        photo.setOnClickListener(this);
        birthday.setOnClickListener(this);
        save.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_btn :
                presenter.onSave(photoChanged);
                photoChanged = false;
                break;
            case R.id.photo_image :
                presenter.onPhoto();
                break;
            case R.id.birthday_text :
                presenter.onBirthday();
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
    public Profile saveProfile(Profile p) {
        p.setFullname(String.valueOf(fullName.getText()));
        p.setType(type.isChecked());
        p.setSex(sex.isChecked());
        p.setBirthday(String.valueOf(birthday.getText()));
        p.setAddress(String.valueOf(address.getText()));
        p.setMarStatus(marStatus.isChecked());
        p.setKitchen(String.valueOf(kitchen.getText()));
        p.setReligion(String.valueOf(religion.getText()));
        p.setKosher(String.valueOf(kosher.getText()));
        p.setLanguage(String.valueOf(language.getText()));
        p.setAbout(String.valueOf(about.getText()));
        p.setAllergy(String.valueOf(allergy.getText()));
        return p;
    }

    @Override
    public void loadProfile(Profile p) {
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
    public Uri getUri() {
        return uri;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == activity.RESULT_OK
                    && requestCode == RequestCode.PHOTO.getId()
                    && data != null) {
                uri = data.getData();
                Picasso.with(activity).load(data.getData()).into(photo);
                photoChanged = true;
            }
    }

}
