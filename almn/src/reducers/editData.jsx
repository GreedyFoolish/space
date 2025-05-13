const init={
	detial:"",
	from:"/",
	data:{},
	requesturl:{
		postURL:"",
		editURL:"",
		delURL:""
	}
}
const detailToFrom=(detail)=>{
	switch (detail) {
		case "管理员管理":
			return "/admin"
		case "博客存储":
			return "/blog/storage"
		case "博客审核":
			return "/blog/review"
		case "用户设置":
			return "/user/setting"
		default:
			return "/";
	}
}
const detialToRequestURL=(detail)=>{
	switch (detail) {
		case "管理员管理":
			return {
				postURL:"adminListData",
				editURL:"editAdmin",
				delURL:	"delAdmin"
			}
		case "博客存储":
			return {
				postURL:"blogListData",
				editURL:"editBlog",
				delURL:	"delBlog"
			}
		case "博客审核":
			return {
				postURL:"reviewListData",
				editURL:"editBlog",
				delURL:	"delBlog"
			}
		case "用户设置":
			return {
				postURL:"userListData",
				editURL:"editUser",
				delURL:	"delUser"
			}
		default:
			return "";
	}
}
const FromToDetial=(url)=>{
	switch (url) {
		case "/admin":
			return "管理员管理";
		case "/blog/storage":
			return "博客存储";
		case "/blog/review":
			return "博客审核";
		case "/user/setting":
			return "用户设置";
		default:
			return "/";
	}
}
export default function reducer(state=init,action) {
    switch (action.type) {
		case "更新编辑列表":
			let {from,data,detial}=action.param;
			if(from){
				detial=FromToDetial(from);
			}else if(detial){
				from=detailToFrom(detial);
			}
			return JSON.parse(JSON.stringify({
				detial,
				from,
				data:data,
				requesturl:detialToRequestURL(detial)
			}));
		case "清空编辑列表":
			{
				let {from,data,detial,requesturl}=state;
				return {
					from,
					detial,
					data:{},
					requesturl
				};
			}
        default:
            return state;
	}
}
export function updateEditList(param){
    return {
		type:"更新编辑列表",
		param:param
    }
}
export function clearEditList(param){
    return {
		type:"清空编辑列表",
    }
}
