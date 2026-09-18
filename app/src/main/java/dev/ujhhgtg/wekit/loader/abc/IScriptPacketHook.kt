package dev.ujhhgtg.wekit.loader.abc

interface IScriptPacketHook {
    /**
     * @param cgiId CGI 编号
     * @param jsonString 响应包的 JSON 字符串
     * @return 修改后的 JSON 字符串；返回 null 表示不修改
     */
    fun onResponse(cgiId: Int, jsonString: String): String?
}