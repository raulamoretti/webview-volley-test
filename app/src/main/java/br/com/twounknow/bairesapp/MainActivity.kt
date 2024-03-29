package br.com.twounknow.bairesapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.com.twounknow.bairesapp.models.Item
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    ItemFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: Item?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item?.htmlUrl))
        startActivity(browserIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_github -> {
                val github = ItemFragment ()
                supportFragmentManager.beginTransaction().replace(R.id.container, github).commit()
            }
            R.id.nav_buttons -> {
                val buttons = ButtonsFragment ()
                supportFragmentManager.beginTransaction().replace(R.id.container, buttons).commit()
            }
            R.id.nav_google -> {
                val google = GoogleFragment ()
                supportFragmentManager.beginTransaction().replace(R.id.container, google).commit()
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun showAlert( view: View){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("About the test")
        builder.setMessage("I'm in my old notebook")
        builder.setPositiveButton("ok"){dialog, which ->
            Toast.makeText(baseContext,"Sorry.", Toast.LENGTH_LONG).show()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showToast( view: View){
        Toast.makeText(this,"It is a toast!.", Toast.LENGTH_LONG).show()
    }
}
