package com.news.aggregator.newstard.network

class NetworkState(_state: States) {

    enum class States {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object{


        fun getSuccessState(): NetworkState {
            return NetworkState(States.SUCCESS)
        }

        fun getLoadingState(): NetworkState {
            return NetworkState(States.LOADING)
        }

        fun getErrorState(): NetworkState {
            return NetworkState(States.ERROR)
        }

    }

    var state: States
        private set

    init {
        state = _state
    }

    override fun toString(): String {
        return "Network(state=${state.name})"
    }
}
