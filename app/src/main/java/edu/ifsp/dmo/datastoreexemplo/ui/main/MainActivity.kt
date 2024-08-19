package edu.ifsp.dmo.datastoreexemplo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import edu.ifsp.dmo.datastoreexemplo.data.repository.UserRepository
import edu.ifsp.dmo.datastoreexemplo.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Instanciar a viewModel usando a Factory
        val respository = UserRepository(applicationContext)
        val myFactory = MainViewModelFactory(respository)

        viewModel = ViewModelProvider(this, myFactory).get(
            MainViewModel::class.java
        )

        setupObservers()
        setupListeners()

    }

    private fun  setupListeners(){
        binding.buttonSave.setOnClickListener{
            val str = binding.editName.text.toString()
            viewModel.save(str)
        }
    }

    private fun setupObservers(){
        lifecycleScope.launch {
            viewModel.username.collect{
                if (it.isNullOrBlank()){
                    binding.textMessage.text = "Bem vindo, usuario!"
                    binding.buttonSave.text = "Salvar"
                }else{
                    binding.textMessage.text = "Bem vindo de volta, $it!"
                    binding.buttonSave.text = "Alterar"
                }
            }
        }
    }




}