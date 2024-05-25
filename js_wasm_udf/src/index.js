var messages = require("./wasmudf_pb.js")

export function greet() {
    const name = Host.inputString()
    var message = messages.UDFArgs()
    Host.outputString(`Hello, ${name}`)
}