package wxmlabs.smshub

@Suppress("unused")
abstract class SMSender(private val name: String, private val version: Double, private val info: String) {
    /**
     * 发送短信
     */
    abstract infix fun send(message: ShortMessage): Transfer

    fun getName() = this.name
    fun getVersion() = this.version
    fun getInfo() = this.info

    override fun toString(): String {
        return "Short Message Sender(name:$name, version:$version, info:$info)"
    }
}
