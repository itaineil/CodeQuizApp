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

public class JavaHelper extends SQLiteOpenHelper{


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_JAVA = "java";
    private static final String TABLE_JAVA = "table_java";
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";

    private static final String KEY_OPTA = "opta";

    private static final String KEY_OPTB = "optb";

    private static final String KEY_OPTC = "optc";

    private SQLiteDatabase dbase;

    public JavaHelper(Context context) {
        super(context, DATABASE_JAVA, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        dbase = db;

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_JAVA+ " ( "

                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES

                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "

                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";

        db.execSQL(sql);

        addQuestion();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JAVA);

        onCreate(db);
    }


    private void addQuestion() {
        // TODO Auto-generated method stub

        Question q1 = new Question("What is a Class", "An Instance of a Object", "A blueprint for creating objects", "A variable", "A blueprint for creating objects");
        this.addQuestion(q1);
        Question q2 = new Question("Which one is a data type in Java",
                "Static", "Return", "Int", "Int");
        this.addQuestion(q2);
        Question q3 = new Question("Which is not a return type",
                "Boolean", "Integer", "Public", "Public");
        this.addQuestion(q3);
        Question q4 = new Question("How is a main method written in Java",
                "public void main(Args)", "public String main",
                "public static void main(String[]Args)",
                "public static void main(String[]Args)");
        this.addQuestion(q4);
        Question q5 = new Question("What is a variable",
                "A memory location", "A pointer to an object",
                "A static variable ", "A memory location");
        this.addQuestion(q5);
        Question q6 = new Question("Which one is a string array", "String[]names = {'he','he'}",
                "int num = 1", "String = 'names'",
                "String[]names = {'he','he'}");
        this.addQuestion(q6);
        Question q7 = new Question("Which is not a collection",
                "HashMap", "LinkedList", "Array", "Array");
        this.addQuestion(q7);
        Question q8 = new Question("Which will legally declare,constuct and initialize an array"
                , "int [] myList = {'1','2','3'}", "int [] myList = (5,8)",
                "int myList[] = {4,3,7}", "int [] myList = {'1','2','3'}");
        this.addQuestion(q8);
        Question q9 = new Question("Which is a void method",
                "A method that does not return anything", "A method in a class",
                "A method with parameters", "A method that does not return anything");
        this.addQuestion(q9);

        Question q10 = new Question("Which is a reserved keyword in Java",
                "native", "subclasses", "references",
                "native");
        this.addQuestion(q10);

        Question q11 = new Question("Which is a valid keyword in Java",
                "String", "Float", "Interface", "Interface");
        this.addQuestion(q11);
        Question q12 = new Question("Which is the valid declarations within an interface definition",
                "public void methoda()", "public final double methoda()", "static void methoda(double d1)", "public void methoda()");
        this.addQuestion(q12);
        Question q13 = new Question("Which one is a valid declaration of a boolean",
                "boolean b3 = false", "boolean b5 = no", "boolean b1 = 0", "boolean b3 = false");
        this.addQuestion(q13);
        Question q14 = new Question("Which is a valid String declaration",
                "String s1 = null", "String s3 = (String)'abc'",
                "String s4 = (String)'e'",
                "String s1 = null");
        this.addQuestion(q14);
        Question q15 = new Question("Which is a Integer",
                "1", "14",
                "12.25", "1");
        this.addQuestion(q15);
        Question q16 = new Question("Which is a Boolean"
                , "true",
                "0", "35",
                "true");
        this.addQuestion(q16);
        Question q17 = new Question(
                "Under which situation do you obtain a default constructor", "When you define at least one constructor",
                "When you define any class", "When the class has no other constructors", "When the class has no other constructors");
        this.addQuestion(q17);
        Question q18 = new Question("Choose the name of the method to schedule a thread for execution", "start()", "run()",
                "do()", "start()");
        this.addQuestion(q18);
        Question q19 = new Question("Which Java Collection does not accept duplicate values",

                "LinkedList", "HashSet",
                "ArrayList", "HashSet");
        this.addQuestion(q19);

        Question q20 = new Question("What does a HashMap do",
                "Store duplicate values", "Store key value pairs", "Store integers",
                "Store key value pairs");
        this.addQuestion(q20);
    }


    public void addQuestion(Question quest) {

        ContentValues val = returnContentValues(quest);

        dbase.insert(TABLE_JAVA, null, val);

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

        String selectQuery = "SELECT  * FROM " + TABLE_JAVA;

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
