package com.aplexgold.aplexshipping.Adapters.Data.Home


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.*
import com.aplexgold.aplexshipping.Data.Model.Home.MenuItem
import shady.samir.lawyerprofessionalacademy.R


class ExpandableListAdapter (
    context: Context, listDataHeader: List<MenuItem>,
    listChildData: HashMap<MenuItem, List<MenuItem>>
) : BaseExpandableListAdapter() {
    private val _context: Context
    private val _listDataHeader: List<MenuItem>

    // child data in format of header title, child title
    private val _listDataChild: HashMap<MenuItem, List<MenuItem>>
    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return _listDataChild[_listDataHeader[groupPosition]]?.get(childPosititon)!!
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup?
    ): View {
        var convertView: View? = convertView
        val childText = getChild(groupPosition, childPosition) as MenuItem
        if (convertView == null) {
            val infalInflater = _context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.list_item, null)
        }
        val txtListChild = convertView?.findViewById(R.id.lblListItem) as TextView
        val imgicon:ImageView = convertView.findViewById(R.id.imgicon) as ImageView
        txtListChild.text = childText.name
        imgicon.setImageResource(childText.icon)

        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return _listDataChild[_listDataHeader[groupPosition]]?.size!!
    }

    override fun getGroup(groupPosition: Int): Any {
        return _listDataHeader[groupPosition]
    }

    override fun getGroupCount(): Int {
        return _listDataHeader.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View? { var convertView: View? = convertView
        val headerTitle = getGroup(groupPosition) as MenuItem
        if (convertView == null) {
            val infalInflater = _context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.list_group, null)
        }
        val lblListHeader = convertView?.findViewById(R.id.lblListHeader) as TextView
        val img_down:ImageView = convertView.findViewById(R.id.img_down) as ImageView
        val imgicon:ImageView = convertView.findViewById(R.id.imgicon) as ImageView

        imgicon.setImageResource(headerTitle.icon)
        lblListHeader.text = headerTitle.name

        if (headerTitle.isMain){
            img_down.visibility = VISIBLE
            if (!isExpanded){
                img_down.setImageResource(R.drawable.ic_down_exg)
            }else{
                img_down.setImageResource(R.drawable.ic_up_exg)
            }
        }else{
            img_down.visibility = INVISIBLE
        }

        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    init {
        _context = context
        _listDataHeader = listDataHeader
        _listDataChild = listChildData
    }
}