let color=window.localStorage.getItem("theme");

const init={
	name:color==="dark"?"dark":"light"
}

export default function theme(state=init,action) {
    switch (action.type) {
		case "夜间模式":
			return {name:"dark"};
		case "日间模式":
			return {name:"light"};
		default:
			return state;
	}
}
export  function SwitchToNight(){
    return {
		type:"夜间模式"
    }
}
export  function SwitchToLight(){
    return {
		type:"日间模式"
    }
}