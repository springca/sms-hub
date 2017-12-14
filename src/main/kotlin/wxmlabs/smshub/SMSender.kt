package wxmlabs.smshub

abstract class SMSender(private val mobile: String) {
    abstract fun send(message: String)
}
