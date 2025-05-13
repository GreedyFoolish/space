import React from 'react'
import ReactDOM from 'react-dom'
import App from './App'
import{createStore,applyMiddleware,compose} from 'redux'
import thunk from 'redux-thunk'
import reducer from "./redux"
import {Provider} from 'react-redux'
import {HashRouter} from 'react-router-dom'
const devToolsExtension=window.devToolsExtension?window.devToolsExtension():()=>{};
let store=window.devToolsExtension?(
	createStore(
		reducer,
		compose(
			applyMiddleware(thunk),
			devToolsExtension
		)
	)
):(
	createStore(reducer,applyMiddleware(thunk))
)
ReactDOM.render(
    <Provider store={store}>
		<HashRouter>
			<App/>
		</HashRouter>
    </Provider>,
    document.getElementById('root')
)