package co.gaffe

import com.dylibso.chicory.runtime.HostFunction
import org.extism.chicory.sdk.{Plugin, Manifest => WASMManifest}

import java.util.concurrent.ConcurrentHashMap
//import org.extism.sdk.manifest.Manifest
//import org.extism.sdk.{HostFunction, HostUserData, Plugin}
//import org.extism.sdk.wasm.{UrlWasmSource, WasmSource, WasmSourceResolver}

import java.net.URL
import java.nio.file.Path

object ChicoryWASMPluginCache {
  var cache: ConcurrentHashMap[String, Plugin] = new ConcurrentHashMap

  def getPlugin(sourceOrPath: String, withWasi: Boolean = false, hostFunctions: Array[HostFunction] = new Array[HostFunction](0)): Plugin = {
    if (sourceOrPath.startsWith("http")) {
      getPlugin(new URL(sourceOrPath), withWasi, hostFunctions)
    } else {
      getPlugin(Path.of(sourceOrPath), withWasi, hostFunctions)
    }
  }

  def getPlugin(sourcePath: Path, withWasi: Boolean , hostFunctions: Array[HostFunction]):Plugin = {
    val srcPath = sourcePath.toAbsolutePath
    if (!cache.contains(srcPath.toString)) {
      val manifest = WASMManifest.fromFilePath(srcPath)
      val plugin = new Plugin(manifest, hostFunctions, null)
      cache.put(srcPath.toString, plugin)
      plugin
    } else {
      cache.get(srcPath.toString)
    }
  }

  //def getPlugin(url: URL, withWasi: Boolean, hostFunction: Array[HostFunction[_ <: HostUserData]]) : Plugin = {
  def getPlugin(url: URL, withWasi: Boolean, hostFunction: Array[HostFunction]) : Plugin = {
    if (!cache.contains(url.toString)) {
      val manifest = WASMManifest.fromUrl(url.toString)
      val plugin = new Plugin(manifest, hostFunction, null)
      //val plugin = this.pluginFromSource(src, withWasi, hostFunction)
      //cache += ((url.toString, plugin))
      cache.put(url.toString, plugin)
      //cache.addOne((url.toString, plugin))
      plugin
    } else {
      cache.get(url.toString)
    }
  }

  /*def getPlugin(sourceOrPath: String, withWasi: Boolean = false, hostFunctions: Array[HostFunction[_ <: HostUserData]] = null): Plugin = {
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
      cache += ((srcPath.toString, plugin))
      //cache.addOne((srcPath.toString, plugin))
      plugin
    } else {
      cache(srcPath.toString)
    }
  }*/

  //private def pluginFromSource(src: WasmSource, withWasi: Boolean = false, hostFunctions: Array[HostFunction[_ <: HostUserData]] = null): Plugin = {
  //  val manifest = new WASMManifest(src)
  //  new Plugin(manifest, withWasi, hostFunctions)
  //}
}
