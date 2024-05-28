using System.Runtime.InteropServices;
using System.Text.Json.Serialization;
using Extism;
using Google.Protobuf;
using Co.Gaffe.Proto;

namespace Plugin;
public class Program
{
    public static void Main()
    {
        // Note: a `Main` method is required for the app to compile
    }

    [UnmanagedCallersOnly(EntryPoint = "add_two")]
    public static int Add_Two()
    {
        var pbufBytes = Pdk.GetInput();
        UDFArgs args = UDFArgs.Parser.ParseFrom(pbufBytes);
        if (args.Args.Count == 2) {
            var first_arg = args.Args[0];
            var second_arg = args.Args[1];
            
            if (first_arg.HasInteger && second_arg.HasInteger) {
                var result = new Literal();
                result.Integer = first_arg.Integer + second_arg.Integer;
                Pdk.SetOutput(result.ToByteArray());
                return 0;
            } else {
                Pdk.SetError($"Both arguments must be integers! Got {first_arg.LiteralTypeCase} and {second_arg.LiteralTypeCase}");
            }
        } else {
            Pdk.SetError($"Invalid number of arguments. Expected 2, got {args.Args.Count}");
        }
        return -1;
    }
}
