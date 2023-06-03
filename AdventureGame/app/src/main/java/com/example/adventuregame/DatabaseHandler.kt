package com.example.adventuregame
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


val DATABASE_NAME = "GameDB"
val TABLE_NAME = "Decisions"
val COL_ID = "id"
val COL_DECISION = "decision"

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_DECISION + " TEXT)"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addDecision(decision: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_DECISION, decision)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
}