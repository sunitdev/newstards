package com.news.aggregator.newstard.ui.fargments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.news.aggregator.newstard.ui.viewmodels.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseFragment<ViewModelClass: ViewModel, DataBindingClass: ViewDataBinding>: Fragment() {

    protected lateinit var viewModel: ViewModelClass
    protected lateinit var layoutBinding: DataBindingClass

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    /**
     * Return layout resource to be used by the activity
     */
    protected abstract fun getLayoutResource(): Int

    /**
     * Callback to initialize data binding variable
     */
    protected open fun bindLayoutVariables(){}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        configureDagger()

        initViewModel()

        initDataBindingLayout(inflater, container)

        bindLayoutVariables()

        return layoutBinding.root
    }

    /**
     * Inject dagger dependencies
     */
    private fun configureDagger(){
        AndroidSupportInjection.inject(this)
    }

    /**
     * Init viewmodel instance
     */
    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
    }

    /**
     * Get viewmodel class from generic instance
     */
    private fun getViewModelClass(): Class<ViewModelClass> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<ViewModelClass>
    }

    /**
     * Init databinding layout variable
     */
    private fun initDataBindingLayout(inflater: LayoutInflater, container: ViewGroup?) {
        layoutBinding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false)
    }


}