package co.gaffe
import java.util.concurrent.ConcurrentHashMap
import org.extism.sdk.manifest.Manifest
import org.extism.sdk.{HostFunction, HostUserData, Plugin}
import org.extism.sdk.wasm.{UrlWasmSource, WasmSource, WasmSourceResolver}

import java.net.URL
import java.nio.file.Path

object WASMPluginCache {
  var cache: ConcurrentHashMap[String, Plugin] = new ConcurrentHashMap

  def getPlugin(sourceOrPath: String, withWasi: Boolean = false, hostFunctions: Array[HostFunction[_ <: HostUserData]] = null): Plugin = {
    if (sourceOrPath.startsWith("http")) {
      getPlugin(new URL(sourceOrPath), withWasi, hostFunctions)
    } else {
      getPlugin(Path.of(sourceOrPath), withWasi, hostFunctions)
    }
  }

  def getPlugin(sourcePath: Path, withWasi: Boolean , hostFunctions: Array[HostFunction[_ <: HostUserData]]):Plugin = {
    val srcPath = sourcePath.toAbsolutePath
    if (!cache.contains(srcPath.toString)) {
      val src = new WasmSourceResolver().resolve(srcPath)
      val plugin = this.pluginFromSource(src, withWasi, hostFunctions)
      cache.put(srcPath.toString, plugin)
      plugin
    } else {
      cache.get(srcPath.toString)
    }
  }

  def getPlugin(url: URL, withWasi: Boolean, hostFunction: Array[HostFunction[_ <: HostUserData]]) : Plugin = {
    if (!cache.contains(url.toString)) {
      val src = UrlWasmSource.fromUrl(url.toString)
      val plugin = this.pluginFromSource(src, withWasi, hostFunction)
      cache.put(url.toString, plugin)
      plugin
    } else {
      cache.get(url.toString)
    }
  }

  private def pluginFromSource(src: WasmSource, withWasi: Boolean = false, hostFunctions: Array[HostFunction[_ <: HostUserData]] = null): Plugin = {
    val manifest = new Manifest(src)
    new Plugin(manifest, withWasi, hostFunctions)
  }
}
