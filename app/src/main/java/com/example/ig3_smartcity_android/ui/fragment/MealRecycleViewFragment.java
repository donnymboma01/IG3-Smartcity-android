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
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ig3_smartcity_android.R;
import com.example.ig3_smartcity_android.model.MealToReceive;
import com.example.ig3_smartcity_android.repositories.configuration.RetrofitConfigurationService;
import com.example.ig3_smartcity_android.ui.actitvity.MealDescription;
import com.example.ig3_smartcity_android.ui.viewModel.MealViewModel;
import org.jetbrains.annotations.NotNull;
import java.util.List;


public class MealRecycleViewFragment extends Fragment {

    private static Fragment fragment;
    private MealAdapter mealAdapter;
    private MealViewModel viewModel;
    private SharedPreferences sharedPreferences;


    public MealRecycleViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_meal_recycle_view,container,false);
        RecyclerView mealRecycleView = root.findViewById(R.id.mealRecyclerView);

        //récupère la valeur du token dans le sharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(getString(R.string.sharedPref),Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(getString(R.string.token),"");

        mealRecycleView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        viewModel = new ViewModelProvider(this).get(MealViewModel.class);

        //le token généré lors de la connexion de l'utilisateur.
        viewModel.getAllMeals(token);
        mealAdapter = new MealAdapter(getActivity());
        viewModel.getMealToReceive().observe(getViewLifecycleOwner(),mealAdapter::setMeals);

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

        private List<MealToReceive> mealToReceives;
        private Context context;

        public MealAdapter(Context context){
            this.context = context;
        }

        @NonNull
        @Override
        public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item_layout,parent,false);
            return new MealViewHolder(view, position->{
                MealToReceive touchedMealToReceive = mealToReceives.get(position);
                Intent intent = new Intent(fragment.getActivity(), MealDescription.class);
                intent.putExtra("name", touchedMealToReceive.getName());
                intent.putExtra("description", touchedMealToReceive.getDescription());
                intent.putExtra("image", touchedMealToReceive.getImage());
                intent.putExtra("portion_number", touchedMealToReceive.getPortion_number());
                fragment.requireActivity().startActivity(intent);
            });
        }

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
            holder.portion_number.setText("Quantité : "+portionNumber.toString()); //TODO: supprimer string hardcodé


            //placeholder(R.drawable.bicky) -> image par defaut si jamais les images de l'api ne veulent pas charger.
            Glide.with(fragment)
                    .load(RetrofitConfigurationService.BASE_URL + "mealimages/" + mealUri)
                    .into(holder.image);
        }

        @Override
        public int getItemCount() {
            return mealToReceives == null ? 0: mealToReceives.size();
        }

        public void setMeals(List<MealToReceive> mealToReceives){
            this.mealToReceives = mealToReceives;
            System.out.println(mealToReceives);
            notifyDataSetChanged();
        }
    }
}