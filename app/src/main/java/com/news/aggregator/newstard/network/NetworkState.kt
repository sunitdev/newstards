package com.news.aggregator.newstard.network

class NetworkState(private val state: States) {

    enum class States {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object{
        val SUCCESS
            get() = NetworkState(States.SUCCESS)

        val ERROR
            get() = NetworkState(States.ERROR)

        val LOADING
            get() = NetworkState(States.LOADING)
    }

    override fun toString(): String {
        return "Network(state=${state.name})"
    }

    override fun equals(other: Any?): Boolean {
        if(other is NetworkState){
            return state == other.state
        }

        return super.equals(other)
    }
}
