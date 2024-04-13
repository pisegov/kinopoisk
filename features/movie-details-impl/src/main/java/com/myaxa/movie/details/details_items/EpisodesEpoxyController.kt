package com.myaxa.movie.details.details_items

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.myaxa.movie.details.impl.R
import com.myaxa.movie.details.impl.databinding.ItemEpisodeBinding
import com.myaxa.movie.details.impl.databinding.ItemEpisodesListBinding
import com.myaxa.movie.details.models.EpisodeUI
import com.myaxa.movies.common.ViewBindingKotlinModel
import javax.inject.Inject

internal data class EpisodesListEpoxyModel(
    private val controller: EpisodesEpoxyController,
) : ViewBindingKotlinModel<ItemEpisodesListBinding>(R.layout.item_episodes_list) {

    override fun ItemEpisodesListBinding.bind() {
        val context = this.root.context
        val layoutManager = LinearLayoutManager(context, GridLayoutManager.HORIZONTAL, false)
        list.setController(controller)
        list.layoutManager = layoutManager
    }
}

internal class EpisodesEpoxyController @Inject constructor() : PagingDataEpoxyController<EpisodeUI>() {
    override fun buildItemModel(currentPosition: Int, item: EpisodeUI?): EpoxyModel<*> {
        return EpisodeEpoxyModel(item!!)
            .id("s${item.seasonNumber}_e${item.episodeNumber}")
    }
}

internal data class EpisodeEpoxyModel(private val model: EpisodeUI) :
    ViewBindingKotlinModel<ItemEpisodeBinding>(R.layout.item_episode) {

    override fun ItemEpisodeBinding.bind() {
        title.text = model.title
        season.text = "Сезон ${model.seasonNumber}"
        episode.text = "Серия ${model.episodeNumber}"
        date.text = model.date
        image.load(model.image) {
            listener(
                onSuccess = { _, _ ->
                    image.visibility = View.VISIBLE
                },
                onError = { _, _ ->
                    image.visibility = View.GONE
                }
            )
        }
    }
}
