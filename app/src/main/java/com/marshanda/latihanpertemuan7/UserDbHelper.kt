package com.marshanda.latihanpertemuan7

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDbHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "user_db"
        private val DATABASE_VERSION = 1

        private val SQL_CREATE_ENTRIES =
            " CREATE TABLE ${UserContract.UserEntry.TABLE_NAME} (" +
                    "${UserContract.UserEntry.COLUMNS_ID} INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "${UserContract.UserEntry.COLUMNS_EMAIL} VARCHAR(225), "+
                    "${UserContract.UserEntry.COLUMNS_PASSWORD} VARCHAR (255))"

        private val SQL_DELETE_ENTRIES = " DROP TABLE IF EXISTS ${UserContract.UserEntry.TABLE_NAME} "

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
    }

    fun insertData(user: User){
        val db = writableDatabase
        val sql = " INSERT INTO ${UserContract.UserEntry.TABLE_NAME} "+
                " (${UserContract.UserEntry.COLUMNS_ID}, " +
                "${UserContract.UserEntry.COLUMNS_EMAIL}, " +
                "${UserContract.UserEntry.COLUMNS_PASSWORD} ) " +
                " VALUES ( null, '${user.email}','${user.password}') "
        db.execSQL(sql)
        db.close()
    }

    fun getUser(email : String, password : String) :User? {
        val db = readableDatabase
        val sql = " SELECT * FROM ${UserContract.UserEntry.TABLE_NAME} WHERE ${UserContract.UserEntry.COLUMNS_EMAIL} = '${email}'" +
                "AND ${UserContract.UserEntry.COLUMNS_PASSWORD} = '${password}'"
        val cursor = db.rawQuery(sql,null)

        var user : User? = null

        if (cursor.moveToFirst()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMNS_ID))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMNS_EMAIL))
            val password = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMNS_PASSWORD))

            user = User(id,email,password)
        }
        cursor.close()
        db.close()
        return user
    }
}