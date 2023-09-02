package com.example.gomrukkolkulatoru.presentation.ui.home

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gomrukkolkulatoru.R
import com.example.gomrukkolkulatoru.common.base.BaseFragment
import com.example.gomrukkolkulatoru.common.util.CALENDAR_FORMAT
import com.example.gomrukkolkulatoru.common.util.hideKeyboard
import com.example.gomrukkolkulatoru.databinding.FragmentHomeBinding
import com.example.gomrukkolkulatoru.domain.model.CalculatorModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var viewModel: HomeViewModel
    private val calendar = Calendar.getInstance(Locale.ENGLISH)
    private val now = System.currentTimeMillis() - 1000
    private var autoType = ""
    private var engineType = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSpinner()
        setSpinnerId()
        setClearListener()
        calendar.set(2013, Calendar.JANUARY, 1)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        val date = object : OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel()
            }
        }

        binding.issueDateEditText.setOnClickListener {
            val dateDialog = DatePickerDialog(
                requireContext(),
                date,
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR)
            )
            dateDialog.show()
            dateDialog.datePicker.minDate = calendar.time.time
            dateDialog.datePicker.maxDate = now
        }

        binding.calculateButton.setOnClickListener {
            calculate()
        }

        binding.clearButton.apply {
            setOnClickListener {
                clearText()
                isEnabled = false
            }
        }
        binding.issueDateEditText.hideKeyboard()
    }

    private fun updateLabel() {
        val dateFormat = SimpleDateFormat(CALENDAR_FORMAT, Locale.ENGLISH)
        binding.issueDateEditText.setText(dateFormat.format(calendar.time))
    }

    private fun setSpinner() {
        val autoTypes = resources.getStringArray(R.array.autoType)
        val arrayAdapterAutoType = ArrayAdapter(requireContext(), R.layout.dropdown_item, autoTypes)
        binding.spinnerAutoType.setAdapter(arrayAdapterAutoType)
        val engineTypes = resources.getStringArray(R.array.engineType)
        val arrayAdapterEngineType =
            ArrayAdapter(requireContext(), R.layout.dropdown_item, engineTypes)
        binding.spinnerEngineType.setAdapter(arrayAdapterEngineType)
    }

    private fun setSpinnerId() {
        binding.spinnerAutoType.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                autoType = position.toString()
            }
        }
        binding.spinnerEngineType.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                engineType = position.toString()
            }
        }
    }

    private fun calculate() {
        val engine = binding.engineEditText.text.toString()
        var result = 0
        val issueDate = binding.issueDateEditText.text.toString()
        val price = binding.priceEditText.text.toString()

        if (binding.radioButton1.isChecked) {
            result = 0
        }
        if (binding.radioButton2.isChecked) {
            result = 1
        }

        if (autoType.isEmpty() || engine.isEmpty() || engine.isEmpty() || binding.radioGroup.isEmpty() || issueDate.isEmpty() || price.isEmpty()) {
            Toast.makeText(context, R.string.xanalari_doldurun, Toast.LENGTH_LONG).show()
        } else {
            findNavController().navigate(R.id.action_homeFragment_to_resultFragment)
            viewModel.calculate(
                CalculatorModel(
                    autoType,
                    engineType,
                    engine.toInt(),
                    result.toString(),
                    issueDate,
                    price.toDouble()
                )
            )
            viewModel.calculateModel.value = (
                CalculatorModel(
                    binding.spinnerEngineType.text.toString(),
                    engineType,
                    engine.toInt(),
                    result.toString(),
                    issueDate.takeLast(4),
                    price.toDouble()
                )
            )
        }
    }

    private fun clearText() {
        binding.spinnerAutoType.text.clear()
        binding.spinnerEngineType.text.clear()
        binding.engineEditText.text?.clear()
        binding.priceEditText.text?.clear()
        binding.issueDateEditText.text?.clear()
        binding.radioGroup.clearCheck()
    }

    private fun setClearListener() {
        binding.spinnerAutoType.addTextChangedListener {
            binding.clearButton.isEnabled = true
        }

        binding.engineEditText.addTextChangedListener {
            binding.clearButton.isEnabled = true
        }
        binding.spinnerEngineType.addTextChangedListener {
            binding.clearButton.isEnabled = true
        }
        binding.issueDateEditText.addTextChangedListener {
            binding.clearButton.isEnabled = true
        }
        binding.priceEditText.addTextChangedListener {
            binding.clearButton.isEnabled = true
        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            binding.clearButton.isEnabled = true
        }
    }

}