package com.example.ig3_smartcity_android.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bumptech.glide.Glide;
import com.example.ig3_smartcity_android.R;
import com.example.ig3_smartcity_android.dataAccess.configuration.RetrofitConfigurationService;
import com.example.ig3_smartcity_android.dataAccess.dto.JwtTokenPayloadDTO;
import com.example.ig3_smartcity_android.model.JwtTokenPayload;
import com.example.ig3_smartcity_android.model.MealToReceive;
import com.example.ig3_smartcity_android.model.Order;
import com.example.ig3_smartcity_android.model.User;
import com.example.ig3_smartcity_android.services.mappers.TokenMapper;
import com.example.ig3_smartcity_android.ui.actitvity.LoginActivity;
import com.example.ig3_smartcity_android.ui.actitvity.MainActivity;
import com.example.ig3_smartcity_android.ui.error.ApiError;
import com.example.ig3_smartcity_android.ui.viewModel.CartViewModel;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CartRecyclerViewFragment extends Fragment {
    private static Fragment fragment;
    private MealAdapter mealAdapter;
    private CartViewModel cartViewModel;
    private SharedPreferences sharedPreferences;
    private String token;

    private Button claimButton;

    public CartRecyclerViewFragment(){

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart,container,false);
        claimButton = root.findViewById(R.id.claimButton);
        RecyclerView mealRecycleView = root.findViewById(R.id.mealRecyclerView);
        mealRecycleView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getError().observe(getViewLifecycleOwner(), error -> {
            ApiError.showError(error, getContext());
        });

        sharedPreferences = requireActivity().getSharedPreferences(getString(R.string.sharedPref),Context.MODE_PRIVATE);
        token = sharedPreferences.getString(getString(R.string.token),null);

        mealAdapter = new MealAdapter(getActivity());
        mealAdapter.setMeals(MainActivity.getMealsForCart());
        mealRecycleView.setAdapter(mealAdapter);

        claimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });

        fragment = this;
        return root;
    }

    //View  Holder.
    private static class MealViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView mealName;
        TextView description;
        TextView portion_number;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.mealImageId);
            mealName = itemView.findViewById(R.id.mealNameID);
            description = itemView.findViewById(R.id.mealDescription);
            portion_number = itemView.findViewById(R.id.portionNumber);
        }
    }

    //Adapter.
    private static class MealAdapter extends RecyclerView.Adapter<MealViewHolder>{

        private ArrayList<MealToReceive> mealToReceives;
        private Context context;

        public MealAdapter(Context context){
            this.context = context;
        }

        @NonNull
        @Override
        public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item_layout,parent,false);
            return new MealViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
            MealToReceive mealToReceive = mealToReceives.get(position);
            String name  = mealToReceive.getName();
            String description = mealToReceive.getDescription();
            String mealImage = mealToReceive.getImage();
            Uri mealUri = Uri.parse(mealImage);
            Integer portionNumber = mealToReceive.getPortion_number();

            holder.mealName.setText(name);
            holder.description.setText(description);
            holder.portion_number.setText(context.getResources().getString(R.string.quantity) + " " + portionNumber.toString());



            Glide.with(fragment)
                    .load(RetrofitConfigurationService.BASE_URL + "mealimages/" + mealUri)
                    .placeholder(R.drawable.bicky)// -> image par defaut si jamais les images de l'api ne veulent pas charger.
                    .into(holder.image);
        }

        @Override
        public int getItemCount() {
            return mealToReceives == null ? 0: mealToReceives.size();
        }

        public void setMeals(ArrayList<MealToReceive> mealToReceives){
            this.mealToReceives = mealToReceives;
            notifyDataSetChanged();
        }
    }

    private void placeOrder(){
        //JWT
        if(token != null){
            DecodedJWT decodedJWT = JWT.decode(token);
            Claim jwtPayload = decodedJWT.getClaim("value");
            JwtTokenPayloadDTO JwtTokenPayloadDTO = jwtPayload.as(JwtTokenPayloadDTO.class);
            JwtTokenPayload jwtTokenPayload = TokenMapper.INSTANCE.mapToJwtTokenPayload(JwtTokenPayloadDTO);

            //meals
            ArrayList<MealToReceive> meals = MainActivity.getMealsForCart();
            if(meals != null){
                if(meals.size() > 0){
                    User userWhoPlaceOrder = new User(jwtTokenPayload.getId(),null,null,null,null,null,null,null,null);
                    Order order = new Order(null, userWhoPlaceOrder, meals);
                    Gson gson = new Gson();
                    String Orderjson = gson.toJson(order);
                    RequestBody body = RequestBody.create(MediaType.parse("application/json"), Orderjson);
                    cartViewModel.createOrder(body, token);
                    MainActivity.resetCart();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(),R.string.emptyCart,Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}
