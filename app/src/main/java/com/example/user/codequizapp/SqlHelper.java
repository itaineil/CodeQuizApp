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

public class SqlHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_SQL= "sql";
    private static final String TABLE_SQL= "table_sql";
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";

    private static final String KEY_OPTA = "opta";

    private static final String KEY_OPTB = "optb";

    private static final String KEY_OPTC = "optc";

    private SQLiteDatabase dbase;

    public SqlHelper(Context context) {
        super(context, DATABASE_SQL, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        dbase = db;

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_SQL + " ( "

                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES

                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "

                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";

        db.execSQL(sql);

        addQuestion();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQL);

        onCreate(db);
    }


    private void addQuestion() {
        // TODO Auto-generated method stub

        Question q1 = new Question("What is the full form of SQL", "Structured Query List", "Structured Query Language", "Simple Query Language", "Structured Query Language");
        this.addQuestion(q1);
        Question q2 = new Question("In SQL, which command(s) is(are) used to change a tabes storage characteristics",
                "CHANGE TABLE", "MODIFY TABLE", "ALTER TABLE", "ALTER TABLE");
        this.addQuestion(q2);
        Question q3 = new Question("Which of the following SQL statements are correct",
                "SELECT Username AND Password FROM Users", "None of these", "SELECT Username,Password FROM Users", "SELECT Username,Password FROM Users");
        this.addQuestion(q3);
        Question q4 = new Question("Which keyword is used to retrieve only unique values",
                "DISTINCT", "DINSTICTIVE",
                "UNIQUE",
                "DISTINCT");
        this.addQuestion(q4);
        Question q5 = new Question("What of the following is used to retrieve data",
                "DELETE", "INSERT",
                "SELECT", "SELECT");
        this.addQuestion(q5);
        Question q6 = new Question("Which statement is used to update data in a database", "SAVE",
                "UPDATE", "SAVE AS",
                "UPDATE");
        this.addQuestion(q6);
        Question q7 = new Question("Find all cities whose humidity is 89",
                "SELECT city FROM weather humidity = 89", "SELECT city FROM weather", "SELECT humidity = 89 from weather", "SELECT city FROM weather humidity = 89");
        this.addQuestion(q7);
        Question q8 = new Question("What is the meaning of LIKE '%0%0'"
                , "Fetaure begins with two 0s", "Feature has two 0s in it,at any position",
                "Feature has more than two 0s", "Feature has two 0s in it,at any position");
        this.addQuestion(q8);
        Question q9 = new Question("Which of the following is legal","SELECT SYSDATE-SYSDATE FROM DUAL",
                "SELECT SYSDATE-(SYSDATE+2) FROM DUAL", "NONE OF THESE",
                "SELECT SYSDATE-SYSDATE FROM DUAL");
        this.addQuestion(q9);

        Question q10 = new Question("Which of the following is not a type of a SQL constraint",
                "ALTERNATE KEY", "FOREIGN KEY", "UNIQUE",
                "ALTERNATE KEY");
        this.addQuestion(q10);

        Question q11 = new Question("The SQL ALTER statement can be used to",
                "change the table structure", "delete rows from the table", "add rows to the table", "change the table structure");
        this.addQuestion(q11);
        Question q12 = new Question("The comman to remove rows from a table 'CUSTOMER' is",
                "DELETE FROM CUSTOMER WHERE...", "UPDATE FROM CUSTOMER", "REMOVE FROM CUSTOMER", "DELETE FROM CUSTOMER WHERE...");
        this.addQuestion(q12);
        Question q13 = new Question("THE SQL WHERE clause",
                "limits the column data that are returned", "limits the row data are returned", "limits altering", "limits the row data are returned");
        this.addQuestion(q13);
        Question q14 = new Question("The command to elimate a table from a database is",
                "REMOVE TABLE CUSTOMER", "DROP TABLE CUSTOMER",
                "DELETE TABLE CUSTOMER",
                "DROP TABLE CUSTOMER");
        this.addQuestion(q14);
        Question q15 = new Question("The SQL keyword() - is used with wildcards",
                "LIKE only", "In only",
                "NOT In Only", "LIKE ONLY");
        this.addQuestion(q15);
        Question q16 = new Question("A subquery in a SQL SELECT statement is enclosed in"
                , "brackets-[...]",
                "parenthisis-(...)", "CAPITAL LETTERS",
                "parenthisis-(...)");
        this.addQuestion(q16);
        Question q17 = new Question(
                "The result of a SQL SELECT statement is a -", "table",
                "report", "file", "table");
        this.addQuestion(q17);
        Question q18 = new Question("The sql keyword BETWEEN is used", "for ranges", "for data that is non returning",
                "for returning a single row", "for ranges");
        this.addQuestion(q18);
        Question q19 = new Question("Which sql statement is used for inserting into a database",

                "INSERT", "UPDATE",
                "SELECT INSERT", "INSERT");
        this.addQuestion(q19);

        Question q20 = new Question("Can you join tables using SQL?",
                "Yes", "No", "Maybe",
                "Yes");
        this.addQuestion(q20);
    }


    public void addQuestion(Question quest) {

        ContentValues val = returnContentValues(quest);

        dbase.insert(TABLE_SQL, null, val);

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

        String selectQuery = "SELECT  * FROM " + TABLE_SQL;

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
