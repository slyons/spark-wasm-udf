# Extism C# PDK Plugin

See more documentation at https://github.com/extism/dotnet-pdk and
[join us on Discord](https://extism.org/discord) for more help.

## Build and run

```
dotnet build ./Plugin.csproj

extism call ./bin/Debug/net8.0/wasi-wasm/AppBundle/Plugin.wasm greet --wasi --input="Steve"
# => Hello, Steve!

extism call ./bin/Debug/net8.0/wasi-wasm/AppBundle/Plugin.wasm add --wasi --input='{"a": 20, "b": 21}'
# => {"Result":41}
```

## Publish

```
dotnet publish ./Plugin.csproj
```

Now you can find a trimmed `Plugin.wasm` file in `bin/Release/net8.0/wasi-wasm/AppBundle`
