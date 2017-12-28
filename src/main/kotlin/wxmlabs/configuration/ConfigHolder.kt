package wxmlabs.configuration

import java.io.File
import java.net.URL
import java.net.URLDecoder

fun findAllConfigFor(appMain: Class<*>): Array<URL> {
    val urls: MutableList<URL> = ArrayList()
    val name = appMain.simpleName.map { if (it.isUpperCase()) "_${it.toLowerCase()}" }
    val fileName = "$name.yml"

    // default
    with(File(URLDecoder.decode(appMain.protectionDomain.codeSource.location.file, "UTF-8"))) {
        if (isFile) {
            with(File(this.parent, fileName)) {
                if (exists()) {
                    urls.add(toURI().toURL())
                }
            }
        } else {
            with(appMain.classLoader.getResource(fileName)) {
                if (this != null) {
                    urls.add(this)
                }
            }
        }
    }
    // global
    // user
    // custom

    return urls.toTypedArray()
}

fun main(args: Array<String>) {
    findAllConfigFor(SomeApp::class.java)
}

class SomeApp : Any() {

}
