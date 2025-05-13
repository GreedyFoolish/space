import { combineReducers } from 'redux'
import theme from './reducers/theme.jsx'
import SilderBar from './reducers/SilderBar'
import editData from './reducers/editData'
export default combineReducers(
	{
		theme,
		SilderBar,
		editData
	}
)
