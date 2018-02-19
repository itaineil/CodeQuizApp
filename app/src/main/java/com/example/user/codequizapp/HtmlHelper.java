package com.example.user.codequizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 9/14/2017.
 */

public class HtmlHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_HTML = "html";
    private static final String TABLE_HTML = "table_html";
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";

    private static final String KEY_OPTA = "opta";

    private static final String KEY_OPTB = "optb";

    private static final String KEY_OPTC = "optc";

    private SQLiteDatabase dbase;

    public HtmlHelper(Context context) {
        super(context, DATABASE_HTML, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        dbase = db;

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_HTML + " ( "

                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES

                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "

                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";

        db.execSQL(sql);

        addQuestion();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HTML);

        onCreate(db);
    }


    private void addQuestion() {
        // TODO Auto-generated method stub

        Question q1 = new Question("What is html", "Hyper Text Markup Language", "Hyper Test Markup Languages", "Hydro Text MarkUp Language", "Hyper Text Markup Language");
        this.addQuestion(q1);
        Question q2 = new Question("Which one is a opening tag",
                "<>", "<tag>", "</>", "<>");
        this.addQuestion(q2);
        Question q3 = new Question("Which is a closing tag",
                "<>", "</>", "<///>", "</>");
        this.addQuestion(q3);
        Question q4 = new Question("Which one is a paragraph",
                "<p>", "<li>",
                "<strong>",
                "<p>");
        this.addQuestion(q4);
        Question q5 = new Question("What goes into the head tag",
                "The title tags", "The body tags",
                "The information tags ", "The title tags");
        this.addQuestion(q5);
        Question q6 = new Question("Which tag is the meaning of this tag <th>", "table row",
                "table header", "table row",
                "table header");
        this.addQuestion(q6);
        Question q7 = new Question("What tag is this <ul>",
                "unidentified list", "unordered list", "unmanagable list", "unordered list");
        this.addQuestion(q7);
        Question q8 = new Question("What tag is this <ol>"
                , "open list", "ordered list",
                "octave list", "ordered list");
        this.addQuestion(q8);
        Question q9 = new Question("Which of the following goes into the body tags",
                "form tags", "head tags",
                "list tags", "form tags");
        this.addQuestion(q9);

        Question q10 = new Question("What does this tag go <input type = 'text'",
                "In the title", "In the form tags", "In the head tags",
                "In the form tags");
        this.addQuestion(q10);

        Question q11 = new Question("What does this tag do <strong>",
                "Italize text", "Bolden Text", "Underline Text", "Bolden Text");
        this.addQuestion(q11);
        Question q12 = new Question("What does this tag do <u>",
                "unmark text", "underline text", "unappend text", "underline text");
        this.addQuestion(q12);
        Question q13 = new Question("Which tag is at the top of the" + "page",
                "<html>", "<head>", "<title>", "<html>");
        this.addQuestion(q13);
        Question q14 = new Question("Which tag is a paragraph tag",
                "<p>", "<li>",
                "<ul>",
                "<p>");
        this.addQuestion(q14);
        Question q15 = new Question("Which one is a list",
                "<li>", "<strong>",
                "<head>", "<li>");
        this.addQuestion(q15);
        Question q16 = new Question("What does this tag mean <b>"
                , "Bold",
                "Italic", "Underline",
                "Bold");
        this.addQuestion(q16);
        Question q17 = new Question(
                "Which tag closes the form ", "<f>",
                "</form>", "<//form>", "</form>");
        this.addQuestion(q17);
        Question q18 = new Question("Which one is a unordered list", "</ul>", "<ol>",
                "<uli>", "<ul>");
        this.addQuestion(q18);
        Question q19 = new Question("Which one is a h1 tag",

                "<h1>", "<hh1>",
                "<hd>", "<h1>");
        this.addQuestion(q19);

        Question q20 = new Question("What tag is in the body tags ",
                "<form>", "<body>", "<head>",
                "<form>");
        this.addQuestion(q20);
    }


    public void addQuestion(Question quest) {

        ContentValues val = returnContentValues(quest);

        dbase.insert(TABLE_HTML, null, val);

    }

    private ContentValues returnContentValues(Question quest) {
        // TODO Auto-generated method stub
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());

        values.put(KEY_ANSWER, quest.getANSWER());

        values.put(KEY_OPTA, quest.getOPTA());

        values.put(KEY_OPTB, quest.getOPTB());

        values.put(KEY_OPTC, quest.getOPTC());

        return values;
    }

    public List<Question> getAllQuestions() {

        List<Question> quesList = new ArrayList<Question>();

        String selectQuery = "SELECT  * FROM " + TABLE_HTML;

        dbase = this.getReadableDatabase();

        Cursor cursor = dbase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                Question question = returnQuestionObject(cursor);

                quesList.add(question);

            } while (cursor.moveToNext());

        }

        return quesList;

    }

    private Question returnQuestionObject(Cursor cursor) {
        // TODO Auto-generated method stub

        Question quest = new Question();
        quest.setID(cursor.getInt(0));

        quest.setQUESTION(cursor.getString(1));

        quest.setANSWER(cursor.getString(2));

        quest.setOPTA(cursor.getString(3));

        quest.setOPTB(cursor.getString(4));

        quest.setOPTC(cursor.getString(5));

        return quest;

    }

}
