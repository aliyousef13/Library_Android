package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;

import java.util.ArrayList;

import models.AllBooks;

public class BooksAdapters extends RecyclerView.Adapter<BooksAdapters.Holder> {

    private final Context context;
    private ArrayList<AllBooks> booklist;   //

    public BooksAdapters(Context context, ArrayList<AllBooks> list) {
        this.context = context;
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

        // إذا بدك لاحقاً تضيف Intent على click:
        // h.itemView.setOnClickListener(v -> { ... });
    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }

    // ✅ اختياري: تحديث القائمة لاحقاً
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
