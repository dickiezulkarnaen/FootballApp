package com.dickiez.soccerhub.localstorage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dickiez.soccerhub.models.FavoriteMatchModel
import com.dickiez.soccerhub.models.FavoriteTeamModel
import org.jetbrains.anko.db.*

class DatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(context, "Football.db", null, 1) {

    companion object {

        private var instance : DatabaseHelper? = null

        fun getInstance(context: Context) : DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance!!
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteMatchModel.TABLE_FAVORITE_MATCH, true,
                FavoriteMatchModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatchModel.MATCH_ID to TEXT,
                FavoriteMatchModel.MATCH_DATE to TEXT,
                FavoriteMatchModel.HOME_TEAM to TEXT,
                FavoriteMatchModel.AWAY_TEAM to TEXT,
                FavoriteMatchModel.HOME_SCORE to TEXT,
                FavoriteMatchModel.AWAY_SCORE to TEXT)

        db?.createTable(FavoriteTeamModel.TABLE_FAVORITE_TEAM, true,
                FavoriteTeamModel.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
        FavoriteTeamModel.TEAM_ID to TEXT,
        FavoriteTeamModel.TEAM_NAME to TEXT,
        FavoriteTeamModel.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMatchModel.TABLE_FAVORITE_MATCH, true)
        db?.dropTable(FavoriteTeamModel.TABLE_FAVORITE_TEAM, true)
    }
}

val Context.database : DatabaseHelper get() = DatabaseHelper.getInstance(applicationContext)