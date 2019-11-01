package im.status.keycard.connect.card

import im.status.keycard.connect.Registry
import java.io.IOException
import java.lang.Exception

class ChangePINCommand(private val newPIN: String) : CardCommand {
    override fun run(context: CardScriptExecutor.ScriptContext): CardCommand.Result {
        try {
            context.cmdSet.changePIN(newPIN).checkOK()
            Registry.pinCache.putPIN(context.cmdSet.applicationInfo.instanceUID, newPIN)
        } catch(e: IOException) {
            return CardCommand.Result.RETRY
        } catch (e: Exception) {
            return CardCommand.Result.CANCEL
        }

        return CardCommand.Result.OK
    }
}