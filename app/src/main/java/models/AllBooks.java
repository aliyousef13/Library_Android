package models;

public class AllBooks {

    private int id;
    private String name;
    private int img;

    public static final String TABLE_BOOKS = "books";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_IMG = "img";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_BOOKS + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NAME + " TEXT NOT NULL UNIQUE, " +
                    COL_IMG + " INTEGER NOT NULL" +
                    ");";


    public AllBooks() {

    }

    public AllBooks(int id, String name, int img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getImg() { return img; }
    public void setImg(int img) { this.img = img; }
}
