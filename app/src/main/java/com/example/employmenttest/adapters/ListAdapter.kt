package com.example.employmenttest.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.employmenttest.R
import com.example.employmenttest.database.ChannelModel
import java.util.*
import kotlin.collections.ArrayList

class ListAdapter(
    private val context: Context,
    channelModel: MutableList<ChannelModel>
) : BaseAdapter(), Filterable {

    private var originalArray = channelModel
    private var filteredArray = channelModel

    override fun getCount(): Int {
        return filteredArray.size
    }

    override fun getItem(position: Int): Any {
        return filteredArray[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.custom_search_list, parent, false)
        } else {
            view = convertView
        }

        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = filteredArray[position].name

        val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
        checkBox.isChecked = filteredArray[position].status

        return view
    }

    override fun getFilter(): Filter {

        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val queryString = constraint?.toString()?.toLowerCase(Locale.ROOT).toString()

                if (queryString.isEmpty()) {
                    filterResults.values = originalArray
                } else {
                    filterResults.values = originalArray.filter {
                        it.name.toLowerCase(Locale.ROOT).contains(queryString)
                    }
                }

                filterResults.count = originalArray.size

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                Log.i("TAG", constraint.toString())
                filteredArray = results!!.values as MutableList<ChannelModel>
                notifyDataSetChanged()
            }
        }
    }
}