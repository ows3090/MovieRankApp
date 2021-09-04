package ows.kotlinstudy.movierankapp.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import ows.kotlinstudy.movierankapp.MovieApplication
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.dagger.ActivityComponent
import ows.kotlinstudy.movierankapp.dagger.ActivityModule
import ows.kotlinstudy.movierankapp.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var activityName : String
    private lateinit var binding: ActivityMainBinding
    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.included.toolbar)
        initViews()
        initActivityComponent()
    }

    private fun initViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        binding.included.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun initActivityComponent() {
        activityComponent = (application as MovieApplication).getAppComponent().getActivityBuilder()
            .setActivity(this)
            .setModule(ActivityModule())
            .build()

        activityComponent.inject(this)

        Log.d("msg",activityName)
    }

    fun getAcitivtyComponent() = activityComponent

}