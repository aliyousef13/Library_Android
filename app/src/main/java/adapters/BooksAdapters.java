package adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.BookDetailsActivity;
import com.example.myfirstapp.R;

import java.util.ArrayList;

import models.AllBooks;

public class BooksAdapters extends RecyclerView.Adapter<BooksAdapters.Holder> {

    private final Context context;
    private ArrayList<AllBooks> booklist; // قائمة الكتب اللي بدنا نعرضها.


    public BooksAdapters(Context context, ArrayList<AllBooks> list) {  // يستقبل القائمة من Activity.
        this.context = context;                                        // إذا القائمة null يعمل قائمة فاضية عشان ما يصير crash.
        this.booklist = (list != null) ? list : new ArrayList<>();
    }

    @NonNull
    @Override


    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder h, int position) {
        AllBooks item = booklist.get(position);
        h.img.setImageResource(item.getImg());
        h.name.setText(item.getName());

        // فتح صفحة تفاصيل
        h.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, BookDetailsActivity.class);
            i.putExtra("book_name", item.getName());
            i.putExtra("book_img", item.getImg());

            if (!(context instanceof Activity)) i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }

    public void setBooklist(ArrayList<AllBooks> newList) {
        this.booklist = (newList != null) ? newList : new ArrayList<>();
        notifyDataSetChanged();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgBook);
            name = itemView.findViewById(R.id.tvName);
        }
    }
}
