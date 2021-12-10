package com.example.ig3_smartcity_android.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ig3_smartcity_android.R;
import com.example.ig3_smartcity_android.model.Category;
import com.example.ig3_smartcity_android.model.Meal;
import com.example.ig3_smartcity_android.model.Token;
import com.example.ig3_smartcity_android.model.User;
import com.example.ig3_smartcity_android.ui.actitvity.MealDescription;
import com.example.ig3_smartcity_android.ui.viewModel.MealViewModel;
import org.jetbrains.annotations.NotNull;
import java.util.Date;
import java.util.List;


public class MealRecycleViewFragment extends Fragment {

    private static Fragment fragment;
    private MealAdapter mealAdapter;
    private MealViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private User user;


    public MealRecycleViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater,@NotNull ViewGroup container,@NotNull
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_meal_recycle_view,container,false);
        RecyclerView mealRecycleView = root.findViewById(R.id.mealRecyclerView);

        //récupère la valeur du token dans le sharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(getString(R.string.sharedPref),Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(getString(R.string.token),"");
        Token jwtToken = new Token(token);

        mealRecycleView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        viewModel = new ViewModelProvider(this).get(MealViewModel.class);

        //le token généré lors de la connexion de l'utilisateur.
        viewModel.getAllMeals(jwtToken);
        mealAdapter = new MealAdapter(getActivity());
        viewModel.getMeal().observe(getViewLifecycleOwner(),mealAdapter::setMeals);

        mealRecycleView.setAdapter(mealAdapter);

        fragment = this;
        return root;
    }

    // interface qui fournit la méthode(void) de récuperation de l'indice de l'élément de la liste cliqué.
    public interface OnItemSelectedListener{
        void onItemSelected(int position);
    }

    //View  Holder.
    private static class MealViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView mealName;
        TextView description;
        TextView portion_number;


        public MealViewHolder(@NonNull View itemView, OnItemSelectedListener listener) {
            super(itemView);
            image = itemView.findViewById(R.id.mealImageId);
            mealName = itemView.findViewById(R.id.mealNameID);
            description = itemView.findViewById(R.id.mealDescription);
            portion_number = itemView.findViewById(R.id.portionNumber);

            itemView.setOnClickListener(e->{
                int currentMealPosition = getAbsoluteAdapterPosition();
                listener.onItemSelected(currentMealPosition);
            });
        }
    }


    //Adapter.
    private static class MealAdapter extends RecyclerView.Adapter<MealViewHolder>{

        private List<Meal> meals;
        private Context context;

        public MealAdapter(Context context){
            this.context = context;
        }

        @NonNull
        @Override
        public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item_layout,parent,false);
            MealViewHolder vh = new MealViewHolder(view, position->{
                Meal touchedMeal = meals.get(position);
                Intent intent = new Intent(fragment.getActivity(), MealDescription.class);
                intent.putExtra("name",touchedMeal.getName());
                intent.putExtra("description",touchedMeal.getDescription());
                intent.putExtra("image",touchedMeal.getImage());
                intent.putExtra("portion_number",touchedMeal.getPortion_number());
                fragment.getActivity().startActivity(intent);
            });
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
            Meal meal = meals.get(position);
            String name  = meal.getName();
            String description = meal.getDescription();
            String mealImage = meal.getImage();
            Uri mealUri = Uri.parse(mealImage);
            Integer portionNumber = meal.getPortion_number();
            //String publication_date = meal.getPublication_date();
            //User user = meal.getUser();
            //Category category = meal.getCategory();


            holder.mealName.setText(name);
            holder.description.setText(description);
            holder.portion_number.setText("Quantité : "+portionNumber.toString());

            Glide.with(fragment)
                    .load(mealUri)
                    .placeholder(R.drawable.bicky)
                    .into(holder.image);
        }

        @Override
        public int getItemCount() {
            return meals == null ? 0:meals.size();
        }

        public void setMeals(List<Meal> meals){
            this.meals = meals;
            System.out.println(meals);
            notifyDataSetChanged();
        }
    }
}