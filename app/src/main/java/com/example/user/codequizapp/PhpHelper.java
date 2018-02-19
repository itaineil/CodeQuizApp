package com.example.user.codequizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
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






    /**
     * Created by USER on 9/14/2017.
     */

    public class PhpHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_PHP = "php";
        private static final String TABLE_PHP= "table_php";
        private static final String KEY_ID = "qid";
        private static final String KEY_QUES = "question";
        private static final String KEY_ANSWER = "answer";

        private static final String KEY_OPTA = "opta";

        private static final String KEY_OPTB = "optb";

        private static final String KEY_OPTC = "optc";

        private SQLiteDatabase dbase;

        public PhpHelper(Context context) {
            super(context, DATABASE_PHP, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            dbase = db;

            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_PHP + " ( "

                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES

                    + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "

                    + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";

            db.execSQL(sql);

            addQuestion();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHP);

            onCreate(db);
        }


        private void addQuestion() {
            // TODO Auto-generated method stub

            Question q1 = new Question("What does Php stand for", "Hypertext Preprocessor", "Processor Home Page", "Pretext Hypertext Processor", "Hypertext Preprocessor");
            this.addQuestion(q1);
            Question q2 = new Question("PHP files have a default extension of",
                    ".html", ".php", ".xml", ".php");
            this.addQuestion(q2);
            Question q3 = new Question("A php script starts with_and ends with_",
                    "< php >", "<p><p/>", "<? php ?>", "<? php ?>");
            this.addQuestion(q3);
            Question q4 = new Question("Which can use _ to comment a single",
                    "/?", "//",
                    "#",
                    "//");
            this.addQuestion(q4);
            Question q5 = new Question("Which stores a value of 111 in num?",
                    "$num = 111", "int $num = 111",
                    "int mum = 111", "$num = 111");
            this.addQuestion(q5);
            Question q6 = new Question("PHP recognizes constructors by the name", "classname()",
                    "_construct", "function_construct()",
                    "function_construct()");
            this.addQuestion(q6);
            Question q7 = new Question("Which keyword is used to inhert a subclass into a superclass",
                    "implements", "extends", "inherit", "extends");
            this.addQuestion(q7);
            Question q8 = new Question("Which keyword is used to refer to properties or methods within the class itself"
                    , "private", "$this",
                    "protected", "$this");
            this.addQuestion(q8);
            Question q9 = new Question("Which function will add a value to the end of an array",
                    "array_push()", "array_unshift()",
                    "into_array()", "array_push()");
            this.addQuestion(q9);

            Question q10 = new Question("Which function sorts in descending order",
                    "sort()", "rsort()", "ksort()",
                    "rsort()");
            this.addQuestion(q10);

            Question q11 = new Question("Which of the following statements invoke the exception class",
                    "throws new Exception()", "new Exception()", "new throws Exception()", "new throws Exception()");
            this.addQuestion(q11);
            Question q12 = new Question("How do you define a function in PHP",
                    "function{function body}", "data type functionName(parameters){function body}", "function functionName(parameters){function body}", "function functionName(parameters){function body}");
            this.addQuestion(q12);
            Question q13 = new Question("Whch of the following are valid function names",
                    "$function()", "function()", ".function()", "function()");
            this.addQuestion(q13);
            Question q14 = new Question("Which of the following properties is not supported by PHP",
                    "friendly", "public",
                    "final",
                    "friendly");
            this.addQuestion(q14);
            Question q15 = new Question("Which one of the following can be used to instantiate an object in PHP assuming class name is Foo",
                    "$obj = new $foo", "$obj = new foo",
                    "$obj = new foo()", "$obj = new foo()");
            this.addQuestion(q15);
            Question q16 = new Question("What one of the following is the right way to define a constant"
                    , "const PI = '3.1415'",
                    "constant PI = '3.1415'", "const $PI = '3.1415'",
                    "const PI = '3.1415'");
            this.addQuestion(q16);
            Question q17 = new Question(
                    "Which is the right way to invoke a method", "object->methodName()",
                    "$object->methodName()", "$object::methodName()", "$object->methodName()");
            this.addQuestion(q17);
            Question q18 = new Question("Which one is used to start a session", "start_session()", "session_start()",
                    "begin_session()", "session_start()");
            this.addQuestion(q18);
            Question q19 = new Question("Which one converts string to uppercase",

                    "strtoupper()", "uppercase()",
                    "struppercase()", "strtoupper()");
            this.addQuestion(q19);

            Question q20 = new Question("Does PHP have data types",
                    "yes", "no", "maybe",
                    "no");
            this.addQuestion(q20);
        }


        public void addQuestion(Question quest) {

            ContentValues val = returnContentValues(quest);

            dbase.insert(TABLE_PHP, null, val);

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

            String selectQuery = "SELECT  * FROM " + TABLE_PHP;

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


