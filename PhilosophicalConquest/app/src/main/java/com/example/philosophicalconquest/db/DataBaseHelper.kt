package com.example.philosophicalconquest.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object TableInfo: BaseColumns {
    const val TABLE_NAME = "Highscores"
    const val TABLE_COLUMN_SCORE = "score"
    const val TABLE_COLUMN_TYPE = "type"
    const val TABLE_COLUMN_NICK = "nick"
}

object BasicCommand {
    const val SQL_CREATE_TABLE =
        "CREATE TABLE ${TableInfo.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_SCORE} INTEGER NOT NULL," +
                "${TableInfo.TABLE_COLUMN_TYPE} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_NICK} TEXT NOT NULL)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME}"
}

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, TableInfo.TABLE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE)
        onCreate(db)
    }

}