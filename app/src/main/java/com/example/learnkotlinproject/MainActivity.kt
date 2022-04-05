package com.example.learnkotlinproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.learnkotlinproject.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.statusMessage.text = "Введите запрос"
        binding.statusIcon.setImageResource(R.drawable.ic_baseline_search_24)

        binding.getCountryButton.setOnClickListener {
            val countryName = binding.cityNameSearchEditText.text.toString()

            lifecycleScope.launch{
                try{
                    val countries = RemoteDataSourse().restCountriesApi.getCountryByName(countryName)
                    val country = countries[0]

                    binding.countryTextView.text = country.name
                    binding.capitalValueTextView.text = country.capital
                    binding.populationValueTextView.text = formatLong(country.population)
                    binding.areaValueTextView.text = formatLong(country.area)

                    binding.languagesValueTextView.text = country.languagesToString()

                    loadSvg(binding.flagImageView, country.flag)

                    binding.resultLayout.visibility = View.VISIBLE
                    binding.statusLayout.visibility = View.INVISIBLE
                }
                catch (e: Exception)
                {
                    binding.resultLayout.visibility = View.INVISIBLE
                    binding.statusLayout.visibility = View.VISIBLE

                    binding.statusMessage.text = "Страна не найдена"
                    binding.statusIcon.setImageResource(R.drawable.ic_baseline_error_outline_24)
                }
            }
        }
    }
}