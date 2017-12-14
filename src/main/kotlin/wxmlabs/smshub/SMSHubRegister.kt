package wxmlabs.smshub

abstract class SMSHubRegister {
    abstract fun register(sender: SMSender)
    abstract fun assignFor(mobile: String): SMSender

    protected class NoSMSender(private val mobile: String) : SMSender(mobile) {
        override fun send(message: String) {
            println("mobile: $mobile, message: $message")
        }
    }
}
