package com.example.empresas_android.ui

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.empresas_android.R
import com.example.empresas_android.databinding.ActivitySearchBinding
import com.example.empresas_android.model.Enterprise
import com.example.empresas_android.model.EnterpriseAdapter
import java.util.*


class SearchActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivitySearchBinding
    private lateinit var viewModel: SearchActivityViewModel
    private var enterpriseRecycler: RecyclerView? = null
    private var adapter: EnterpriseAdapter? = null
    private val enterpriseList = arrayListOf<Enterprise>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        setUpViewModel()
        setUpObservers()
        setUpUi()
    }

    private fun setRecyclerView() {

        enterpriseRecycler!!.layoutManager = LinearLayoutManager(this)
        enterpriseRecycler!!.adapter = adapter
        if(enterpriseRecycler!!.itemDecorationCount > 0)
            enterpriseRecycler!!.removeItemDecorationAt(0)
        enterpriseRecycler!!.addItemDecoration(object : RecyclerView.ItemDecoration() {

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State,
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                if (parent.getChildAdapterPosition(view) > 0) {
                    if(viewModel.calledWithFilter)
                        outRect.top = 5
                    else
                        outRect.top = 25
                }
            }
        })
    }

    private fun setUpUi(){
        enterpriseRecycler = dataBinding.recyclerEnterprise
        dataBinding.searchEditText.onRightDrawableClicked {
            it.visibility = View.GONE
            dataBinding.ioasysLogo.visibility = VISIBLE
            dataBinding.searchIcon.visibility = VISIBLE
            it.text.clear()
            viewModel.calledWithFilter = false
            setRecyclerView()
        }

        dataBinding.searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus){
                viewModel.fetchEnterprise()
                val keyboard = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                keyboard.hideSoftInputFromWindow(dataBinding.searchEditText.windowToken, 0)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun EditText.onRightDrawableClicked(onClicked: (view: EditText) -> Unit) {
        this.setOnTouchListener { v, event ->
            var hasConsumed = false
            if (v is EditText) {
                if (event.x >= v.width - v.totalPaddingRight) {
                    if (event.action == MotionEvent.ACTION_UP) {
                        onClicked(this)
                    }
                    hasConsumed = true
                }
            }
            hasConsumed
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)
        dataBinding.viewModel = viewModel
    }

    private fun setUpObservers(){

        viewModel.enterpriseList.observe(this, {
            enterpriseList.clear()
            enterpriseList.addAll(it)
            adapter = EnterpriseAdapter(this, enterpriseList)
            setRecyclerView()
        })

        viewModel.isSearching.observe(this, {
            enterpriseList.clear()
            dataBinding.ioasysLogo.visibility = it
            dataBinding.searchIcon.visibility = it
            dataBinding.searchEditText.visibility = VISIBLE
            dataBinding.searchEditText.requestFocus()
            val keyboard: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            keyboard.showSoftInput(dataBinding.searchEditText, InputMethodManager.SHOW_IMPLICIT)
        })

    }
}