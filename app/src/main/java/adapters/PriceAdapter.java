package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myfirstapp.Library;
import com.example.myfirstapp.MainCyber;
import com.example.myfirstapp.Payment_methode;
import com.example.myfirstapp.R;

import java.util.ArrayList;

import models.Price;

public class PriceAdapter extends BaseAdapter {

    Context context;
    ArrayList<Price> priceArrayList = new ArrayList<>();
    LayoutInflater inflater;

    public PriceAdapter(Context context, ArrayList<Price> priceArrayList) {
        this.priceArrayList = priceArrayList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return priceArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return priceArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return priceArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View root = inflater.inflate(R.layout.price_products, null);

        ImageView os_img = root.findViewById(R.id.circle_image1);
        TextView os_name = root.findViewById(R.id.os_name_tv);
        RatingBar rating1 = root.findViewById(R.id.rate1_bar);
        TextView os_price = root.findViewById(R.id.price1_tv);

        Button price_but = root.findViewById(R.id.but_price1);
        TextView price_cancel = root.findViewById(R.id.list_cancel);
        Button price_course = root.findViewById(R.id.buy_course);

        Price item = priceArrayList.get(position);


        os_img.setImageResource(item.getImg());

        os_name.setText(item.getName());
        rating1.setRating(item.getRating());
        os_price.setText(String.valueOf(item.getPrice()));

        price_but.setOnClickListener(v -> {
            Intent goPay = new Intent(context, Payment_methode.class);
            context.startActivity(goPay);
        });

        price_cancel.setOnClickListener(v -> {
            Intent cancel = new Intent(context, Library.class);
            context.startActivity(cancel);
        });

        price_course.setOnClickListener(v -> {
            Intent i = new Intent(context, MainCyber.class);

            i.putExtra("title", item.getName());
            i.putExtra("price", item.getPrice());
            i.putExtra("rating", item.getRating());
            i.putExtra("imageRes", item.getImg()); //   للـ Fragment

            context.startActivity(i);
        });

        return root;
    }
}
