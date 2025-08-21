package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myfirstapp.ListView_Price;
import com.example.myfirstapp.Payment_methode;
import com.example.myfirstapp.R;

public class Cyber_Fragment extends Fragment {

    public Cyber_Fragment() { /* Required empty public constructor */ }

    public static Cyber_Fragment newInstance(String param1, String param2) {
        Cyber_Fragment fragment = new Cyber_Fragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cyber_, container, false);


        Button buyCourse   = root.findViewById(R.id.buy_course);
        TextView backBtn   = root.findViewById(R.id.list_cancel);
        TextView tvTitle   = root.findViewById(R.id.tvTitle);
        TextView tvPrice   = root.findViewById(R.id.tvPrice);
        RatingBar rating   = root.findViewById(R.id.ratingBar);
        ImageView imgCourse= root.findViewById(R.id.imgCourse);






        Bundle args = getArguments();
        if (args != null) {
            if (tvTitle != null) tvTitle.setText(args.getString("title", "Course Title"));
            if (tvPrice != null) tvPrice.setText(((int) args.getFloat("price", 0)) + " SAR");
            if (rating != null)  rating.setRating(args.getFloat("rating", 4f));
            int res = args.getInt("imageRes", 0);
            if (imgCourse != null && res != 0) imgCourse.setImageResource(res);
        }

        if (buyCourse != null) {
            buyCourse.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent i = new Intent(requireActivity(), Payment_methode.class);
                    startActivity(i);
                }
            });
        }

        if (backBtn != null) {
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent bac = new Intent(requireActivity(), ListView_Price.class);
                    startActivity(bac);



                }
            });
        }

        return root;
}
}