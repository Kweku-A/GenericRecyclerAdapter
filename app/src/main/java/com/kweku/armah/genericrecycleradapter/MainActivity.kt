package com.kweku.armah.genericrecycleradapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.kweku.armah.genericrecycleradapter.databinding.ActivityMainBinding
import com.kweku.armah.genericrecycleradapter.databinding.ItemLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var genericAdapter: GenericAdapter<Student, ItemLayoutBinding>

    // data model list
    private val updatedList = mutableListOf<Student>(
        Student("John Doe Jr.", 17, "Grade A"),
        Student("Abraham Dappy", 15, "Grade B"),
        Student("Chris Christian", 18, "Grade A"),
        Student("Jasmin Flower", 14, "Grade A"),
        Student("Enoch Boon", 19, "Grade C"),
        Student("Jane Doe", 16, "Grade A"),
        Student("Fam Doe", 16, "Grade A"),
        Student("Sarah Kay", 17, "Grade A")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //pass the resource id to the adapter, which will be used for inflation of the databinding
        genericAdapter = GenericAdapter(R.layout.item_layout)

        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = genericAdapter
        }

        //receive the dataBinding from the adapter along with the data and set them as required
        genericAdapter.listener =
            object : GenericAdapter.GenericDataAndBindingInterface<Student, ItemLayoutBinding> {
                override fun bindingData(dataModel: Student, binding: ItemLayoutBinding) {
                    binding.apply {
                        ageTextView.text = dataModel.age.toString()
                        gradeTextView.text = dataModel.grade
                        nameTextView.text = dataModel.name
                        root.setOnClickListener {
                            Toast.makeText(
                                this@MainActivity,
                                " You clicked $dataModel",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                }

            }

        binding.updateButton.setOnClickListener {
            genericAdapter.dataModelList = updatedList
        }

    }
}