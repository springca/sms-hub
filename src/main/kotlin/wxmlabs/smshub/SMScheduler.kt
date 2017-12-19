package wxmlabs.smshub

abstract class SMScheduler {
    /**
     * 向调度中心添加Sender
     */
    abstract fun addSender(sender: SMSender)
    /**
     * 通过Sender名称获取Sender
     */
    abstract fun getSender(name: String): SMSender
    /**
     * 从调度中心移除Sender
     */
    abstract fun removeSender(name: String): SMSender
    /**
     * 为目标短信分配Sender
     */
    abstract fun assignFor(message: ShortMessage): SMSender
    /**
     * 确认该短信已送达。如：短信验证码被验证通过。
     */
    abstract fun receipt(transfer: Transfer)
}
