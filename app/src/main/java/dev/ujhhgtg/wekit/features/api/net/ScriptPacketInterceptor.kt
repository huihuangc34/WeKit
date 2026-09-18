package dev.ujhhgtg.wekit.features.api.net

import dev.ujhhgtg.wekit.features.api.net.abc.IWePacketInterceptor
import dev.ujhhgtg.wekit.loader.abc.ScriptHookRegistry
import org.json.JSONObject

object ScriptPacketInterceptor : IWePacketInterceptor {

    override fun onResponse(uri: String, cgiId: Int, respBytes: ByteArray): ByteArray? {
        val hooks = ScriptHookRegistry.getHooks()
        if (hooks.isEmpty()) return null

        return try {
            val data = WeProtoData.fromBytes(respBytes)
            val json = data.toJsonObject()
            var current = json.toString()

            var modified = false
            for (hook in hooks) {
                val result = hook.onResponse(cgiId, current)
                if (result != null && result != current) {
                    current = result
                    modified = true
                }
            }

            if (!modified) return null

            val newJson = JSONObject(current)
            data.applyViewJson(newJson, true)
            data.toPacketBytes()
        } catch (e: Exception) {
            null
        }
    }
}