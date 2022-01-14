package com.example.a186010_lab4_mpip.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.a186010_lab4_mpip.FirstFragmentDirections
import com.example.a186010_lab4_mpip.R
import com.example.a186010_lab4_mpip.SecondFragmentDirections
import com.example.a186010_lab4_mpip.models.DisplayStudent
import com.example.a186010_lab4_mpip.viewmodels.FirstFragmentViewModel

class StudentRecyclerViewAdapter(private val context: Fragment, private var students: List<DisplayStudent>, private var viewModel: FirstFragmentViewModel) :
    RecyclerView.Adapter<StudentRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val userId: TextView = view.findViewById(R.id.tvUserId)
        val nameSurname: TextView = view.findViewById(R.id.tvNameSurname)
        val index: TextView = view.findViewById(R.id.tvIndex)
        val telephone: TextView = view.findViewById(R.id.tvTelephone)
        val address: TextView = view.findViewById(R.id.tvAdress)

        val edit: Button = view.findViewById(R.id.btnEdit)
        val delete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_row, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentStudent = students[position]

        holder.edit.setOnClickListener {
            val action = SecondFragmentDirections.actionSecondFragmentToThirdFragment(currentStudent)
            findNavController(context).navigate(action)
        }

        holder.delete.setOnClickListener {
            viewModel.deleteStudent(currentStudent.id!!)
        }

        holder.userId.text = "userId: ${currentStudent.userId}"
        holder.nameSurname.text = "year: ${currentStudent.name} ${currentStudent.surname}"
        holder.index.text = "index: ${currentStudent.index}"
        holder.telephone.text = "telephone: ${currentStudent.telephoneNumber}"
        holder.address.text = "address: ${currentStudent.address}"
    }

    override fun getItemCount(): Int {
        return students.size
    }

    fun updateData(data: List<DisplayStudent>){
        this.students = data
        this.notifyDataSetChanged()
    }
}