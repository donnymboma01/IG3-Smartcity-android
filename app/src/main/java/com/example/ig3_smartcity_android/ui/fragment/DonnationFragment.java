package com.example.ig3_smartcity_android.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
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

import com.example.ig3_smartcity_android.R;
import com.example.ig3_smartcity_android.databinding.FragmentDonnationBinding;
import com.example.ig3_smartcity_android.model.Meal;
import com.example.ig3_smartcity_android.ui.actitvity.RegistrationActivity;
import com.example.ig3_smartcity_android.ui.viewModel.DonnationViewModel;


public class DonnationFragment extends Fragment {

    private EditText nameMealText;
    private EditText descriptionText;
    private EditText categorieText;
    private EditText nbPortionText;
    private Button takePictureButton;
    private Button addMealButton;
    private ImageView imageView;
    private DonnationViewModel donnationViewModel;
    private FragmentDonnationBinding binding;

    private boolean areAllFiledsChecked = false;

    public DonnationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        donnationViewModel = new ViewModelProvider(this).get(DonnationViewModel.class);
        binding = FragmentDonnationBinding.inflate(inflater,container,false);
        binding.getViewModel();
        binding.getLifecycleOwner();
        /*View root  = inflater.inflate(R.layout.fragment_donnation,container,false);
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
            },100);
        }

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivityForResult(intent,100);
                }else{
                    Toast.makeText(getContext(),getResources().getText(R.string.camera_error),Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;*/
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 100 ){
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

    public void addMeal(){
        String portion = nbPortionText.getText().toString();
        Integer nbPortion = Integer.parseInt(portion);
        Meal meal = new Meal(nameMealText.getText().toString(),descriptionText.getText().toString(),imageView.toString(),nbPortion);
        donnationViewModel.addNewMeal(meal);

    }

}