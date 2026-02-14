package com.example.myfirstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

import adapters.BooksAdapters;
import api.AppController;
import models.AllBooks;

public class RecycelerViewBooks extends AppCompatActivity {

    RecyclerView recyclerBooks;
    BooksAdapters adapter;
    ArrayList<AllBooks> booklist;

    TextView tvTitle, tvLogout;
    Button btn_LookBooks, btnBack;

    // ✅ عشان ما يتكرر نفس العنوان بين الأقسام
    private final HashSet<String> usedTitles = new HashSet<>();
    private final ArrayList<AllBooks> apiResult = new ArrayList<>();

    // ✅ 9 أقسام = 9 صور ثابتة + 9 Queries
    private static class Topic {
        String query;
        int drawable;
        String fallbackName;     // لو API ما رجّع اسم مناسب
        String[] mustContain;    // كلمات لازم تكون بالعنوان (تقريباً)

        Topic(String query, int drawable, String fallbackName, String[] mustContain) {
            this.query = query;
            this.drawable = drawable;
            this.fallbackName = fallbackName;
            this.mustContain = mustContain;
        }
    }

    private final Topic[] TOPICS = new Topic[]{
            new Topic("operating system", R.drawable.operating, "Operating System", new String[]{"operating", "system"}),
            new Topic("information security", R.drawable.libraryinformation, "Information Security", new String[]{"information", "security"}),
            new Topic("web security", R.drawable.libraryweb, "Web Security", new String[]{"web", "security"}),
            new Topic("network security", R.drawable.network, "Network Security", new String[]{"network", "security"}),
            new Topic("malware analysis", R.drawable.malware, "Malware Analysis", new String[]{"malware", "analysis"}),
            new Topic("digital forensics", R.drawable.digital, "Digital Forensics", new String[]{"digital", "forensic"}),
            new Topic("cloud security", R.drawable.cloud, "Cloud Security", new String[]{"cloud", "security"}),
            new Topic("ethical hacking", R.drawable.hacking, "Ethical Hacking", new String[]{"hacking", "ethical"}),
            new Topic("database security", R.drawable.database, "Database Security", new String[]{"database", "security"})
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recyceler_view_books);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerBooks = findViewById(R.id.recyclerBooks);
        btn_LookBooks = findViewById(R.id.btn_LookBooks);
        btnBack = findViewById(R.id.btnBackRegister);
        tvTitle = findViewById(R.id.tvTitle);
        tvLogout = findViewById(R.id.tvLogout);

        recyclerBooks.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerBooks.setHasFixedSize(true);

        tvLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(RecycelerViewBooks.this, EmailPasswordActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        });

        tvTitle.setOnClickListener(v ->
                startActivity(new Intent(RecycelerViewBooks.this, AddEditBookActivity.class))
        );

        // ✅ adapter فاضي بالبداية
        booklist = new ArrayList<>();
        adapter = new BooksAdapters(this, booklist);
        recyclerBooks.setAdapter(adapter);

        // ✅ حمّل من API (9 أسماء مختلفة)
        loadBooksFromApiStepByStep(0);

        btn_LookBooks.setOnClickListener(v -> {
            startActivity(new Intent(RecycelerViewBooks.this, CategoriesDetail.class));
            finish();
        });

        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(RecycelerViewBooks.this, EmailPasswordActivity.class));
            finish();
        });
    }

    // ✅ نجيب عنوان واحد لكل Topic بالترتيب (بدون تكرار)
    private void loadBooksFromApiStepByStep(int index) {
        if (index == 0) {
            usedTitles.clear();
            apiResult.clear();
        }

        if (index >= TOPICS.length) {
            adapter.setBooklist(new ArrayList<>(apiResult));
            return;
        }

        Topic topic = TOPICS[index];
        String url = buildUrl(topic.query);

        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    String title = pickUniqueTitle(response.optJSONArray("docs"), topic.mustContain);

                    if (title == null || title.trim().isEmpty()) {
                        title = topic.fallbackName;
                    }

                    // ✅ الصورة ثابتة حسب التصنيف
                    apiResult.add(new AllBooks(index + 1, title, topic.drawable));

                    // ✅ تابع لللي بعده
                    loadBooksFromApiStepByStep(index + 1);
                },
                error -> {
                    // ✅ لو فشل API: حط fallback لهذا التصنيف فقط
                    apiResult.add(new AllBooks(index + 1, topic.fallbackName, topic.drawable));
                    loadBooksFromApiStepByStep(index + 1);
                }
        );

        AppController.getInstance().addToRequestQueue(req, "books_api");
    }

    private String buildUrl(String q) {
        Uri uri = Uri.parse(AppController.link).buildUpon()
                .appendQueryParameter("q", q)
                .appendQueryParameter("fields", "title")
                .appendQueryParameter("limit", "25")
                .build();
        return uri.toString();
    }

    // ✅ اختار أول عنوان:
    // 1) يحتوي كلمات من mustContain قدر الإمكان
    // 2) مش مكرر عالمستوى العام
    private String pickUniqueTitle(JSONArray docs, String[] mustContain) {
        if (docs == null) return null;

        // 1) جرّب عنوان يطابق الكلمات
        for (int i = 0; i < docs.length(); i++) {
            JSONObject obj = docs.optJSONObject(i);
            if (obj == null) continue;

            String title = obj.optString("title", "").trim();
            if (title.isEmpty()) continue;

            if (!containsAny(title, mustContain)) continue;

            String key = normalize(title);
            if (usedTitles.add(key)) return title;
        }

        // 2) لو ما لقى، خذ أي عنوان غير مكرر
        for (int i = 0; i < docs.length(); i++) {
            JSONObject obj = docs.optJSONObject(i);
            if (obj == null) continue;

            String title = obj.optString("title", "").trim();
            if (title.isEmpty()) continue;

            String key = normalize(title);
            if (usedTitles.add(key)) return title;
        }

        return null;
    }

    private boolean containsAny(String title, String[] mustContain) {
        if (mustContain == null || mustContain.length == 0) return true;

        String t = title.toLowerCase();
        for (String w : mustContain) {
            if (w != null && !w.isEmpty() && t.contains(w.toLowerCase())) return true;
        }
        return false;
    }

    private String normalize(String s) {
        return s.toLowerCase().replaceAll("[^a-z0-9]+", "");
    }
}
