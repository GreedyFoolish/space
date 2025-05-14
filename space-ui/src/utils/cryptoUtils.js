/**
 * 使用 SHA-256 算法对密码进行哈希
 * 此函数利用浏览器内置的 crypto 对象来执行哈希操作，确保密码的安全性
 * 它首先检查浏览器是否支持必要的 crypto 功能，然后将密码转换为字节数组，
 * 再使用 SHA-256 算法进行哈希处理最后，将哈希结果转换为十六进制字符串
 * @param {string} password 需要哈希的密码
 * @returns {Promise<string>} 返回一个 Promise，解析为哈希后的十六进制字符串
 * @throws {Error} 如果浏览器不支持 crypto 对象，则抛出错误
 */
export async function sha256(password) {
    // 检查浏览器是否支持 crypto 对象
    if (!window.crypto || !window.crypto.subtle) {
        throw new Error("浏览器不支持 crypto 对象");
    }
    // 使用浏览器内置的 crypto 对象
    const encoder = new TextEncoder();
    // 将密码转换为字节数组
    const data = encoder.encode(password);
    // 使用 SHA-256 算法对密码进行哈希
    const hashBuffer = await window.crypto.subtle.digest("SHA-256", data);
    // 将哈希结果的 ArrayBuffer 转换为包含每个字节值的数组
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    // 将每个字节转换为十六进制字符串并拼接
    return hashArray.map(b => b.toString(16).padStart(2, "0")).join("");
}
