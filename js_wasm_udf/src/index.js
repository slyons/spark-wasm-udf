var wasmudf = require("./wasmudf_pb.js")
var literal = require("./literal_pb.js")

export function add_two() {
    const inputBytes = Host.input()
    var args = wasmudf.UDFArgs.deserializeBinary(bytes);
    var argsList = args.getArgsList()
    if (argsList.length == 2) {
        var first_arg = argsList[0];
        var second_arg = argsList[1];
        if (first_arg.hasInteger() && second_arg.hasInteger()) {
            var res = new literal.Literal();
            res.setInteger(first_arg + second_arg);
            Host.output(res.serializeBinary())
            return 0;
        } else {
            Host.outputString("Both arguments should be Integers");
        }
    } else {
        Host.outputString("Invalid number of arguments, should be 2")
    }
    return -1;
}