package com.example.ig3_smartcity_android.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.ig3_smartcity_android.R;
import com.example.ig3_smartcity_android.databinding.FragmentDonnationBinding;
import com.example.ig3_smartcity_android.model.JwtTokenPayload;
import com.example.ig3_smartcity_android.repositories.dto.JwtTokenPayloadDTO;
import com.example.ig3_smartcity_android.services.mappers.TokenMapper;
import com.example.ig3_smartcity_android.ui.actitvity.MainActivity;
import com.example.ig3_smartcity_android.ui.viewModel.DonnationViewModel;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class DonnationFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private EditText nameMealText;
    private EditText descriptionText;
    private EditText categorieText;
    private EditText nbPortionText;
    private Button takePictureButton;
    private Button addMealButton;
    private ImageView imageView;
    private DonnationViewModel donnationViewModel;
    private FragmentDonnationBinding binding;
    private SharedPreferences sharedPreferences;

    public DonnationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        donnationViewModel = new ViewModelProvider(this).get(DonnationViewModel.class);
        binding = FragmentDonnationBinding.inflate(inflater,container,false);
        View root  = inflater.inflate(R.layout.fragment_donnation,container,false);
        nameMealText = root.findViewById(R.id.nom);
        descriptionText = root.findViewById(R.id.descriptionID);
        categorieText = root.findViewById(R.id.categorie);
        nbPortionText = root.findViewById(R.id.nbPortionsID);
        imageView = root.findViewById(R.id.image_view_id);
        addMealButton = root.findViewById(R.id.donnationID);
        takePictureButton = root.findViewById(R.id.takePictureId);

        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.CAMERA
            },REQUEST_IMAGE_CAPTURE);
        }

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getActivity();
                PackageManager packageManager = context == null ? null : context.getPackageManager();
                if (packageManager == null || !packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                    Toast.makeText(getContext(),getResources().getText(R.string.camera_error),Toast.LENGTH_LONG).show();
                } else {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        addMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean areAllFiledsChecked = isFormValid();
                if(areAllFiledsChecked){
                    boolean isMealAdded = addMeal();
                    if(isMealAdded) {
                        goToMainActivity();
                    }
                }
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_IMAGE_CAPTURE ){
            Bundle bundle = data.getExtras();
            Bitmap imageCaptured = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(imageCaptured);
        }
    }


    private boolean isFormValid(){
        if(nameMealText.getText().length() == 0){
            nameMealText.setError(getResources().getText(R.string.meal_name_error));
            return false;
        }
        if(descriptionText.getText().length() == 0){
            descriptionText.setError(getResources().getText(R.string.meal_description_error));
            return false;
        }
        if(nbPortionText.getText().length() == 0){
            nbPortionText.setError(getResources().getText(R.string.nbPortions_error));
            return false;
        }
        return true;
    }

    public boolean addMeal(){
        //JWT
        sharedPreferences = requireActivity().getSharedPreferences(getString(R.string.sharedPref),Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(getString(R.string.token),"");
        DecodedJWT decodedJWT = JWT.decode(token);
        Claim jwtPayload = decodedJWT.getClaim("value");
        JwtTokenPayloadDTO JwtTokenPayloadDTO = jwtPayload.as(JwtTokenPayloadDTO.class);
        JwtTokenPayload jwtTokenPayload = TokenMapper.INSTANCE.mapToJwtTokenPayload(JwtTokenPayloadDTO);

        //meal data
        String name = nameMealText.getText().toString();
        String description = descriptionText.getText().toString();
        Integer portionNumber = Integer.parseInt(nbPortionText.getText().toString());
        Integer userId = jwtTokenPayload.getId();
        Integer categorieId = Integer.parseInt(categorieText.getText().toString());
        byte[] imageInByte = null;
        if(imageView.getDrawable() != null){
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imageInByte = baos.toByteArray();
        }

        //FormData
        HashMap<String, MultipartBody.Part> partMap = new HashMap<>();
        MultipartBody.Part namePart = MultipartBody.Part.createFormData("name", null, RequestBody.create(MediaType.parse("text/plain"), name));
        MultipartBody.Part descriptionPart = MultipartBody.Part.createFormData("description", null, RequestBody.create(MediaType.parse("text/plain"), description));
        MultipartBody.Part portionNumberPart = MultipartBody.Part.createFormData("portion_number", null, RequestBody.create(MediaType.parse("text/plain"), String.valueOf(portionNumber)));
        MultipartBody.Part userFkPart = MultipartBody.Part.createFormData("user_fk", null, RequestBody.create(MediaType.parse("text/plain"), String.valueOf(userId)));
        MultipartBody.Part categoryFkPart = MultipartBody.Part.createFormData("category_fk", null, RequestBody.create(MediaType.parse("text/plain"), String.valueOf(categorieId)));
        MultipartBody.Part filePart = null;
        if(imageInByte != null){
            filePart = MultipartBody.Part.createFormData("image", "image", RequestBody.create(MediaType.parse("image/*"), imageInByte));
        }
        if(filePart != null){
            donnationViewModel.addNewMeal(namePart,descriptionPart,portionNumberPart,userFkPart,categoryFkPart, filePart, token);
            return true;
        }else{
            Toast.makeText(getContext(),R.string.imageRequired,Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private void goToMainActivity(){
        Intent intent = new Intent(requireActivity().getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}