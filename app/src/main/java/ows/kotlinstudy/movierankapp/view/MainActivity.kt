package ows.kotlinstudy.movierankapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import ows.kotlinstudy.movierankapp.Constants.MOVIEID
import ows.kotlinstudy.movierankapp.MovieApplication
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.dagger.component.ActivityComponent
import ows.kotlinstudy.movierankapp.dagger.module.ActivityModule
import ows.kotlinstudy.movierankapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var activityComponent: ActivityComponent
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navController by lazy { navHostFragment.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.included.toolbar)
        initViews()
        initActivityComponent()
    }

    override fun onSupportNavigateUp(): Boolean {
        val controller = Navigation.findNavController(this, R.id.nav_host_fragment)
        return (NavigationUI.navigateUp(controller, appBarConfiguration)
                || super.onSupportNavigateUp())
    }

    private fun initViews() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_movie, R.id.nav_moviedetail),
            drawerLayout = binding.drawerLayout
        )
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    private fun initActivityComponent() {
        activityComponent = (application as MovieApplication).getAppComponent().getActivityBuilder()
            .setActivity(this)
            .setModule(ActivityModule())
            .build()
        activityComponent.inject(this)
    }

    fun getAcitivtyComponent() = activityComponent

    fun showMovieDetail(id: Int) {
        navController.navigate(R.id.nav_moviedetail, Bundle().apply { putInt(MOVIEID, id) })
    }

}