package com.news.aggregator.newstard.ui.views.iconbarview

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.repositories.services.NewsService
import com.news.aggregator.newstard.utils.extensions.scrollXToViewCenter

class IconBarView(context: Context, attributeSet: AttributeSet): ConstraintLayout(context, attributeSet) {

    // Class level extension function
    private fun Float.toDP() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, resources.displayMetrics).toInt()

    // Layout for View
    val layout: ConstraintLayout = inflate(context, R.layout.view_icon_bar_layout, this) as ConstraintLayout

    // Data binding Variables
    var  services: List<NewsService>? = null
    set(value){
        field = value

        value?.let { renderServicesAsImageIcons(it) }
    }

    // Property to set current selected icon
    var selectedNewsService: NewsService? = null
    set(value){
        val container: LinearLayout = findViewById(R.id.viewIconBarImageIconLayout)

        // If selected service exists and View with same id exists then scale down
        selectedNewsService?.let{
            container.findViewById<ImageButton>(it.id)?.let { imageButton -> scaleDownImageButton(imageButton)  }
        }

        // If value is not null and View with same id exists then scale up and scroll
        value?.let { newsService ->
            container.findViewById<ImageButton>(newsService.id)?.let {
                    scaleUpImageButton(it)
                    findViewById<HorizontalScrollView>(R.id.viewIconBarImageIconScrollView).scrollXToViewCenter(it)
            }
        }

        field = value
    }

    // Private variables
    private val _serviceClickEventHandler: ArrayList<ServiceIconClickListener> = ArrayList()


    private val _iconButtonWidth = 30f.toDP()
    private val _iconButtonHeight = 30f.toDP()
    private val _activeIconButtonWidth = 50f.toDP()
    private val _activeIconButtonHeight = 50f.toDP()


    // Public apis

    /**
     * Add listener for icon click listener
     */
    fun setOnServiceIconClickListener(listener: ServiceIconClickListener){
        _serviceClickEventHandler.add(listener)
    }

    /**
     * Add listener for icon click listener via lambda expression
     */
    fun setOnServiceIconClickListener(wrapper: (service: NewsService) -> Unit) {

        _serviceClickEventHandler.add(object: ServiceIconClickListener{
            override fun onServiceIconClicked(service: NewsService) = wrapper(service)
        })

    }


    /**
     * Add services as child view in linear layout
     */
    private fun renderServicesAsImageIcons(services: List<NewsService>) {
        val container: LinearLayout = findViewById(R.id.viewIconBarImageIconLayout)
        services.forEach { container.addView(getViewForService(it)) }
    }

    /**
     * Convert service instance to ImageButtonView instance
     */
    private fun getViewForService(service: NewsService): ImageButton {

        val imageButton = ImageButton(context)

        val linearLayoutparams = LinearLayout.LayoutParams(_iconButtonWidth, _iconButtonHeight)
        linearLayoutparams.setMargins(5F.toDP(), 0, 5F.toDP(), 0)

        with(imageButton) {
            id = service.id
            layoutParams = linearLayoutparams
            scaleType = ImageView.ScaleType.FIT_XY
            adjustViewBounds = true


            setBackgroundResource(R.drawable.view_icon_bar_icon_button_background)
            setImageResource(service.icon)

            setOnClickListener {
                handleImageButtonClicked(imageButton, service)
            }
        }

        // Check for selected service
        if(service == selectedNewsService){
            scaleUpImageButton(imageButton)
        }else{
            scaleDownImageButton(imageButton)
        }

        return imageButton
    }

    private fun handleImageButtonClicked(view: ImageButton, service: NewsService) {
        // Updated selected service
        selectedNewsService = service

        // Call all listener
        _serviceClickEventHandler.map { it.onServiceIconClicked(service) }
    }

    /**
     * Scale up animation fot Image Button
     * Note: Can use ValueAnimator to show animation instead of instantaneous value change
     */
    private fun scaleUpImageButton(view: ImageButton){
        val layoutParams = view.layoutParams
        layoutParams.width = _activeIconButtonWidth
        layoutParams.height = _activeIconButtonHeight
        view.layoutParams = layoutParams
    }

    /**
     * Scale down animation fot Image Button
     */
    private fun scaleDownImageButton(view: ImageButton){
        val layoutParams = view.layoutParams
        layoutParams.width = _iconButtonWidth
        layoutParams.height = _iconButtonHeight
        view.layoutParams = layoutParams
    }

}
