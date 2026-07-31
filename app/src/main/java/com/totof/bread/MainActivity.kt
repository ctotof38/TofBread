package com.totof.bread

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.viewpager2.widget.ViewPager2
import com.totof.bread.data.Input
import com.totof.bread.data.Preferences
import com.totof.bread.databinding.ActivityMainBinding
import com.totof.bread.process.InputInterface
import com.totof.bread.viewmodel.BreadViewModel

class MainActivity : AppCompatActivity(), MainFragmentCallBack {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: BreadViewModel by viewModels()
    private lateinit var mSectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.footerContainer) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(bottom = systemBars.bottom)
            insets
        }

        preferences = Preferences(this)
        viewModel.setInput(preferences!!.input)

        val input = viewModel.input.value

        mSectionsPagerAdapter = SectionsPagerAdapter(this)
        mSectionsPagerAdapter.addFragment(RecipeFragment.newInstance(input))
        mSectionsPagerAdapter.addFragment(InputFragment.newInstance(input))
        mSectionsPagerAdapter.addFragment(QuantityFragment.newInstance(input))

        binding.container.adapter = mSectionsPagerAdapter
        binding.container.currentItem = DEFAULT_FRAGMENT
        updateFooterText(DEFAULT_FRAGMENT)
        
        binding.container.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handlePageSelected(position)
                updateFooterText(position)
            }
        })
    }

    private fun updateFooterText(position: Int) {
        val messageResId = when (position) {
            0 -> R.string.footer_recipe
            1 -> R.string.footer_quantity
            2 -> R.string.footer_instructions
            else -> R.string.bon_appetit
        }
        binding.footerText.setText(messageResId)
    }

    private fun handlePageSelected(position: Int) {
        val input = viewModel.input.value ?: return
        input.currentFragmentPosition = position
        
        val old = mSectionsPagerAdapter.getFragment(input.oldFragmentPosition)
        val imm = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(binding.container.windowToken, 0)

        if (old is InputInterface) {
            old.update()
            preferences?.update()
        }

        val current = mSectionsPagerAdapter.getFragment(position)
        if (current is InputInterface) {
            current.update()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onChangedFragment(input: Input) {
        Toast.makeText(this, "fragment move", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val DEFAULT_FRAGMENT = 1
        var preferences: Preferences? = null
            private set
    }
}
