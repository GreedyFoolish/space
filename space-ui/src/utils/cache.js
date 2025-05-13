const session = {
    getJSON(key) {
        try {
            const item = sessionStorage.getItem(key)
            return item ? JSON.parse(item) : null
        } catch (e) {
            console.error(`读取缓存 ${key} 失败`, e)
            return null
        }
    },
    setJSON(key, value) {
        try {
            sessionStorage.setItem(key, JSON.stringify(value))
        } catch (e) {
            console.error(`写入缓存 ${key} 失败`, e)
        }
    }
}

export default {session}
