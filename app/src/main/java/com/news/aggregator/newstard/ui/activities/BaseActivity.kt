package com.news.aggregator.newstard.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.news.aggregator.newstard.ui.viewmodels.ViewModelFactory
import dagger.android.AndroidInjection
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseActivity<ViewModelClass: ViewModel, DataBindingClass: ViewDataBinding>: AppCompatActivity(){

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


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        _handleOnCreate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _handleOnCreate()
    }

    /**
     * Handle onCreate callback
     */
    private fun _handleOnCreate(){
        _configureDagger()

        _initViewModel()

        _initDataBindingLayout()

        bindLayoutVariables()
    }

    /**
     * Inject dagger dependencies
     */
    private fun _configureDagger(){
        AndroidInjection.inject(this)
    }

    /**
     * Init viewmodel instance
     */
    private fun _initViewModel() {
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
    private fun _initDataBindingLayout() {
        layoutBinding = DataBindingUtil.setContentView(this, getLayoutResource())
    }


}
