package com.example.automatic_analytics_analyser.view.fragments.medication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.AnalysisItemBinding
import com.example.automatic_analytics_analyser.databinding.FragmentCalendarBinding
import com.example.automatic_analytics_analyser.databinding.FragmentMedicationBinding
import com.example.automatic_analytics_analyser.databinding.MedicationItemBinding
import com.example.automatic_analytics_analyser.model.Analysis
import com.example.automatic_analytics_analyser.model.Appointment
import com.example.automatic_analytics_analyser.model.Medication
import com.example.automatic_analytics_analyser.model.Medicine
import com.example.automatic_analytics_analyser.view.fragments.AbstractFragment
import com.example.automatic_analytics_analyser.view.fragments.DrawerActivity
import com.example.automatic_analytics_analyser.view.fragments.analysis.BindingAnalysisItem
import com.example.automatic_analytics_analyser.view.fragments.calendar.BindingCalendarItem
import com.example.automatic_analytics_analyser.view.fragments.calendar.CalendarViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook

class MedicationFragment : AbstractFragment() {
    private val itemAdapter = ItemAdapter<BindingMedicationItem>()
    private lateinit var binding: FragmentMedicationBinding

    private val viewModel: MedicationViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MedicationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentMedicationBinding>(
            inflater,
            R.layout.fragment_medication,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Move to AbstractRecyclerFragment?
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val fastAdapter = FastAdapter.with(itemAdapter)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fastAdapter
        }

        viewModel.medication.observe(viewLifecycleOwner, Observer {
            populateMedication(it)
        })

        //Functionality for share button
        fastAdapter.addEventHook(object : ClickEventHook<BindingMedicationItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return viewHolder.asBinding<MedicationItemBinding> {
                    it.shareButton
                }
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<BindingMedicationItem>,
                item: BindingMedicationItem
            ) {
                shareMedication(item.medication)
            }

        })

        (requireActivity() as DrawerActivity).supportActionBar!!.setTitle(R.string.medication_screen)

        viewModel.refreshMedication()
    }

    private fun populateMedication(data: List<Medication>) {
        binding.refreshMedication.isRefreshing = false
        val items = data.map {
            BindingMedicationItem(it)
        }
        itemAdapter.setNewList(items)
    }

    private fun parseMedicationContent(medicines: List<Medicine>): String {
        var medicinesText = ""
        medicines.forEach {
            medicinesText += it.name + " " + it.dose + "mg" + "\n\n"
        }
        return medicinesText
    }

    private fun shareMedication(medication: Medication) {
        val shareText =
            "${resources.getString(R.string.medicationDisease)} ${medication.disease} " +
                    resources.getString(R.string.es) + "\n" +
                    "${parseMedicationContent(medication.medicines)} \n\n"
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, shareText)
        startActivity(shareIntent)
    }


}