package dev.ujhhgtg.wekit.loader.abc

object ScriptHookRegistry {
    private val hooks = mutableListOf<IScriptPacketHook>()

    fun registerResponseHook(hook: IScriptPacketHook) {
        hooks.add(hook)
    }

    fun clear() {
        hooks.clear()
    }

    fun getHooks(): List<IScriptPacketHook> = hooks.toList()
}