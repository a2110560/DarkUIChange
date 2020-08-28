package com.example.darklightchange

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref=SharedPreference(this)
        if (sharedPref.loadNightModeState()){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (sharedPref.loadNightModeState()){
            myswitch.isChecked=true
        }
        myswitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPref.setNightModeState(true)
                recreate()
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPref.setNightModeState(false)
                recreate()
            }
        }
        switch_test_page.setOnClickListener {
            startActivity(Intent(this,TestActivity::class.java))
            finish()
        }
    }
}
class SharedPreference(context: Context) {
    var mySharedPreferences:SharedPreferences = context.getSharedPreferences("filename",Context.MODE_PRIVATE)

    fun setNightModeState(state:Boolean) {
        val editor=mySharedPreferences.edit()
        editor.putBoolean("NightMode",state);
        editor.apply();
    }
    // this method will load the Night Mode State
    fun loadNightModeState():Boolean{
        var state = mySharedPreferences.getBoolean("NightMode",false);
        return  state;
    }
}