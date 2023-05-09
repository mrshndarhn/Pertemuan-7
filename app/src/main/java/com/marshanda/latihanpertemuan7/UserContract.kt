package com.marshanda.latihanpertemuan7

import android.provider.BaseColumns

object UserContract {
    object UserEntry : BaseColumns{
        const val TABLE_NAME = "user"
        const val COLUMNS_ID = "id"
        const val COLUMNS_EMAIL = "email"
        const val COLUMNS_PASSWORD = "password"
    }
}