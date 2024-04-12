package com.myaxa.movie.details.details_items

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.myaxa.movie.details.impl.R
import com.myaxa.movie.details.impl.databinding.ItemReviewBinding
import com.myaxa.movie.details.impl.databinding.ItemReviewsListBinding
import com.myaxa.movie.details.models.ReviewUI
import com.myaxa.movies.common.ViewBindingKotlinModel
import javax.inject.Inject

internal data class ReviewsListEpoxyModel(
    private val controller: ReviewsEpoxyController,
) : ViewBindingKotlinModel<ItemReviewsListBinding>(R.layout.item_reviews_list) {

    override fun ItemReviewsListBinding.bind() {
        val context = this.root.context
        val layoutManager = LinearLayoutManager(context, GridLayoutManager.HORIZONTAL, false)
        list.setController(controller)
        list.layoutManager = layoutManager
    }
}

internal class ReviewsEpoxyController @Inject constructor() : PagingDataEpoxyController<ReviewUI>() {
    override fun buildItemModel(currentPosition: Int, item: ReviewUI?): EpoxyModel<*> {
        return ReviewEpoxyModel(item!!)
            .id("review_${item.id}")
    }
}

internal data class ReviewEpoxyModel(private val model: ReviewUI) :
    ViewBindingKotlinModel<ItemReviewBinding>(R.layout.item_review) {

    override fun ItemReviewBinding.bind() {
        type.setBackgroundColor(root.context.getColor(model.type.attr))
        author.text = model.author
        title.text = model.title
        review.text = model.review
        date.text = model.date
    }
}

