package wxmlabs.smshub

object SMSHub {

    val register = Class.forName(System.getProperty("wxmlabs.smshub.register", MemoryRegister::class.java.name)).newInstance() as SMSHubRegister

    @JvmStatic
    fun register(sender: SMSender) {
        register.register(sender)
    }

    @JvmStatic
    fun assignFor(mobile: String): SMSender {
        return register.assignFor(mobile)
    }

    @JvmStatic
    infix fun send(message: String): ShortMessage {
        return ShortMessage(message)
    }

    class ShortMessage(private val message: String) {
        infix fun to(mobile: String) {
            SMSHub.assignFor(mobile).send(message)
        }
    }


    class MemoryRegister : SMSHubRegister() {
        override fun register(sender: SMSender) {
        }

        override fun assignFor(mobile: String): SMSender {
            return NoSMSender(mobile)
        }

    }
}
