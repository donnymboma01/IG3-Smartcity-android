package com.example.ig3_smartcity_android.ui.fragment;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import com.example.ig3_smartcity_android.R;
import com.example.ig3_smartcity_android.model.Category;
import com.example.ig3_smartcity_android.model.Meal;
import com.example.ig3_smartcity_android.model.User;
import com.example.ig3_smartcity_android.ui.viewModel.MealViewModel;
import org.jetbrains.annotations.NotNull;
import java.util.Date;
import java.util.List;


public class MealRecycleViewFragment extends Fragment {

    private static Fragment fragment;
    private MealAdapter mealAdapter;
    private MealViewModel viewModel;


    public MealRecycleViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater,@NotNull ViewGroup container,@NotNull
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_meal_recycle_view,container,false);
        RecyclerView mealRecycleView = root.findViewById(R.id.mealRecyclerView);
        mealRecycleView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        viewModel = new ViewModelProvider(this).get(MealViewModel.class);
        viewModel.getAllMeals();
        mealAdapter = new MealAdapter(getActivity());
       // viewModel.getMeal().observe(getViewLifecycleOwner(),mealAdapter::setMeals);

        mealRecycleView.setAdapter(mealAdapter);
        fragment = this;
        return root;
    }


    //View  Holder.
    private static class MealViewHolder extends RecyclerView.ViewHolder{
        TextView mealName;
        TextView description;
        TextView price;
        TextView publication_date;
        TextView isAvailable;
        TextView user;
        TextView category;

        public MealViewHolder(@NonNull View itemView, AdapterView.OnItemSelectedListener listener) {
            super(itemView);
            mealName = itemView.findViewById(R.id.mealName);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            publication_date = itemView.findViewById(R.id.publication_date);
            isAvailable = itemView.findViewById(R.id.isAvailable);
            user = itemView.findViewById(R.id.user);
            category = itemView.findViewById(R.id.category);

            itemView.setOnClickListener(e->{
                int currentMealPosition = getAbsoluteAdapterPosition(); //getAdaptaterPosition() n'est plus disponible.
                //listener.onItemSelected(currentMealPosition); --> je mets bien un entier mais...error wtf?
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
            MealViewHolder vh = new MealViewHolder(view,position->{
                Meal touchedMeal = meals.get(position);
                //Intent intent = new Intent(fragment.getActivity(),)
            });
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
            Meal meal = meals.get(position);
            String name  = meal.getName();
            String description = meal.getDescription();
            Float price = meal.getPrice();
            Date publication_date = meal.getPublication_date();
            User user = meal.getUser();
            Category category = meal.getCategory();

            holder.mealName.setText(name);
            holder.description.setText(description);
            //holder.price.setText(price);

        }

        @Override
        public int getItemCount() {
            return meals == null?0:meals.size();
        }

        public void setMeals(List<Meal> meals){
            this.meals = meals;
            notifyDataSetChanged();
        }
    }
}