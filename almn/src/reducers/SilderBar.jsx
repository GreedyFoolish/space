const init={
	SilderCollapsed:true
}
export default function reducer(state=init,action) {
    switch (action.type) {
        case "切换侧边栏":
			state.SilderCollapsed=!state.SilderCollapsed;
			return JSON.parse(JSON.stringify(state));
        default:
            return state;
    }
}
export function switchSilderBar() {
    return {
		type:"切换侧边栏"
    }
}